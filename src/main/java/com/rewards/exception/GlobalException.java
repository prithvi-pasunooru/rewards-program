
package com.rewards.exception;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

import java.time.LocalDateTime;

/**
 * Handles all exceptions globally.
 */
@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleNotFound(ResourceNotFoundException ex) {
        return new ResponseEntity<>(
                new ErrorResponse(LocalDateTime.now(), 404, ex.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneric(Exception ex) {
        return new ResponseEntity<>(
                new ErrorResponse(LocalDateTime.now(), 500, "Internal Server Error"),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
}
}
