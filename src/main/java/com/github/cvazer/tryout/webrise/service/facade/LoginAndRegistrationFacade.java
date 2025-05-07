package com.github.cvazer.tryout.webrise.service.facade;

import com.github.cvazer.tryout.webrise.security.JwtProvider;
import com.github.cvazer.tryout.webrise.service.credentials.basic.BasicCredentials;
import com.github.cvazer.tryout.webrise.service.credentials.basic.BasicCredentialsService;
import com.github.cvazer.tryout.webrise.service.facade.exceptions.UserRegistrationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginAndRegistrationFacade {
    private final BasicCredentialsService basicCredentialsService;
    private final JwtProvider jwtProvider;

    public void register(BasicCredentials credentials) {
        try {
            basicCredentialsService.register(credentials);
        } catch (IllegalArgumentException e) {
            throw new UserRegistrationException("Unable to register user with provided basic credentials", e);
        }
    }

    public String login(BasicCredentials credentials) {
        var user = basicCredentialsService.authenticateCredentials(credentials);
        var jwt = jwtProvider.generateAndSign(user);
        return jwt.serialize();
    }

    // Тут могут быть методы для регистрации\логина через другие (сторонние) сайте типа Google, Facebook и т. д.
}
