package com.djap.shopeeclone.exception;

public class EmailPasswordNotMatchException extends RuntimeException{
    private final String emailPasswordError;

    public EmailPasswordNotMatchException(String emailPasswordError){
        this.emailPasswordError = emailPasswordError;
    }

}
