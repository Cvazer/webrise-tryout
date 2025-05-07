package com.github.cvazer.tryout.webrise.dao.repo;

import com.github.cvazer.tryout.webrise.dao.entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepo extends JpaRepository<ServiceEntity, String> {
}
