package com.datawizard.backend.datawizardbackend.domain.exception;

import org.springframework.http.HttpStatus;

public class APINotFoundException extends APIException{
    public APINotFoundException() {
        super("Resource not found.", HttpStatus.NOT_FOUND);
    }
    public APINotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
    public APINotFoundException(String message, Throwable cause) {
        super(message, cause, HttpStatus.NOT_FOUND);
    }
}
