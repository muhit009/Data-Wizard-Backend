package com.datawizard.backend.datawizardbackend.domain.exception;

import org.springframework.http.HttpStatus;

public class APIException extends RuntimeException {
    private final HttpStatus status;

    public APIException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public APIException(String message, Throwable cause, HttpStatus status) {
        super(message, cause);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
