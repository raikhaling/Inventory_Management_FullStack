package com.amnil.invbackend.service.impl;

import com.amnil.invbackend.dto.core.RoleDto;
import com.amnil.invbackend.entity.LocalUser;
import com.amnil.invbackend.entity.Role;
import com.amnil.invbackend.exception.EntityNotFoundException;
import com.amnil.invbackend.repository.RoleRepository;
import com.amnil.invbackend.repository.UserRepository;
import com.amnil.invbackend.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    public RoleDto createRole(RoleDto roleDto) {
        log.info("inside role creation method.");
        if (roleRepository.existsByName(roleDto.getName())) {
            throw new EntityNotFoundException("Role already exists.");
        }

        // Create the new role
        Role role = new Role();
        role.setName(roleDto.getName());
        roleRepository.save(role);
        log.info("role created with :{}",role.getName());
        return roleDto;
    }

    public void assignRoleToUser(Long userId, String roleName) {

        LocalUser user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found."));

        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new EntityNotFoundException("Role not found."));

        user.getRoles().add(role);

        // Update the user in the database
        userRepository.save(user);
    }
}
