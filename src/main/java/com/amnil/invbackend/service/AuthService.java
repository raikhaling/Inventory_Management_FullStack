package com.amnil.invbackend.service;

import com.amnil.invbackend.dto.auth.LoginDto;
import com.amnil.invbackend.dto.auth.RegisterDto;

public interface AuthService {
    String register (RegisterDto registerDto);
    String login (LoginDto loginDto);
}
