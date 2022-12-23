package com.djap.shopeeclone.exception.token;

public class PasswordResetTokenNotFoundException extends RuntimeException{
    public PasswordResetTokenNotFoundException(){
        super("Token does not exist.");
    }
}
