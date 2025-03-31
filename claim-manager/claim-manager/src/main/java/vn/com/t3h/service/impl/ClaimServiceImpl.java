package vn.com.t3h.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import vn.com.t3h.entity.ClaimEntity;
import vn.com.t3h.mapper.ClaimMapper;
import vn.com.t3h.mapper.DetailClaimMapper;
import vn.com.t3h.repository.ClaimRepository;
import vn.com.t3h.service.ClaimService;
import vn.com.t3h.service.dto.ClaimDTO;
import vn.com.t3h.service.dto.DetailClaimDTO;
import vn.com.t3h.service.dto.response.BaseResponse;
import vn.com.t3h.service.dto.response.ResponsePage;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ClaimServiceImpl implements ClaimService {

    @Autowired
    private ClaimRepository claimRepository;
    @Autowired
    private ClaimMapper claimMapper;
    @Autowired
    private DetailClaimMapper detailMapper;

    @Override
    public ResponsePage<List<ClaimDTO>> getAllClaims(String claimCode, LocalDate fromDate, LocalDate toDate, String codeStatus, Pageable pageable) {
        ResponsePage<List<ClaimDTO>> response = new ResponsePage<>();
        if (StringUtils.isEmpty(claimCode)){
            claimCode = null;
        }
        if (StringUtils.isEmpty(codeStatus)){
            codeStatus = null;
        }
        Page<ClaimEntity> page = claimRepository.findCondition(claimCode, fromDate, toDate, codeStatus, pageable);
        List<ClaimDTO> claimDTOS = page.stream().map(claimMapper::toDto).toList();
        response.setData(claimDTOS);
        response.setCode(HttpStatus.OK.value());
        response.setMessage("success");
        response.setPageIndex(pageable.getPageNumber());
        response.setPageSize(pageable.getPageSize());
        response.setTotalPage(page.getTotalPages());
        response.setTotalElement(page.getTotalElements());
        return response;
    }

    @Override
    public ResponsePage<List<DetailClaimDTO>> getClaimDetail(Pageable pageable) {
        ResponsePage<List<DetailClaimDTO>> response = new ResponsePage<>();
        Page<ClaimEntity> page = claimRepository.getAllClaims(pageable);
        List<DetailClaimDTO> detailDTOS = page.stream().map(detailMapper::toDto).toList();
        response.setPageIndex(pageable.getPageNumber());
        response.setPageSize(pageable.getPageSize());
        response.setTotalElement(page.getTotalElements());
        response.setTotalPage(page.getTotalPages());
        response.setCode(HttpStatus.OK.value());
        response.setMessage("success");
        response.setData(detailDTOS);
        return response;
    }

    @Override
    public BaseResponse<DetailClaimDTO> findByIdClaim(Long claimId) {
        BaseResponse<DetailClaimDTO> response = new BaseResponse<>();
        Optional<ClaimEntity> claim = claimRepository.findById(claimId);
        if (claim.isEmpty()){
            response.setCode(HttpStatus.NOT_FOUND.value());
            response.setMessage("claim not found");
            return response;
        }
        ClaimEntity claimEntity = claim.get();
        response.setCode(HttpStatus.OK.value());
        response.setMessage("success");
        response.setData(detailMapper.toDto(claimEntity));
        return response;
    }

}