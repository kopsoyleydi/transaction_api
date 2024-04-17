package com.example.client_service.data;

import com.example.client_service.data.repointer.PermissionRepoInter;
import com.example.client_service.data.repository.PermissionRepository;
import com.example.client_service.model.Permission;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PermissionImpl implements PermissionRepoInter {

    private final PermissionRepository permissionRepository;
    @Override
    public Permission insert(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public Permission change(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public Permission getPermissionById(Long id) {
        return permissionRepository.findAllById(id);
    }

    @Override
    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }
}
