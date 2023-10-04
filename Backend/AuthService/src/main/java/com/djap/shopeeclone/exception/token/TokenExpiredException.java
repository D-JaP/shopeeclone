package com.djap.shopeeclone.exception.token;

public class TokenExpiredException extends RuntimeException {
    public TokenExpiredException() {
        super("Token Expired");
    }
}
