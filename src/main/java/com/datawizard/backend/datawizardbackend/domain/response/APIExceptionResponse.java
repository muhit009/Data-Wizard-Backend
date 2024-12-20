package com.datawizard.backend.datawizardbackend.domain.response;

import com.datawizard.backend.datawizardbackend.domain.exception.APIException;

public class APIExceptionResponse {
    private String message;
    private int statusCode;
    private String status;

    public APIExceptionResponse(APIException apiException){
        this.message = apiException.getMessage();
        this.statusCode = apiException.getStatus().value();
        this.status = apiException.getStatus().name();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
