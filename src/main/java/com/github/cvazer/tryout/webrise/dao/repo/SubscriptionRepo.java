package com.github.cvazer.tryout.webrise.dao.repo;

import com.github.cvazer.tryout.webrise.dao.entity.SubscriptionEntity;
import com.github.cvazer.tryout.webrise.service.subscriptions.SubscriptionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Set;

@Repository
public interface SubscriptionRepo extends JpaRepository<SubscriptionEntity, SubscriptionEntity.Identity> {

    Set<SubscriptionEntity> findAllByStatus(SubscriptionStatus status);
    Set<SubscriptionEntity> findAllByStatusAndRenewDueDateBefore(SubscriptionStatus status, LocalDateTime limit);
    void deleteAllByStatusAndRenewDueDateBefore(SubscriptionStatus status, LocalDateTime limit);

}
