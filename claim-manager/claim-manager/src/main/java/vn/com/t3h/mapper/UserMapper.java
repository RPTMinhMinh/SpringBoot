package vn.com.t3h.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import vn.com.t3h.entity.UserEntity;
import vn.com.t3h.service.dto.UserDTO;

@Component
public class UserMapper {
    public UserDTO toDto(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setCode(userEntity.getCode());
        userDTO.setFirstName(userEntity.getFirstName());
        userDTO.setLastName(userEntity.getLastName());
        userDTO.setAddress(userEntity.getAddress());
        userDTO.setCreateDate(userEntity.getCreatedDate());
        return userDTO;
    }
}
