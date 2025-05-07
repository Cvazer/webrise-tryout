package com.github.cvazer.tryout.webrise.dao.repo;

import com.github.cvazer.tryout.webrise.dao.entity.BasicCredentialsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasicCredentialsRepo extends JpaRepository<BasicCredentialsEntity, Long> {

    Optional<BasicCredentialsEntity> findByUsername(String username);

}
