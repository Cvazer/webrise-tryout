package com.github.cvazer.tryout.webrise.service.subscriptions;

import com.github.cvazer.tryout.webrise.dao.entity.UserEntity;

public interface SubscriptionServiceHandler {

    /**
     * Async method that subscribes user to this service
     * @param user whom will be subscribed
     */
    void subscribe(UserEntity user);

    /**
     * Async method that unsubscribes user from given service
     * @param user for who subscription will be stopped
     * @return {@code true} if user was subscribed before the call, {@code false} otherwise
     */
    boolean unsubscribe(UserEntity user);

    /**
     * @return ID of a service that is handled by this handler
     */
    String getServiceId();

}
