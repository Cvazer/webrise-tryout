package com.github.cvazer.tryout.webrise.service.subscriptions.handlers;

import com.github.cvazer.tryout.webrise.dao.entity.UserEntity;
import com.github.cvazer.tryout.webrise.dao.repo.SubscriptionRepo;
import com.github.cvazer.tryout.webrise.service.subscriptions.SubscriptionService;
import com.github.cvazer.tryout.webrise.service.subscriptions.SubscriptionServiceHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.concurrent.ForkJoinPool;

import static com.github.cvazer.tryout.webrise.service.subscriptions.SubscriptionStatus.CANCELED;
import static com.github.cvazer.tryout.webrise.service.subscriptions.SubscriptionStatus.SUBSCRIBED;

@Slf4j
@RequiredArgsConstructor
public abstract class AbstractSubscriptionServiceHandler implements SubscriptionServiceHandler {
    private final SubscriptionService subscriptionService;
    private final SubscriptionRepo subscriptionRepo;

    public static final int DELAY = 5;

    @Override
    public void subscribe(UserEntity user) {
        ForkJoinPool.commonPool().execute(() -> asyncSubscribe(user));
    }

    @Override
    public boolean unsubscribe(UserEntity user) {
        var isSubscribed = user.getSubscriptions().stream()
                .anyMatch(it -> it.getId().serviceId().equals(getServiceId()));

        if (isSubscribed) {
            ForkJoinPool.commonPool().execute(() -> asyncUnsubscribe(user));
            return true;
        } else {
            return false;
        }
    }

    protected void asyncSubscribe(UserEntity user) {
        log.info("Doing async subscription process for user [{}:{}] service [{}]...",
                user.getId(), user.getDisplayName(), getServiceId());

        //Симулирую запрос на API сервиса
        try {
            Thread.sleep(30 * 1000);
        } catch (InterruptedException ignored) {
            //ignore
        }

        var sub = subscriptionService
                .getSubscription(user, getServiceId())
                .orElseThrow(() -> new IllegalStateException(String.format(
                        "Unable to mark subscription as [%s] for user [%d:%s], sub is null!",
                        SUBSCRIBED.name(), user.getId(), user.getDisplayName()
                )));

        sub.setStatus(SUBSCRIBED);
        sub.setRenewDueDate(LocalDateTime.now().plusMinutes(DELAY));
        subscriptionRepo.save(sub);
    }

    protected void asyncUnsubscribe(UserEntity user) {
        log.info("Doing subscription cancellation process for user [{}:{}] service [{}]...",
                user.getId(), user.getDisplayName(), getServiceId());

        //Симулирую запрос на API сервиса
        try {
            Thread.sleep(30 * 1000);
        } catch (InterruptedException ignored) {
            //ignore
        }

        var sub = subscriptionService
                .getSubscription(user, getServiceId())
                .orElseThrow(() -> new IllegalStateException(String.format(
                        "Unable to mark subscription as [%s] for user [%d:%s], sub is null!",
                        CANCELED.name(), user.getId(), user.getDisplayName()
                )));

        sub.setRenewDueDate(null);
        sub.setStatus(CANCELED);
        subscriptionRepo.save(sub);

        log.info("User [{}:{}] is now SUBSCRIBED to service [{}]...",
                user.getId(), user.getDisplayName(), getServiceId());
    }

}
