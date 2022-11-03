package com.djap.shopeeclone.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class EmailUsedException extends RuntimeException {
    private final String emailUsedException;
}
