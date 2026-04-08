package com.microservice.RatingService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleResourceNotFoundException(final ResourceNotFoundException ex){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("message", ex.getMessage());
        errorMap.put("success", String.valueOf(false));
        errorMap.put("status", String.valueOf(HttpStatus.NOT_FOUND));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMap);
    }
}
