package com.github.cvazer.tryout.webrise.service.facade.exceptions;

import com.github.cvazer.tryout.webrise.web.ApiException;

public class UserRegistrationException extends ApiException {

    public UserRegistrationException(String message, Throwable cause) {
        super(message, cause);
    }

}
