package com.github.cvazer.tryout.webrise.service.credentials;

import com.github.cvazer.tryout.webrise.web.ApiException;

public class BadCredentialsException extends ApiException {
    public BadCredentialsException() {
        super("User missing or incorrect password");
    }
}
