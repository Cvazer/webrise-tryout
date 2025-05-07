package com.github.cvazer.tryout.webrise.service.facade;

import com.github.cvazer.tryout.webrise.service.subscriptions.SubscriptionService;
import com.github.cvazer.tryout.webrise.service.users.UserService;
import com.github.cvazer.tryout.webrise.service.facade.exceptions.SubscriptionException;
import com.github.cvazer.tryout.webrise.service.subscriptions.ServiceHandlerRegistry;
import com.github.cvazer.tryout.webrise.service.subscriptions.SubscriptionMapper;
import com.github.cvazer.tryout.webrise.web.dto.SubscriptionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

import static com.github.cvazer.tryout.webrise.service.subscriptions.SubscriptionStatus.WILL_NOT_RENEW;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubscriptionsFacade {
    private final UserService userService;
    private final SubscriptionMapper mapper;
    private final ServiceHandlerRegistry serviceHandlerRegistry;
    private final SubscriptionService subscriptionService;

    public Set<SubscriptionDto> list() {
        var user = userService.getCurrentUser();
        return user.getSubscriptions().stream()
                .map(mapper::fromEntity)
                .collect(Collectors.toSet());
    }

    @Transactional
    public void subscribe(String serviceId) {
        var user = userService.getCurrentUser();
        var handler = serviceHandlerRegistry.get(serviceId);

        if (subscriptionService.getSubscription(user, serviceId).isPresent()) {
            throw new SubscriptionException("Already subscribed");
        }

        subscriptionService.createSubscription(user, serviceId);
        handler.subscribe(user);
    }

    @Transactional
    public void unsubscribe(String serviceId) {
        var user = userService.getCurrentUser();
        subscriptionService.getActiveSubscription(user, serviceId).ifPresent(sub -> {
            sub.setStatus(WILL_NOT_RENEW);
            log.info(
                    "User [{}:{}] has ended his subscription to [{}]. " +
                            "It will be marked as canceled on the next watcher cycle",
                    user.getId(), user.getDisplayName(), serviceId);
        });
    }
}
