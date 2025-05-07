package com.github.cvazer.tryout.webrise.service;

import com.github.cvazer.tryout.webrise.dao.entity.UserEntity;

public interface UserService {

    /**
     * <p>Creates new user with given display name.</p>
     * @param displayName display name of created user
     * @return already persisted {@link UserEntity}
     */
    UserEntity create(String displayName);

    /**
     * <p>Attempts to fetch user using data in
     * {@link org.springframework.security.core.context.SecurityContextHolder}<p/>
     * @throws IllegalStateException when no security context or authentication exists, or it is not supported
     */
    UserEntity getCurrentUser() throws IllegalStateException;
}
