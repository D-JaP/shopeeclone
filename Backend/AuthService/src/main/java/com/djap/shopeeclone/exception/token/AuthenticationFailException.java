package com.djap.shopeeclone.exception.token;

public class AuthenticationFailException extends RuntimeException{
    public AuthenticationFailException(){
        super("Invalid or Expired Token");
    }
}
