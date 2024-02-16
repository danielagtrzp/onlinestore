package com.dancode.onlinestore.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GeneralCustomApplicationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CustomErrorDetails argumentNotValid(GeneralCustomApplicationException ex, WebRequest request){
        var errorsMessage = ex.getMessage();
        var stackTrace = ex.getStackTrace();
        var uri = ((ServletWebRequest) request).getRequest().getRequestURI();
        return new CustomErrorDetails(uri,errorsMessage,stackTrace);
    }

}
