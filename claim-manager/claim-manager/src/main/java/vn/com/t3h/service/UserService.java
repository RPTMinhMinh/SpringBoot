package vn.com.t3h.service;

import org.springframework.data.domain.Pageable;
import vn.com.t3h.service.dto.UserDTO;
import vn.com.t3h.service.dto.response.BaseResponse;
import vn.com.t3h.service.dto.response.ResponsePage;

import java.time.LocalDateTime;
import java.util.List;

public interface UserService {
    ResponsePage<List<UserDTO>> getAllUsers(String code, LocalDateTime fromDate, LocalDateTime toDate, String phone, Pageable pageable);
    BaseResponse<UserDTO> saveUser(UserDTO user);
    BaseResponse<UserDTO> findById(Long id);
    BaseResponse<UserDTO> updateUser(Long id, UserDTO user);
}
