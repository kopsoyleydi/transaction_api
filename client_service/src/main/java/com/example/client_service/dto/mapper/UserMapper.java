package com.example.client_service.dto.mapper;

import com.example.client_service.dto.UserDto;
import com.example.client_service.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CityMapper.class, PermissionMapper.class})
public interface UserMapper {
    @Mapping(source = "city", target = "city")
    User toModel(UserDto userDto);

    @Mapping(source = "city", target = "city")
    UserDto toDto(User user);

    List<UserDto> toDtoList(List<User> userList);

    List<User> toModelList(List<UserDto> userDtoList);
}