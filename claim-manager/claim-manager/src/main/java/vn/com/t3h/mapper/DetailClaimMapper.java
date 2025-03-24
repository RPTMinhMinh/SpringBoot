package vn.com.t3h.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.com.t3h.entity.*;
import vn.com.t3h.repository.CustomerRepository;
import vn.com.t3h.repository.InsuranceProductRepository;
import vn.com.t3h.repository.StatusRepository;
import vn.com.t3h.service.dto.DetailClaimDTO;

import java.util.Optional;

@Component
public class DetailClaimMapper {
    @Autowired
    private CustomerRepository customer;
    @Autowired
    private InsuranceProductRepository product;
    @Autowired
    private StatusRepository statusRepository;


    public DetailClaimDTO toDto(ClaimEntity detail) {
        DetailClaimDTO detailDTO = new DetailClaimDTO();
        detailDTO.setId(detail.getId());
        detailDTO.setCode(detail.getCode());
        Optional<CustomerEntity> customerEntity = customer.findById(detail.getCustomerEntity().getId());
        if (customerEntity.isPresent()) {
            detailDTO.setCustomerName(customerEntity.get().getName());
            detailDTO.setEmail(customerEntity.get().getEmail());
            detailDTO.setPhone(customerEntity.get().getPhoneNumber());
        }
        Optional<InsuranceProductEntity> insuranceProductEntity = product.findById(detail.getInsuranceProductEntity().getId());
        if (insuranceProductEntity.isPresent()) {
            detailDTO.setInsuranceProductName(insuranceProductEntity.get().getName());
        }
        detailDTO.setCreatedAt(detail.getCreatedDate());
        detailDTO.setDescription(detail.getDescription());
        Optional<ClaimStatusEntity> statusEntity = statusRepository.findById(detail.getClaimStatusEntity().getId());
        if (statusEntity.isPresent()) {
            detailDTO.setStatusCode(statusEntity.get().getCode());
        }
        return detailDTO;
    }
}
