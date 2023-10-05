package com.amnil.invbackend.controller;
import com.amnil.invbackend.dto.core.RoleDto;
import com.amnil.invbackend.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The type Role controller.
 */
@RestController
@RequestMapping("/api/role/admin")
@RequiredArgsConstructor
public class RoleController {
    /**
     * roleService
     */
    private final RoleService roleService;

    /**
     * Create role role dto.
     *
     * @param roleDto the role dto
     * @return the role dto
     */
    @PostMapping("/create")
    public RoleDto createRole(@RequestBody RoleDto roleDto) {
        return roleService.createRole(roleDto);
    }

    /**
     * Assign role to user response entity.
     *
     * @param roleDto the role dto
     * @return the response entity
     */
    @PostMapping("/assign")
    public ResponseEntity<String> assignRoleToUser(@RequestBody RoleDto roleDto) {
        roleService.assignRoleToUser(roleDto);
        return ResponseEntity.ok("Role assigned successfully.");
    }

    //update role
}
