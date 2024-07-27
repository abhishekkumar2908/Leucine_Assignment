package com.leucine.Assignment.handler;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        // Existing exception handlers
        if (e.getMessage().equals("INVALID_CREDENTIALS")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials. Please try again.");
        } else if (e.getMessage().equals("INVALID_ROLE")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid role selected.");
        } else if (e.getMessage().equals("Access Denied")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied");
        }  else if(e.getMessage().equals("JWT Token has expired")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("JWT Token has expired");
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred. Please try again later.");
        }
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<?> handleExpiredJwtException(ExpiredJwtException e) {
        if(e.getMessage().equals("JWT Token has expired"))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("JWT Token has expired");
        else
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied");
    }


}