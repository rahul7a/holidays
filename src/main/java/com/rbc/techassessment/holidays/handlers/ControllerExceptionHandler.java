package com.rbc.techassessment.holidays.handlers;

import java.util.Set;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
@ResponseBody
public class ControllerExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> resourceNotFoundException(ConstraintViolationException ex, WebRequest request) {
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        StringBuilder errorMsg = new StringBuilder();
        violations.forEach(violation -> {
            errorMsg.append(violation.getMessage());
        });
        return new ResponseEntity<Object>(errorMsg.toString(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> resourceNotFoundException(IllegalArgumentException ex, WebRequest request) {
        return new ResponseEntity<Object>(ex.getMessage().toString(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

}
