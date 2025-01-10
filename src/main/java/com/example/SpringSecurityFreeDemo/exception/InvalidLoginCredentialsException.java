package com.example.SpringSecurityFreeDemo.exception;

import java.io.Serial;

public class InvalidLoginCredentialsException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidLoginCredentialsException(String message) {
        super(message);
    }
}
