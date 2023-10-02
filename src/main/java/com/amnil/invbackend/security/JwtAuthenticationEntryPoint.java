package com.amnil.invbackend.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
class CustomAuthenticationException extends AuthenticationException {
    public CustomAuthenticationException(String message) {
        super(message);
    }
}

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        // Extract the exception message
        String errorMessage = authException.getMessage();

        // Create a custom error message based on the exception
        String customErrorMessage = "Authentication failed: " + errorMessage;

        // Send the custom error message along with 401 status code
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, customErrorMessage);
    }
}
