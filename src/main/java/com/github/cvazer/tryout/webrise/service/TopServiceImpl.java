package com.github.cvazer.tryout.webrise.service;

import com.github.cvazer.tryout.webrise.dao.entity.ServiceEntity;
import com.github.cvazer.tryout.webrise.dao.repo.ServiceRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Limit;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TopServiceImpl implements TopService {

    private final ServiceRepo serviceRepo;

    public TopServiceImpl(ServiceRepo serviceRepo) {
        this.serviceRepo = serviceRepo;
    }

    @Override
    public Set<String> threePopular() {
        return serviceRepo.findTop(Limit.of(3))
                .stream()
                .map(ServiceEntity::getName)
                .collect(Collectors.toSet());
    }

    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.MINUTES)
    @CacheEvict(value="top", allEntries=true)
    public void evictCache() {
        log.trace("Evicting [top] cache by timeout");
    }
}
