package com.dancode.onlinestore.exceptions;

import java.util.stream.Collectors;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CustomErrorDetails argumentNotValid(MethodArgumentNotValidException ex, WebRequest request){
        var errors = ex.getAllErrors().stream().map(e->e.getDefaultMessage()).collect(Collectors.toList()).toString();
        return new CustomErrorDetails(errors);
    }


    // @ExceptionHandler(ConstraintViolationException.class)
    // public CustomErrorDetails contrainViolationException(ConstraintViolationException ex, WebRequest request){
    //     var errors = ex.getConstraintViolations().stream().map(e->e.getMessage()).collect(Collectors.toList()).toString();
    //     return new CustomErrorDetails(errors);
    // }

}
