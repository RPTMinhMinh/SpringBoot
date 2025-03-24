package vn.com.t3h.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import vn.com.t3h.entity.UserEntity;
import vn.com.t3h.mapper.UserMapper;
import vn.com.t3h.repository.UserRepository;
import vn.com.t3h.service.UserService;
import vn.com.t3h.service.dto.UserDTO;
import vn.com.t3h.service.dto.response.BaseResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    @Override
    public BaseResponse<List<UserDTO>> getAllUsers(String code, LocalDateTime createDate, String address, Pageable pageable) {
        BaseResponse<List<UserDTO>> response = new BaseResponse<>();
        if (StringUtils.isEmpty(code)){
            code = null;
        }
        if (StringUtils.isEmpty(address)){
            address = null;
        }
        Page<UserEntity> page = userRepository.findByCondition(code, createDate, address, pageable);
        List<UserDTO> userDTOS = page.stream().map(userMapper::toDto).toList();
        response.setData(userDTOS);
        response.setMessage("thanh cong!");
        response.setCode(HttpStatus.OK.value());
        response.setPageSize(pageable.getPageSize());
        response.setPageIndex(pageable.getPageSize());
        response.setTotalPage(page.getTotalPages());
        response.setTotalElement(page.getTotalElements());
        return response;
    }
}
