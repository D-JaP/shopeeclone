package com.djap.shopeeclone.exception.email;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EmailNotExistAdvice {
    @ResponseBody
    @ExceptionHandler(EmailNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String EmailNotExistExceptionHandler(EmailUsedException ex){
        return ex.getMessage();
    }
}
