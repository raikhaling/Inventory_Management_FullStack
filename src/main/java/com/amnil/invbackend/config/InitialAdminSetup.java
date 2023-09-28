//package com.amnil.invbackend.config;
//
//import com.amnil.invbackend.entity.LocalUser;
//import com.amnil.invbackend.entity.Role;
//import com.amnil.invbackend.exception.EntityNotFoundException;
//import com.amnil.invbackend.repository.RoleRepository;
//import com.amnil.invbackend.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.DependsOn;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.util.Collections;
//import java.util.HashSet;
//import java.util.Set;
//
////@DependsOn("initialRoleSetup") telling Spring to ensure that the InitialAdminSetup bean is created
////        only after the initialRoleSetup bean has been initialized.
//
//@DependsOn("initialRoleSetup")
//@Slf4j
//public class InitialAdminSetup {
//    private final UserRepository userRepository;
//    private final RoleRepository roleRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    @Autowired
//    public InitialAdminSetup(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.roleRepository = roleRepository;
//        this.passwordEncoder = passwordEncoder;
//        log.info("Admin setup starting after initializing roles.");
//        initializeAdmin();
//
//    }
//    private void initializeAdmin(){
//      try {
//          if(userRepository.findByUsername("admin").isEmpty()) {
//              Role adminRole = roleRepository.findByName("ROLE_ADMIN")
//                      .orElseThrow(() -> new EntityNotFoundException("Admin role not found. " +
//                              "Make sure to create roles."));
//
//              LocalUser addAdmin = new LocalUser();
//              addAdmin.setName("admin");
//              addAdmin.setUsername("admin");
//              addAdmin.setEmail("admin@gmail.com");
//              addAdmin.setPassword(passwordEncoder.encode("admin"));
//              addAdmin.setAddress("xyz");
//              addAdmin.setPhoneNumber("9834546575");
//              addAdmin.setActive(true);
//              // Merge the adminRole to reattach it to the session
//
//              // Ensure that the Role entity is managed by JPA
//              adminRole = roleRepository.save(adminRole);
//
//
//
//              log.info("just before role setup for admin.");
//              Set<Role> adminRoleSet = new HashSet<>();
//              adminRoleSet.add(adminRole);
//              addAdmin.setRoles(adminRoleSet);
//
//                addAdmin.setRoles(Collections.singleton(adminRole));
//
//              userRepository.save(addAdmin);
//              log.info("Initializing of admin finished.");
//
//          }
//      }catch (Exception e){
//          log.error("Error occurred during admin creation: {}", e.getMessage());
//      }
//    }
//}
