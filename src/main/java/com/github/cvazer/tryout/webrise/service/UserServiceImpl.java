package com.github.cvazer.tryout.webrise.service;

import com.github.cvazer.tryout.webrise.dao.entity.UserEntity;
import com.github.cvazer.tryout.webrise.dao.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo repo;

    @Override
    @Transactional
    public UserEntity create(String displayName) throws IllegalArgumentException {
        return repo.save(new UserEntity(displayName));
    }

    @Override
    public UserEntity getCurrentUser() throws IllegalStateException {
        var auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null) {
            throw new IllegalStateException("Must be called with auth context present");
        }

        if (auth instanceof JwtAuthenticationToken jwtAuth) {
            var jwt = (Jwt) jwtAuth.getPrincipal();
            var userId = Long.parseLong(jwt.getSubject());
            return repo.findById(userId)
                    .orElseThrow(() -> new IllegalStateException("Invalid  user from JWT claim"));
        } else {
            throw new IllegalStateException(String.format("[%s] auth is not supported", auth.getClass().getName()));
        }
    }
}
