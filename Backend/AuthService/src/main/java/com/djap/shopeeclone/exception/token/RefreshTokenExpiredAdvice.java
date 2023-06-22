package com.djap.shopeeclone.exception.token;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RefreshTokenExpiredAdvice {
    @ResponseBody
    @ExceptionHandler(RefreshTokenExpiredException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String RefreshTokenExpiredAdviceHandler(RefreshTokenExpiredException ex){
        return ex.getMessage();
    }
}
