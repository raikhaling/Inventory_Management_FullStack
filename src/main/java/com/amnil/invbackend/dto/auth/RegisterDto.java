package com.amnil.invbackend.dto.auth;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto{
    private String name;
    private String username;
    private String email;
    private String password;
    private String address;
    private String phoneNumber;
    private Boolean active ;
    private LocalDateTime date;

}
