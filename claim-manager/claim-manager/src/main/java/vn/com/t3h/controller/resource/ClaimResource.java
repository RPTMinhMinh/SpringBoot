package vn.com.t3h.controller.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.com.t3h.service.ClaimService;
import vn.com.t3h.service.dto.ClaimDTO;
import vn.com.t3h.service.dto.DetailClaimDTO;
import vn.com.t3h.service.dto.response.BaseResponse;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/claim")
public class ClaimResource {
    @Autowired
    private ClaimService claimService;
    // localhost:8080/api/claim
    @GetMapping()
    public ResponseEntity<BaseResponse<List<ClaimDTO>>> getListClaim(@RequestParam(required = false) String claimCode,
                                                     @RequestParam(required = false) LocalDate fromDate,
                                                     @RequestParam(required = false) LocalDate toDate,
                                                     @RequestParam(required = false) String codeStatus,
                                                     Pageable pageable){
        BaseResponse<List<ClaimDTO>> claimDTOS = claimService.getAllClaims(claimCode, fromDate, toDate, codeStatus,pageable);
        return ResponseEntity.ok(claimDTOS);
    }

    @GetMapping("/all-detail")
    public ResponseEntity<BaseResponse<List<DetailClaimDTO>>> getAllDetailClaim(Pageable pageable){
        BaseResponse<List<DetailClaimDTO>> response = claimService.getClaimDetail(pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/findByIdClaim/{id}")
    public ResponseEntity<BaseResponse<DetailClaimDTO>> findByIdClaim(@PathVariable(value = "id") Long id){
        BaseResponse<DetailClaimDTO> response = claimService.findByIdClaim(id);
        return ResponseEntity.ok(response);
    }
}
