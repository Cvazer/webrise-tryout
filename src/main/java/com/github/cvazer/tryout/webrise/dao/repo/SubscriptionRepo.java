package com.github.cvazer.tryout.webrise.dao.repo;

import com.github.cvazer.tryout.webrise.dao.entity.SubscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepo extends JpaRepository<SubscriptionEntity, SubscriptionEntity.Identity> {
}
