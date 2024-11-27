package com.datawizard.backend.datawizardbackend.controller.advice;

import com.datawizard.backend.datawizardbackend.domain.exception.APIException;
import com.datawizard.backend.datawizardbackend.domain.exception.APIInternalServerErrorException;
import com.datawizard.backend.datawizardbackend.domain.exception.APIMethodNotAllowedException;
import com.datawizard.backend.datawizardbackend.domain.exception.APINotFoundException;
import com.datawizard.backend.datawizardbackend.domain.response.APIExceptionResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
@ResponseBody
public class ControllerExceptionHandler {

    Log logger = LogFactory.getLog(ControllerExceptionHandler.class);

    @ExceptionHandler(APIException.class)
    public ResponseEntity<APIExceptionResponse> apiExceptionHandler(APIException ex, @SuppressWarnings("unused") WebRequest request) {
        APIException exception = ex;
        if(exception == null){
            exception = new APIInternalServerErrorException();
        }

        return new ResponseEntity<>(new APIExceptionResponse(exception), exception.getStatus());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    public APIExceptionResponse methodNotSupportedExceptionHandler(@SuppressWarnings("unused") Exception ex, @SuppressWarnings("unused") WebRequest request) {
        return new APIExceptionResponse(new APIMethodNotAllowedException());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public APIExceptionResponse noHandlerFoundExceptionHandler(@SuppressWarnings("unused") Exception ex, @SuppressWarnings("unused") WebRequest request) {
        return new APIExceptionResponse(new APINotFoundException());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public APIExceptionResponse defaultExceptionHandler(Exception ex, @SuppressWarnings("unused") WebRequest request) {
        logger.error("Uncaught exception handled by default exception handler.", ex);
        return new APIExceptionResponse(new APIInternalServerErrorException());
    }
}

