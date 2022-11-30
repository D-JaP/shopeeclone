package com.djap.shopeeclone.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EmailUsedExceptionAdvice {
    @ResponseBody
    @ExceptionHandler(EmailUsedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String EmailUsedExceptionHandler(EmailUsedException ex){
        return ex.getMessage();
    }
}
