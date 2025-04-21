package com.vaghani.securityapplication.spring_security_application.exceptions;

import lombok.Data;

import java.util.List;

@Data
public class RequiredFieldMissingException extends RuntimeException {

    List<String> subErrors;

    public RequiredFieldMissingException(String message) {
        super(message);
        this.subErrors = null;
    }

    public RequiredFieldMissingException(String message, List<String> subErrors) {
        super(message);
        this.subErrors = subErrors;
    }
}
