package com.example.CivicAlert.exception.auth;

import java.io.Serial;

public class InvalidRegisterCredentialsException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidRegisterCredentialsException(String message) {
        super(message);
    }
}
