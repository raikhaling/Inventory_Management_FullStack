package com.amnil.invbackend.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @NotBlank(message = "Enter your name.")
    private String name;
    /**
     * username
     */
    @NotBlank(message = "Enter your username.")
    private String username;
    /**
     * email
     */
    @NotBlank(message = "Enter your email.")
    @Email(message = "Invalid email format")
    private String email;
    /**
     * password
     */
    @NotBlank(message = "Enter your password.")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;
    /**
     * address
     */
    @NotBlank(message = "Enter your address.")
    private String address;
    /**
     * phoneNumber
     */
    @NotBlank(message = "Enter your phone number.")
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
