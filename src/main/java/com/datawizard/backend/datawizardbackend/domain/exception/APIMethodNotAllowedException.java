package com.datawizard.backend.datawizardbackend.domain.exception;

import org.springframework.http.HttpStatus;

public class APIMethodNotAllowedException extends APIException{
    public APIMethodNotAllowedException() {
        super("Request method is not supported.", HttpStatus.METHOD_NOT_ALLOWED);
    }
    public APIMethodNotAllowedException(String message) {
        super(message, HttpStatus.METHOD_NOT_ALLOWED);
    }
    public APIMethodNotAllowedException(String message, Throwable cause) {
        super(message, cause, HttpStatus.METHOD_NOT_ALLOWED);
    }
}
