package com.hirecars.hire.core.exceptions;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class ExceptionResponse {
    @Schema(name = "httpStatus", type = "number", example = "500")
    private final HttpStatus httpStatus;

    @Schema(name = "message", type = "string", example = "Internal server error")
    private final String message;

    @Schema(name = "timeStamp", type = "string")
    private final LocalDateTime timeStamp;

    public ExceptionResponse(HttpStatus httpStatus, String message, LocalDateTime timeStamp) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.timeStamp = timeStamp;
    }
}
