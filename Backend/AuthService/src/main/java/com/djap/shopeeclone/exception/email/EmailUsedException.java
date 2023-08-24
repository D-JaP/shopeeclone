package com.djap.shopeeclone.exception.email;

public class EmailUsedException extends RuntimeException {
    public EmailUsedException(String email){
        super("Email used.");
    }
}
