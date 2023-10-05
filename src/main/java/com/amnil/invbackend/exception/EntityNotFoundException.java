package com.amnil.invbackend.exception;

import lombok.NoArgsConstructor;

/**
 * The type Entity not found exception.
 */
@NoArgsConstructor
public class EntityNotFoundException extends RuntimeException{
    /**
     * Instantiates a new Entity not found exception.
     *
     * @param message the message
     */
    public EntityNotFoundException(String message){
        super(message);
    }
}

