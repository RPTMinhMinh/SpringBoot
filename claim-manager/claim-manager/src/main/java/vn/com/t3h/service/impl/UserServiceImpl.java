package vn.com.t3h.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import vn.com.t3h.entity.RoleEntity;
import vn.com.t3h.entity.UserEntity;
import vn.com.t3h.mapper.UserMapper;
import vn.com.t3h.repository.RoleRepository;
import vn.com.t3h.repository.UserRepository;
import vn.com.t3h.service.UserService;
import vn.com.t3h.service.dto.UserDTO;
import vn.com.t3h.service.dto.response.BaseResponse;
import vn.com.t3h.service.dto.response.ResponsePage;
import vn.com.t3h.utils.Constant;
import vn.com.t3h.utils.GenerateCode;
import vn.com.t3h.utils.ImageUtils;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ImageUtils imageUtils;

    @Override
    public ResponsePage<List<UserDTO>> getAllUsers(String code, LocalDateTime fromDate, LocalDateTime toDate, String phone, Pageable pageable) {
        ResponsePage<List<UserDTO>> response = new ResponsePage<>();
        if (StringUtils.isEmpty(code)){
            code = null;
        }
        if (StringUtils.isEmpty(phone)){
            phone = null;
        }
        Page<UserEntity> page = userRepository.findByCondition(code, fromDate,toDate, phone, pageable);
        List<UserDTO> userDTOS = page.stream()
                .map(userEntity -> userMapper.toDto(userEntity, imageUtils))
                .toList();
        response.setData(userDTOS);
        response.setMessage("thanh cong!");
        response.setCode(HttpStatus.OK.value());
        response.setPageSize(pageable.getPageSize());
        response.setPageIndex(pageable.getPageSize());
        response.setTotalPage(page.getTotalPages());
        response.setTotalElement(page.getTotalElements());
        return response;
    }

    @Override
    public BaseResponse<UserDTO> saveUser(UserDTO userDto) {
        String pathFileAvatar = imageUtils.saveImageAvatar(userDto);
        String convertImageToBase64 = imageUtils.convertImageToBase64(pathFileAvatar);

        UserEntity userEntity = userMapper.toEntity(userDto);
        userEntity.setPathAvatar(pathFileAvatar);
        userEntity.setCode(GenerateCode.generateUniqueCode("USER"));
        setRole(userEntity);
        userEntity = userRepository.save(userEntity);
        userDto = userMapper.toDto(userEntity, imageUtils);
        userDto.setStringBase64Avatar(convertImageToBase64);
        BaseResponse<UserDTO> response = new BaseResponse<UserDTO>();
        response.setCode(HttpStatus.OK.value());
        response.setMessage(HttpStatus.OK.name());
        response.setData(userDto);
        return response;
    }

    private void setRole(UserEntity userEntity) {
        RoleEntity roleEntity = roleRepository.findByCodeAndDeletedIsFalse(Constant.ROLE_ADMIN_CODE);
        Set<RoleEntity> roleEntities = new HashSet<>();
        roleEntities.add(roleEntity);
        userEntity.setRoles(roleEntities);
    }


}
