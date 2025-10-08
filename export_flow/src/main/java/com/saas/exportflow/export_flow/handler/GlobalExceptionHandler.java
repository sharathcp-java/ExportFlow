package com.saas.exportflow.export_flow.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    // Handles exceptions from the authentication process (e.g., in AuthController)
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException ex) {
        // The AOP layer already logged the exception, this just formats the response.
        return new ResponseEntity<>("Invalid credentials: " + ex.getMessage(), HttpStatus.UNAUTHORIZED); // 401
    }

    // Handles any other unexpected runtime exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        // The AOP layer already logged the exception, this just formats the response.
        return new ResponseEntity<>("An internal error occurred. Please try again later.", HttpStatus.INTERNAL_SERVER_ERROR); // 500
    }
}