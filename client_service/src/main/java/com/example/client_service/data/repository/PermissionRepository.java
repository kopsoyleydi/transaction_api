package com.example.client_service.data.repository;

import com.example.client_service.model.Permission;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    Permission findAllById(Long id);
}
