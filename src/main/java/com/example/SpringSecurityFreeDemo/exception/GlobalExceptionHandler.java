package com.example.SpringSecurityFreeDemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorObject> handleUserAlreadyExistsException(UserAlreadyExistsException ex, WebRequest request) {
        ErrorObject errorObject = new ErrorObject();
        errorObject.setMessage(ex.getMessage());
        errorObject.setStatusCode(HttpStatus.CONFLICT.value());
        errorObject.setTimestamp(new Date());

        return new ResponseEntity<>(errorObject, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidRegisterCredentialsException.class)
    public ResponseEntity<ErrorObject> handleInvalidRegisterCredentialsException(InvalidRegisterCredentialsException ex, WebRequest request) {
        ErrorObject errorObject = new ErrorObject();
        errorObject.setMessage(ex.getMessage());
        errorObject.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorObject.setTimestamp(new Date());

        return new ResponseEntity<>(errorObject, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidLoginCredentialsException.class)
    public ResponseEntity<ErrorObject> handleInvalidLoginCredentialsException(InvalidLoginCredentialsException ex, WebRequest request) {
        ErrorObject errorObject = new ErrorObject();
        errorObject.setMessage(ex.getMessage());
        errorObject.setStatusCode(HttpStatus.UNAUTHORIZED.value());
        errorObject.setTimestamp(new Date());

        return new ResponseEntity<>(errorObject, HttpStatus.UNAUTHORIZED);
    }
}
