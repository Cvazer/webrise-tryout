package com.github.cvazer.tryout.webrise.security;

import com.github.cvazer.tryout.webrise.dao.entity.UserEntity;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JwtProviderImpl implements JwtProvider {
    private final SecurityKeyProvider keyProvider;

    @Override
    public SignedJWT generateAndSign(UserEntity user) {
        var jwk = keyProvider.getRsaKey();

        try {
            var jwt = new SignedJWT(
                    new JWSHeader.Builder(JWSAlgorithm.RS512)
                            .keyID(jwk.getKeyID())
                            .build(),
                    new JWTClaimsSet.Builder()
                            .jwtID(UUID.randomUUID().toString())
                            .subject(user.getId() + "")
                            .build()
            );
            jwt.sign(new RSASSASigner(jwk));
            return jwt;
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }
}
