package com.github.cvazer.tryout.webrise.service.subscriptions;

import com.github.cvazer.tryout.webrise.dao.entity.SubscriptionEntity;
import com.github.cvazer.tryout.webrise.dao.repo.SubscriptionRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import static com.github.cvazer.tryout.webrise.service.subscriptions.handlers.AbstractSubscriptionServiceHandler.DELAY;

//Мок для перевода подписок из одного статуса в другой
@Slf4j
@Service
@RequiredArgsConstructor
public class SubscriptionWatcher {
    private final SubscriptionRepo subscriptionRepo;

//    @Transactional
//    @Scheduled(fixedRate = 10, timeUnit = TimeUnit.SECONDS)
//    public void watchForPending() {
//        subscriptionRepo.findAllByStatus(SubscriptionStatus.PENDING_SERVICE_CONFIRMATION)
//                .forEach(this::setSubscribed);
//    }

    @Transactional
    @Scheduled(fixedRate = 10, timeUnit = TimeUnit.SECONDS)
    public void watchForWillNotRenew() {
        subscriptionRepo.findAllByStatus(SubscriptionStatus.WILL_NOT_RENEW)
                .forEach(this::setCanceled);
    }

    @Transactional
    @Scheduled(fixedRate = 10, timeUnit = TimeUnit.SECONDS)
    public void watchForSubscribedAndDue() {
        subscriptionRepo.findAllByStatusAndRenewDueDateBefore(
                SubscriptionStatus.SUBSCRIBED,
                LocalDateTime.now()
        ).forEach(this::paySub);
    }

    @Transactional
    @Scheduled(fixedRate = 10, timeUnit = TimeUnit.SECONDS)
    public void watchForOld() {
        subscriptionRepo.deleteAllByStatusAndRenewDueDateBefore(
                SubscriptionStatus.CANCELED,
                LocalDateTime.now()
        );
    }

    private void setSubscribed(SubscriptionEntity sub) {
        sub.setStatus(SubscriptionStatus.SUBSCRIBED);
        log.info("User [{}:{}] is now SUBSCRIBED to [{}]", sub.getUser().getId(),
                sub.getUser().getDisplayName(), sub.getService().getId());
    }

    private void setCanceled(SubscriptionEntity sub) {
        sub.setStatus(SubscriptionStatus.CANCELED);
        log.info("User's [{}:{}] subscription to [{}] is now CANCELED", sub.getUser().getId(),
                sub.getUser().getDisplayName(), sub.getService().getId());
    }

    private void paySub(SubscriptionEntity sub) {
        sub.setRenewDueDate(LocalDateTime.now().plusMinutes(DELAY));
        log.info("User's [{}:{}] subscription to [{}] has renewed now", sub.getUser().getId(),
                sub.getUser().getDisplayName(), sub.getService().getId());
    }

}
