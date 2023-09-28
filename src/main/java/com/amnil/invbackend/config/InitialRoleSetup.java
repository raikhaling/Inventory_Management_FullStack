//package com.amnil.invbackend.config;
//
//import com.amnil.invbackend.entity.Role;
//import com.amnil.invbackend.repository.RoleRepository;
//import jakarta.annotation.PostConstruct;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//
//@RequiredArgsConstructor
//@Slf4j
//public class InitialRoleSetup {
//    private final RoleRepository roleRepository;
//
//    @PostConstruct
//    public void initialize() {
//        log.info("Role setup started at the beginning.");
//        initializeRole("ROLE_ADMIN");
//        initializeRole("ROLE_USER");
//        log.info("initialization of role ended.");
//    }
//
//    private void initializeRole(String roleName) {
//        if (roleRepository.findByName(roleName).isEmpty()) {
//            Role role = new Role();
//            role.setName(roleName);
//            roleRepository.save(role);
//        }
//    }
//}
