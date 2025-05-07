package com.github.cvazer.tryout.webrise.web;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {
    protected boolean print = true;

    public ApiException(String message) {
        super(message);
    }

    public ApiException() {
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }
}
