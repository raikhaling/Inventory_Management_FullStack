package com.amnil.invbackend.controller;

import com.amnil.invbackend.dto.RoleDto;
import com.amnil.invbackend.repository.RoleRepository;
import com.amnil.invbackend.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/role/admin")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;
    @PostMapping("/create")
    public RoleDto createRole(@RequestBody RoleDto roleDto) {
        return roleService.createRole(roleDto);
    }

    @PostMapping("/assign")
    public ResponseEntity<String> assignRoleToUser(@RequestParam Long userId, @RequestParam String roleName) {
        roleService.assignRoleToUser(userId, roleName);
        return ResponseEntity.ok("Role assigned successfully.");
    }
}
