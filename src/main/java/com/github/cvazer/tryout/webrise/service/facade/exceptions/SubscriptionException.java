package com.github.cvazer.tryout.webrise.service.facade.exceptions;

import com.github.cvazer.tryout.webrise.web.ApiException;

public class SubscriptionException extends ApiException {
    public SubscriptionException(String message) {
        this(message, false);
    }

    public SubscriptionException(String message, boolean print) {
        super(message);
        this.print = print;
    }
}
