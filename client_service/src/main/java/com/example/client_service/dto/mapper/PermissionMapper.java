package com.example.client_service.dto.mapper;

import com.example.client_service.dto.PermissionDto;
import com.example.client_service.model.Permission;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    PermissionDto toDto(Permission permission);

    Permission toModel(PermissionDto boardDto);

    List<PermissionDto> toDtoList(List<Permission> list);

    List<Permission> toModelList(List<PermissionDto> list);
}
