package com.amnil.invbackend.service;

import com.amnil.invbackend.dto.RoleDto;

public interface RoleService {
    RoleDto createRole(RoleDto roleDto);

    void assignRoleToUser(Long userId, String roleName);
}