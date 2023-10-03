package com.amnil.invbackend.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.amnil.invbackend.dto.StringResponseWithLinks;
import com.amnil.invbackend.dto.auth.JwtAuthResponse;
import com.amnil.invbackend.dto.auth.LoginDto;
import com.amnil.invbackend.dto.auth.RegisterDto;
import com.amnil.invbackend.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthService authService;
    private final StringResponseWithLinks responseWithLinks;

    //Register Api
    @PostMapping("/register")
    public ResponseEntity<StringResponseWithLinks> register(@RequestBody RegisterDto registerDto){
        log.info("Attempting to register in with email: {}", registerDto.getEmail());
        String response = authService.register(registerDto);

        //Add links to response
        Link selfLink = linkTo(AuthController.class).slash("/login").withSelfRel();

        responseWithLinks.setMessage(response);
        responseWithLinks.add(selfLink);

        return ResponseEntity.ok(responseWithLinks);
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
