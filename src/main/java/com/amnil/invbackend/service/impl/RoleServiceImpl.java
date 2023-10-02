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
        if (roleRepository.existsByName(roleDto.getRoleName())) {
            throw new EntityNotFoundException("Role already exists.");
        }

        // Create the new role
        Role role = new Role();
        role.setName(roleDto.getRoleName());
        roleRepository.save(role);
        log.info("role created with :{}",role.getName());
        return roleDto;
    }

    public void assignRoleToUser(RoleDto roleDto) {
        try{
            LocalUser user = userRepository.findById(roleDto.getId())
                    .orElseThrow(() -> new EntityNotFoundException("User not found."));

            Role role = roleRepository.findByName(roleDto.getRoleName())
                    .orElseThrow(() -> new EntityNotFoundException("Role not found."));

            user.getRoles().add(role);

            userRepository.save(user);
        }catch (Exception e){
            log.error("Exception : {}",e.getMessage());
            throw new EntityNotFoundException(e.getMessage());
        }
    }

    public RoleDto updateRole(Long roleId, RoleDto updatedRoleDto) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new EntityNotFoundException("Role not found."));

        role.setName(updatedRoleDto.getRoleName());

        roleRepository.save(role);

        return updatedRoleDto;
    }
}
