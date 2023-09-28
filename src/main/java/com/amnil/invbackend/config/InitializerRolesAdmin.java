package com.amnil.invbackend.config;

import com.amnil.invbackend.entity.LocalUser;
import com.amnil.invbackend.entity.Role;
import com.amnil.invbackend.exception.EntityNotFoundException;
import com.amnil.invbackend.repository.RoleRepository;
import com.amnil.invbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class InitializerRolesAdmin implements CommandLineRunner {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        log.info("Role and Admin Initialization Started.");

        // Initialize roles
        initializeRoles();

        // Initialize admin user
        initializeAdmin();

        log.info("Role and Admin Initialization Completed.");
    }

    private void initializeRoles() {
        try {
            if (roleRepository.findByName("ROLE_ADMIN").isEmpty()) {
                Role adminRole = new Role();
                adminRole.setName("ROLE_ADMIN");
                roleRepository.save(adminRole);
            }

            if (roleRepository.findByName("ROLE_USER").isEmpty()) {
                Role userRole = new Role();
                userRole.setName("ROLE_USER");
                roleRepository.save(userRole);
            }
        } catch (Exception e) {
            log.error("Error occurred during role initialization: {}", e.getMessage());
        }
    }

    private void initializeAdmin() {
        try {
            if (userRepository.findByUsername("admin").isEmpty()) {
                Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                        .orElseThrow(() -> new EntityNotFoundException("Admin role not found. " +
                                "Make sure to create roles."));

                LocalUser adminUser = new LocalUser();
                adminUser.setName("Admin");
                adminUser.setUsername("admin");
                adminUser.setEmail("admin@example.com");
                adminUser.setPassword(passwordEncoder.encode("admin"));
                adminUser.setAddress("Admin Address");
                adminUser.setPhoneNumber("1234567890");
                adminUser.setActive(true);

                Set<Role> adminRoleSet = new HashSet<>();
                adminRoleSet.add(adminRole);
                adminUser.setRoles(adminRoleSet);

                userRepository.save(adminUser);
                log.info("Admin Initialization Completed.");
            }
        } catch (Exception e) {
            log.error("Error occurred during admin initialization: {}", e.getMessage());
        }
    }
}