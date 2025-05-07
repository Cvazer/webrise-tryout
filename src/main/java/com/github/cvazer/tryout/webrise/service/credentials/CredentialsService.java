package com.github.cvazer.tryout.webrise.service.credentials;

import com.github.cvazer.tryout.webrise.dao.entity.UserEntity;

public interface CredentialsService<T> {

    /**
     * <p>Registers new user with given credentials</p>
     * @param credentials to be used as a default basic auth method
     * @return Already persisted {@link UserEntity} representing created user
     * @throws IllegalArgumentException before creating a user when credentials validation fails
     */
    UserEntity register(T credentials) throws IllegalArgumentException;

    /**
     * <p>Attempts to authenticate given  credentails  returning user  in case of success or throwing exception
     * in case of failure</p>
     * @param credentials to use in login process
     * @return {@link UserEntity} persisted entity representing user with given credentials
     * @throws BadCredentialsException when user is missing or password is incorrect
     */
    UserEntity authenticateCredentials(T credentials) throws BadCredentialsException;

    /**
     * <p>Deletes credentials of specific type linked to given user</p>
     * @param user for whom credentials would be deleted
     */
    void deleteUserCredentials(UserEntity user);
}
