package com.amnil.invbackend.security;

import com.amnil.invbackend.entity.LocalUser;
import com.amnil.invbackend.exception.EntityNotFoundException;
import com.amnil.invbackend.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        log.info("Attempting to load user by email: {}", usernameOrEmail);

        LocalUser user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(()-> new EntityNotFoundException("User does not exist by Username or Password."));
//        LocalUser user = userRepository.findByEmail(email)
//                .orElseThrow(()-> new EntityNotFoundException("User does not exist by Email ."));
        log.info("Found user: {}", user.getUsername());


        Set<GrantedAuthority> authoritySet = user.getRoles().stream()
                .map((role) -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());

        return new User(
                usernameOrEmail,
                user.getPassword(),
                authoritySet
        );
    }
}
