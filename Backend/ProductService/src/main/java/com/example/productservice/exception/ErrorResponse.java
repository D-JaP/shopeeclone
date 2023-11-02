package com.example.productservice.exception;

import lombok.Data;

@Data
public class ErrorResponse {
    public String message;
    public ErrorResponse(RuntimeException ex){
        this.message = ex.getMessage();
    }
}
