package vn.com.t3h.service.dto;

import java.time.LocalDateTime;

public class DetailClaimDTO {
    private Long id;
    private String code;
//    private Long customerId;
    private String customerName;
    private String email;
    private String phone;
//    private Long productId;
    private String insuranceProductName;
    private LocalDateTime createdAt;
    private String description;
//    private Long statusId;
    private String statusCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getInsuranceProductName() {
        return insuranceProductName;
    }

    public void setInsuranceProductName(String insuranceProductName) {
        this.insuranceProductName = insuranceProductName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
}
