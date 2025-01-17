package com.boatcharter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class EntityNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler (EntityNotFound.class)
    @ResponseStatus (HttpStatus.NOT_FOUND)
    public String entityNotFoundHandler (EntityNotFound ex) {
        return ex.getMessage();
    }
}
