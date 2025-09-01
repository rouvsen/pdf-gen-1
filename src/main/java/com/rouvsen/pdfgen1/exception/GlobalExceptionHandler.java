package com.rouvsen.pdfgen1.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(exception = Exception.class)
    public ResponseEntity<?> handleException(Exception exception) {
        log.error("Internal Unexpected Exception occurred: {}", exception.getMessage());
        return new ResponseEntity<>(BAD_REQUEST);
    }
}
