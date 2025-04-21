package com.vaghani.securityapplication.spring_security_application.advice;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ApiError {

    private LocalDateTime timeStamp;

    private String error;

    private List<String> subErrors;

    private HttpStatus statusCode;

    public ApiError() {
        this.timeStamp = LocalDateTime.now();
    }

    public ApiError(String error, HttpStatus statusCode, List<String> subErrors) {
        this();
        this.error = error;
        this.statusCode = statusCode;
        this.subErrors = subErrors;
    }


}
