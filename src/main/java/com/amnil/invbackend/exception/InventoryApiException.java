package com.amnil.invbackend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class InventoryApiException extends RuntimeException{
    private HttpStatus status;
    private String message;
}
