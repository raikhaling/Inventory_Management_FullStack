package com.amnil.invbackend.exception.global;

import com.amnil.invbackend.exception.EntityNotFoundException;
import com.amnil.invbackend.exception.ErrorDetails;
import com.amnil.invbackend.exception.InventoryApiException;
import com.amnil.invbackend.exception.ValidationErrorResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The type Global exception handler.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Handel resource not found exception response entity.
     *
     * @param e the e
     * @return the response entity
     */
    /* if id not found in service controller then
    * orElseThrow method throw here and i will give response*/
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handelResourceNotFoundException(EntityNotFoundException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Hand inventory api exception response entity.
     *
     * @param e   the e
     * @param web the web
     * @return the response entity
     */
    @ExceptionHandler(InventoryApiException.class)
    public ResponseEntity<ErrorDetails> handInventoryApiException(InventoryApiException e, WebRequest web){
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                e.getMessage(),
                web.getDescription(false)
        );
        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle constraint violation exception response entity.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        List<String> errors = ex.getConstraintViolations().stream()
                .map(violation -> violation.getMessage())
                .collect(Collectors.toList());

        ValidationErrorResponse errorResponse = new ValidationErrorResponse("Validation failed", errors);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return ResponseEntity.badRequest().body(errors);
    }

}
