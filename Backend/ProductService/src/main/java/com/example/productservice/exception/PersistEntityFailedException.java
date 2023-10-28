package com.example.productservice.exception;

public class PersistEntityFailedException extends RuntimeException {
    public PersistEntityFailedException(){
        super("Save data failed.");
    }
}
