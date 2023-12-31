package com.amnil.invbackend.dto.core;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

/**
 * The type User dto.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends RepresentationModel<UserDto> {
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
