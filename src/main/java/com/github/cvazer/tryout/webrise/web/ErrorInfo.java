package com.github.cvazer.tryout.webrise.web;

public record ErrorInfo(
        int code,
        String description
) {
    public static final int OK_CODE = 0;
    public static final int DEFAULT_ERROR_CODE = 1;

    public ErrorInfo() {
        this(OK_CODE, null);
    }

    public ErrorInfo(Throwable ex) {
        this(DEFAULT_ERROR_CODE, ex.getMessage());
    }
}
