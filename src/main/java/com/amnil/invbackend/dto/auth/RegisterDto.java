package com.amnil.invbackend.dto.auth;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

/**
 * The type Register dto.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto extends RepresentationModel<RegisterDto> {
    /**
     * name
     */
    private String name;
    /**
     * username
     */
    private String username;
    /**
     * email
     */
    private String email;
    /**
     * password
     */
    private String password;
    /**
     * address
     */
    private String address;
    /**
     * phoneNumber
     */
    private String phoneNumber;
    /**
     * active
     */
    private Boolean active ;
    /**
     * date
     */
    private LocalDateTime date;

}
