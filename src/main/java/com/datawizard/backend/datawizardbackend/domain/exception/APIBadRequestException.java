package com.datawizard.backend.datawizardbackend.domain.exception;

import org.springframework.http.HttpStatus;

public class APIBadRequestException extends APIException{
    public APIBadRequestException() {
        super("Bad request.", HttpStatus.BAD_REQUEST);
    }
    public APIBadRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
    public APIBadRequestException(String message, Throwable cause) {
        super(message, cause, HttpStatus.BAD_REQUEST);
    }
}
