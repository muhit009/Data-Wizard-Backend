package com.datawizard.backend.datawizardbackend.domain.exception;

import org.springframework.http.HttpStatus;

public class APINotImplementedException extends APIException{
    public APINotImplementedException() {
        super("Endpoint has not been implemented.", HttpStatus.NOT_IMPLEMENTED);
    }
    public APINotImplementedException(String message) {
        super(message, HttpStatus.NOT_IMPLEMENTED);
    }
    public APINotImplementedException(String message, Throwable cause) {
        super(message, cause, HttpStatus.NOT_IMPLEMENTED);
    }
}
