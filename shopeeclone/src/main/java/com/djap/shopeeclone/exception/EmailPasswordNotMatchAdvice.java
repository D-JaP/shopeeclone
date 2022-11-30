package com.djap.shopeeclone.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EmailPasswordNotMatchAdvice {
    @ResponseBody
    @ExceptionHandler(EmailPasswordNotMatchException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String emailPasswordNotMatchHandler(EmailPasswordNotMatchException ex){
        return ex.getMessage();
    }
}
