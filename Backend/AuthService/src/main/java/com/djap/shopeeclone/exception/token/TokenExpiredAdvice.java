package com.djap.shopeeclone.exception.token;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TokenExpiredAdvice {
    @ResponseBody
    @ExceptionHandler(TokenExpiredException.class)
    @ResponseStatus(HttpStatus.OK)
    public String tokenExpiredExceptionHandler(TokenExpiredException ex) {
        return ex.getMessage();
    }
}
