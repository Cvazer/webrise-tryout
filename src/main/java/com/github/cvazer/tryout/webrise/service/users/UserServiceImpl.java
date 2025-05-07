package com.github.cvazer.tryout.webrise.service.users;

import com.github.cvazer.tryout.webrise.dao.entity.UserEntity;
import com.github.cvazer.tryout.webrise.dao.repo.UserRepo;
import com.github.cvazer.tryout.webrise.service.credentials.CredentialsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo repo;
    private final Set<CredentialsService<?>> credentialsServices;

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
                    .orElseThrow(() -> new IllegalStateException("Invalid user from JWT claim"));
        } else {
            throw new IllegalStateException(String.format("[%s] auth is not supported", auth.getClass().getName()));
        }
    }

    @Override
    @Transactional
    public void delete(UserEntity user) {
        credentialsServices.forEach(it -> it.deleteUserCredentials(user));
        repo.delete(user);
        //Здесь может быть удаление каких-то связанных с пользователем вещей, кроме сущностей.
    }
}
