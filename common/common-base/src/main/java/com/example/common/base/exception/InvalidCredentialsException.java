package com.example.common.base.exception;

public class InvalidCredentialsException  extends RuntimeException {
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
