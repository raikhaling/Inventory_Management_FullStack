package com.amnil.invbackend.controller;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import com.amnil.invbackend.dto.core.RoleDto;
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
    public ResponseEntity<String> assignRoleToUser(@RequestBody RoleDto roleDto) {
        roleService.assignRoleToUser(roleDto);
        return ResponseEntity.ok("Role assigned successfully.");
    }

    //update role
}
