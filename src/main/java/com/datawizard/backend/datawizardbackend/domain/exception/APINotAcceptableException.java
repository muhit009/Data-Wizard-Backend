package com.datawizard.backend.datawizardbackend.domain.exception;

import org.springframework.http.HttpStatus;

public class APINotAcceptableException extends APIException{
    public APINotAcceptableException() {
        super("Accept header content type not supported.", HttpStatus.NOT_ACCEPTABLE);
    }
    public APINotAcceptableException(String message) {
        super(message, HttpStatus.NOT_ACCEPTABLE);
    }
    public APINotAcceptableException(String message, Throwable cause) {
        super(message, cause, HttpStatus.NOT_ACCEPTABLE);
    }
}
