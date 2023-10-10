package com.amnil.invbackend.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import com.amnil.invbackend.dto.auth.JwtAuthResponse;
import com.amnil.invbackend.dto.auth.LoginDto;
import com.amnil.invbackend.dto.auth.RegisterDto;
import com.amnil.invbackend.service.AuthService;
import com.amnil.invbackend.utils.StringResponseWithLinks;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Authentication controller.
 */
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@Slf4j
public class AuthController {
    private AuthService authService;
    private StringResponseWithLinks responseWithLinks;

    /**
     * Register response entity.
     *
     * @param registerDto the register dto
     * @return the response entity
     */
    @PostMapping("/register")
    public ResponseEntity<StringResponseWithLinks> register(@RequestBody RegisterDto registerDto){
        log.info("Attempting to register in with email: {}", registerDto.getEmail());
        String response = authService.register(registerDto);

        //Add links to response
        Link selfLink = linkTo(AuthController.class).slash("/login").withSelfRel();

        responseWithLinks.setMessage(response);
        responseWithLinks.add(selfLink);

        return new ResponseEntity<>(responseWithLinks, HttpStatus.CREATED);
    }

    /**
     * Login response entity.
     *
     * @param loginDto the login dto
     * @return the response entity
     */
//Login Api
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@Valid @RequestBody LoginDto loginDto){
        log.info("Attempting to log in with email: {}", loginDto.getUsernameOrEmail());

        String token = authService.login(loginDto);

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }
}
