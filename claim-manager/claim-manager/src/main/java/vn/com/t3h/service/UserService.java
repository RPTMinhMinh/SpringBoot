package vn.com.t3h.service;

import org.springframework.data.domain.Pageable;
import vn.com.t3h.service.dto.UserDTO;
import vn.com.t3h.service.dto.response.BaseResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface UserService {
    BaseResponse<List<UserDTO>> getAllUsers(String code, LocalDateTime createDate, String address, Pageable pageable);
}
