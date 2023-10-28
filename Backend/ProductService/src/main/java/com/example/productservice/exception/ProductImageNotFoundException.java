package com.example.productservice.exception;

public class ProductImageNotFoundException extends RuntimeException {
    public ProductImageNotFoundException() {
        super("Product image not found, failed to delete.");
    }
}
