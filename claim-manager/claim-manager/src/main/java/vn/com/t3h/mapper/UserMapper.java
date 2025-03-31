package vn.com.t3h.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.com.t3h.entity.UserEntity;
import vn.com.t3h.service.dto.UserDTO;
import vn.com.t3h.utils.ImageUtils;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity toEntity(UserDTO userDTO);
    @Mapping(target = "stringBase64Avatar", expression = "java(imageUtils.convertImageToBase64(userEntity.getPathAvatar()))")
    UserDTO toDto(UserEntity userEntity, @Context ImageUtils imageUtils);
}
