package com.hirecars.hire.core.exceptions;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ExceptionResponse {
    private final HttpStatus httpStatus;
    private final String message;
    private final LocalDateTime timeStamp;

    public ExceptionResponse(HttpStatus httpStatus, String message, LocalDateTime timeStamp) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.timeStamp = timeStamp;
    }
}
