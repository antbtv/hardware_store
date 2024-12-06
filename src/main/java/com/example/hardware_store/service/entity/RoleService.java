package com.example.hardware_store.service.entity;

import com.example.hardware_store.entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    Optional<Role> findRoleById(Long id);

    Optional<Role> findRoleByName(String name);

    List<Role> findAllRoles();

    void saveRole(Role role);

    void updateRole(Role role);

    void deleteRoleById(Long id);
}