package com.amnil.invbackend.service;

import com.amnil.invbackend.dto.core.RoleDto;

/**
 * The interface Role service.
 */
public interface RoleService {
    /**
     * Create role role dto.
     *
     * @param roleDto the role dto
     * @return the role dto
     */
    RoleDto createRole(RoleDto roleDto);

    /**
     * Assign role to user.
     *
     * @param roleDto the role dto
     */
    void assignRoleToUser(RoleDto roleDto);

}
