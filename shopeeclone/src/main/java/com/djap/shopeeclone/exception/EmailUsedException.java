package com.djap.shopeeclone.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class EmailUsedException extends RuntimeException {
    public EmailUsedException(String email){
        super("Could not find email: " + email);
    }
}
