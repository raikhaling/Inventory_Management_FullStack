package com.amnil.invbackend.controller;

import com.amnil.invbackend.dto.auth.JwtAuthResponse;
import com.amnil.invbackend.dto.auth.LoginDto;
import com.amnil.invbackend.dto.auth.RegisterDto;
import com.amnil.invbackend.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@Slf4j
public class AuthController {
    private AuthService authService;


   
   
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        log.info("Attempting to register in with email: {}", registerDto.getEmail());
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    //Login Api
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto){
        log.info("Attempting to log in with email: {}", loginDto.getUsernameOrEmail());

        String token = authService.login(loginDto);

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }
}
