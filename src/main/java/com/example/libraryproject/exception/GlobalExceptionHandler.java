package com.example.libraryproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException ex, WebRequest request) {
//        Map<String, String> response = new HashMap<>();
//        response.put("error", ex.getMessage());
//
//        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//    }
    // todo : ya global yada sexsi exception class ataciq heleki global ataciq

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Map<String, String>> handleGenericException(RuntimeException ex, WebRequest request) {
//        Map<String, String> response = new HashMap<>();
//        response.put("error", "Something went wrong. Please try again later.");
//        response.put("details", ex.getMessage());
//
//        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//    }
}
