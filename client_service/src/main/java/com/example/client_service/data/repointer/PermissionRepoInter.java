package com.example.client_service.data.repointer;

import com.example.client_service.model.Permission;

import java.util.List;

public interface PermissionRepoInter {

    Permission insert(Permission permission);

    Permission change(Permission permission);

    Permission getPermissionById(Long id);

    List<Permission> getAllPermissions();
}
