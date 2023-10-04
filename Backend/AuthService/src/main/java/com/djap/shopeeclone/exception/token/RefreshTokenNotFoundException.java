package com.djap.shopeeclone.exception.token;

public class RefreshTokenNotFoundException extends RuntimeException {
    public RefreshTokenNotFoundException() {
        super("Token does not exist");
    }
}
