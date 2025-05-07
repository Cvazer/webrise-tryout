package com.github.cvazer.tryout.webrise.service.credentials.basic;

import com.github.cvazer.tryout.webrise.dao.entity.UserEntity;
import com.github.cvazer.tryout.webrise.dao.repo.BasicCredentialsRepo;
import com.github.cvazer.tryout.webrise.service.UserService;
import com.github.cvazer.tryout.webrise.service.ValidationUtils;
import com.github.cvazer.tryout.webrise.service.credentials.BadCredentialsException;
import com.github.cvazer.tryout.webrise.service.credentials.CredentialsService;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BasicCredentialsService implements CredentialsService<BasicCredentials> {
    private final UserService userService;
    private final Validator validator;
    private final BasicCredentialsMapper mapper;
    private final BasicCredentialsRepo repo;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserEntity register(BasicCredentials credentials) throws IllegalArgumentException {

        if (ValidationUtils.isInvalid(credentials, validator, log)) {
            throw new IllegalArgumentException("Can't register user with provided credentials");
        }

        var user = userService.create(credentials.getUsername());
        var entity = mapper.fromCredentials(credentials);
        entity.setUser(user);
        repo.save(entity);
        return user;
    }

    @Override
    public UserEntity authenticateCredentials(BasicCredentials credentials) throws BadCredentialsException {

        if (ValidationUtils.isInvalid(credentials, validator, log)) {
            throw new BadCredentialsException();
        }

        var entity = repo.findByUsername(credentials.getUsername())
                .orElseThrow(BadCredentialsException::new);

        if (!passwordEncoder.matches(credentials.getPassword(), entity.getPassword())) {
            throw new BadCredentialsException();
        }

        return entity.getUser();
    }


}
