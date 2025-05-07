package com.github.cvazer.tryout.webrise.service.subscriptions;

import com.github.cvazer.tryout.webrise.web.ApiException;

public class MultipleHandlersException extends ApiException {
    public static final String TEMPLATE = "Both handler [%s] and [%s] operate with same service ID [%s]. " +
            "Only one handler per service is allowed";

    public MultipleHandlersException(
            String serviceId,
            Class<? extends SubscriptionServiceHandler> firstHandlerClass,
            Class<? extends SubscriptionServiceHandler> secondHandlerClass
    ) {
        super(String.format(TEMPLATE, firstHandlerClass.getName(), secondHandlerClass.getName(), serviceId));
    }
}
