package com.amnil.invbackend.service.impl;

import com.amnil.invbackend.dto.auth.LoginDto;
import com.amnil.invbackend.dto.auth.RegisterDto;
import com.amnil.invbackend.entity.LocalUser;
import com.amnil.invbackend.entity.Role;
import com.amnil.invbackend.exception.EntityNotFoundException;
import com.amnil.invbackend.exception.InventoryApiException;
import com.amnil.invbackend.repository.RoleRepository;
import com.amnil.invbackend.repository.UserRepository;
import com.amnil.invbackend.security.JwtTokenProvider;
import com.amnil.invbackend.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public String register(RegisterDto registerDto) {

        // check username is already exists in database
        if(userRepository.existsByUsername(registerDto.getUsername())){
            throw new InventoryApiException(HttpStatus.BAD_REQUEST, "Username already exists!");
        }

        // check email is already exists in database
        if(userRepository.existsByEmail(registerDto.getEmail())){
            throw new InventoryApiException(HttpStatus.BAD_REQUEST, "Email is already exists!.");
        }

        try {
            LocalUser user = new LocalUser();
            user.setName(registerDto.getName());
            user.setUsername(registerDto.getUsername());
            user.setEmail(registerDto.getEmail());
            user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
            user.setAddress(registerDto.getAddress());
            user.setActive(true);
            user.setPhoneNumber(registerDto.getPhoneNumber());
            user.setDate(LocalDateTime.now());

            Set<Role> roles = new HashSet<>();
            Role userRole = roleRepository.findByName("ROLE_USER")
                    .orElseThrow(()-> new EntityNotFoundException("Role is not found."));
            log.info("user created with role : {} name : {}",userRole.getName(),user.getName());
            roles.add(userRole);

            user.setRoles(roles);


            userRepository.save(user);


        }catch(Exception e){
            log.info("Exception occured : {}",e.getMessage());
        }
        return "User Registered Successfully! Please proceed to login.";
    }

    @Override
    public String login(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(),
                loginDto.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return token;
    }
}
