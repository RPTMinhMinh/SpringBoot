package vn.com.t3h.service;

import org.springframework.data.domain.Pageable;
import vn.com.t3h.service.dto.ClaimDTO;
import vn.com.t3h.service.dto.DetailClaimDTO;
import vn.com.t3h.service.dto.response.BaseResponse;

import java.time.LocalDate;
import java.util.List;

public interface ClaimService {
    BaseResponse<List<ClaimDTO>> getAllClaims(String claimCode, LocalDate fromDate, LocalDate toDate, String codeStatus, Pageable pageable);
    BaseResponse<List<DetailClaimDTO>> getClaimDetail(Pageable pageable);
    BaseResponse<DetailClaimDTO> findByIdClaim(Long claimId);
}
