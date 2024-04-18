package com.hirecars.hire.controllers;

import com.hirecars.hire.core.exceptions.AccountTokenExpired;
import com.hirecars.hire.core.exceptions.BusinessException;
import com.hirecars.hire.core.exceptions.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handlerInvalidRequest(MethodArgumentNotValidException e) {
        Map<String, String> errorMap = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = AccountTokenExpired.class)
    public void handlerAccountTokenExpired(AccountTokenExpired e) {

    }

    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<ExceptionResponse> handlerBusinessException(BusinessException e) {
        ExceptionResponse apiException = new ExceptionResponse(
                e.getHttpStatus(),
                e.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(apiException, e.getHttpStatus());
    }
}
