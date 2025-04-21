package com.vaghani.securityapplication.spring_security_application.advice;

import com.vaghani.securityapplication.spring_security_application.exceptions.RequiredFieldMissingException;
import com.vaghani.securityapplication.spring_security_application.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleResourceNotFoundException(ResourceNotFoundException exception) {
        ApiError apiError = new ApiError(exception.getMessage(), HttpStatus.NOT_FOUND, null);
        return buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(RequiredFieldMissingException.class)
    public ResponseEntity<ApiResponse<?>> handleRequiredFieldMissingException(RequiredFieldMissingException exception) {
        ApiError apiError = new ApiError(exception.getMessage(), HttpStatus.BAD_REQUEST, exception.getSubErrors());
        return buildErrorResponseEntity(apiError);
    }

    private ResponseEntity<ApiResponse<?>> buildErrorResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(new ApiResponse<>(apiError), apiError.getStatusCode());
    }

}
