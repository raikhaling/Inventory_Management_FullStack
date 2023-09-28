package com.amnil.invbackend.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;

    private String name;

    private String username;

    private String email;

   // private String password;

    private String address;

    private String phoneNumber;

    private Boolean active ;

    private LocalDateTime date;
}
