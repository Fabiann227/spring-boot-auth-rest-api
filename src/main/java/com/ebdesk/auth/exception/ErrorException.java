package com.ebdesk.auth.exception;

import org.springframework.http.HttpStatus;

public class ErrorException extends RuntimeException{
    private final HttpStatus status;

    public ErrorException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
