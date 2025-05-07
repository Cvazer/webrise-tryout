package com.github.cvazer.tryout.webrise.web;

import com.fasterxml.jackson.annotation.JsonInclude;

public record ApiResponse<T>(
        ErrorInfo errorInfo, @JsonInclude(JsonInclude.Include.NON_NULL) T data
) {
    public ApiResponse(T data) {
        this(new ErrorInfo(), data);
    }

    public ApiResponse(ErrorInfo errorInfo) {
        this(errorInfo, null);
    }

    public ApiResponse(Throwable throwable) {
        this(new ErrorInfo(throwable), null);
    }
}
