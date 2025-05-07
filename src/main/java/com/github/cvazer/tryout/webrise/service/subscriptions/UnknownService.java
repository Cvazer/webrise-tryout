package com.github.cvazer.tryout.webrise.service.subscriptions;

import com.github.cvazer.tryout.webrise.web.ApiException;

public class UnknownService extends ApiException {

    public UnknownService(String id) {
        super(String.format("No such service with id [%s]", id));
    }

}
