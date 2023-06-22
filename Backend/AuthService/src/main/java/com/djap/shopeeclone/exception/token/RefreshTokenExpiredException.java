package com.djap.shopeeclone.exception.token;

public class RefreshTokenExpiredException extends RuntimeException{
    public RefreshTokenExpiredException() {
        super("Refresh token expired. Re-loggin is required.");
    }
}
