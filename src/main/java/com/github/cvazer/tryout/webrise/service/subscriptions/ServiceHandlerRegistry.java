package com.github.cvazer.tryout.webrise.service.subscriptions;

/**
 * Acts as a registry for all subscription services handlers
 */
public interface ServiceHandlerRegistry {

    /**
     * @return {@link SubscriptionServiceHandler} corresponding to given service id
     * @throws UnknownService when no handler is found dor given service id
     */
    SubscriptionServiceHandler get(String serviceId) throws UnknownService;

}
