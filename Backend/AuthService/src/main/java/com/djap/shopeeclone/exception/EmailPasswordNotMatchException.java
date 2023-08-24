package com.djap.shopeeclone.exception;

import org.springframework.security.core.AuthenticationException;

public class EmailPasswordNotMatchException extends AuthenticationException {
    public EmailPasswordNotMatchException() {
        super("Email/Password not match.");
    }

}
