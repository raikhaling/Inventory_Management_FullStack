package com.amnil.invbackend.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Login dto.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {

    @NotBlank(message = "Username and Email Field should not be blank.")
    private String usernameOrEmail;

    @NotBlank(message = "Password should not be blank.")
    private String password;
}
