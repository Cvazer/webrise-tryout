package com.github.cvazer.tryout.webrise.dao.repo;

import com.github.cvazer.tryout.webrise.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {
}
