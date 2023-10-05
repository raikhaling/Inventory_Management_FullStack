package com.amnil.invbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Api response.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
    /**
     * message
     */
    private String message;
    /**
     * success
     */
    private boolean success;
}
