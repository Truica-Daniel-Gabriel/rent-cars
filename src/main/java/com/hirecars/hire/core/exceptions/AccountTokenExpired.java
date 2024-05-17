package com.hirecars.hire.core.exceptions;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AccountTokenExpired extends RuntimeException{
    private String message;
    private LocalDateTime timestamp;


    public AccountTokenExpired(String message) {
        super(message);
        timestamp = LocalDateTime.now();
    }
}
