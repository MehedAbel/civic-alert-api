package com.example.CivicAlert.exception.auth;

import java.io.Serial;

public class ReportNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public ReportNotFoundException(String message) {
        super(message);
    }
}
