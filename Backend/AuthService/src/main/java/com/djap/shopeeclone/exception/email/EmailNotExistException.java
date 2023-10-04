package com.djap.shopeeclone.exception.email;

public class EmailNotExistException extends RuntimeException {
    public EmailNotExistException() {
        super("Email not exist");
    }
}
