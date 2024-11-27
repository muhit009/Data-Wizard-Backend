package com.datawizard.backend.datawizardbackend.domain.exception;

import org.springframework.http.HttpStatus;

public class APIInternalServerErrorException extends APIException{
    public APIInternalServerErrorException() {
        super("An internal server error has occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    public APIInternalServerErrorException(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    public APIInternalServerErrorException(String message, Throwable cause) {
        super(message, cause, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
