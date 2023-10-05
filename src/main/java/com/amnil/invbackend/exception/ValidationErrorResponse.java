package com.amnil.invbackend.exception;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * The type Validation error response.
 */
@Getter
@Setter
public class ValidationErrorResponse {
    private String message;
    private List<String> errors;

    /**
     * Instantiates a new Validation error response.
     *
     * @param message the message
     * @param errors  the errors
     */
    public ValidationErrorResponse(String message, List<String> errors) {
        this.message = message;
        this.errors = errors;
    }

}
