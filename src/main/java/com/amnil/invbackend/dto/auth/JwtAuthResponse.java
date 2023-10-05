package com.amnil.invbackend.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Jwt auth response.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthResponse {
    /**
     * accessToken
     */
    private String accessToken;
    /**
     * tokenType
     */
    private String tokenType = "Bearer";
}
