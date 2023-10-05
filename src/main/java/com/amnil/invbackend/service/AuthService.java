package com.amnil.invbackend.service;

import com.amnil.invbackend.dto.auth.LoginDto;
import com.amnil.invbackend.dto.auth.RegisterDto;

/**
 * The interface Auth service.
 */
public interface AuthService {
    /**
     * Register string.
     *
     * @param registerDto the register dto
     * @return the string
     */
    String register (RegisterDto registerDto);

    /**
     * Login string.
     *
     * @param loginDto the login dto
     * @return the string
     */
    String login (LoginDto loginDto);
}
