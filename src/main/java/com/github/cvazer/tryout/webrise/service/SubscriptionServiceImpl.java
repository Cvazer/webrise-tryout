package com.github.cvazer.tryout.webrise.service;

import com.github.cvazer.tryout.webrise.dao.entity.SubscriptionEntity;
import com.github.cvazer.tryout.webrise.dao.entity.UserEntity;
import com.github.cvazer.tryout.webrise.dao.repo.ServiceRepo;
import com.github.cvazer.tryout.webrise.dao.repo.SubscriptionRepo;
import com.github.cvazer.tryout.webrise.service.subscriptions.SubscriptionStatus;
import com.github.cvazer.tryout.webrise.service.subscriptions.UnknownService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionRepo repo;
    private final ServiceRepo serviceRepo;

    @Override
    @Transactional
    public SubscriptionEntity createSubscription(UserEntity user, String serviceId) {
        var service = serviceRepo.findById(serviceId)
                .orElseThrow(() -> new UnknownService(serviceId));

        var sub = new SubscriptionEntity(
                null,
                SubscriptionStatus.PENDING_SERVICE_CONFIRMATION,
                new SubscriptionEntity.Identity(null, null),
                service,
                user
        );
        return repo.save(sub);
    }

    @Override
    public Optional<SubscriptionEntity> getSubscription(UserEntity user, String serviceId) {
        return repo.findById(new SubscriptionEntity.Identity(user.getId(), serviceId));
    }

}
