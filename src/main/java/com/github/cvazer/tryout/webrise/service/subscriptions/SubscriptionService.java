package com.github.cvazer.tryout.webrise.service.subscriptions;

import com.github.cvazer.tryout.webrise.dao.entity.ServiceEntity;
import com.github.cvazer.tryout.webrise.dao.entity.SubscriptionEntity;
import com.github.cvazer.tryout.webrise.dao.entity.UserEntity;

import java.util.Optional;

public interface SubscriptionService {

    /**
     * @param user for whom subscription record should be created
     * @param serviceId {@link String} id of a service
     * @return persisted {@link ServiceEntity} record
     */
    SubscriptionEntity createSubscription(UserEntity user, String serviceId);

    /**
     * @return {@link SubscriptionEntity} corresponding to given user and service or empty optional
     */
    Optional<SubscriptionEntity> getSubscription(UserEntity user, String serviceId);

    /**
     * Only returns subscription if it exists AND it is in "Active" status
     * @return {@link SubscriptionEntity} corresponding to given user and service or empty optional
     */
    Optional<SubscriptionEntity> getActiveSubscription(UserEntity user, String serviceId);

}
