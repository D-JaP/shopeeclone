package com.djap.shopeeclone.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ApiRequestException extends RuntimeException{
    private final String apiRequestError;
}