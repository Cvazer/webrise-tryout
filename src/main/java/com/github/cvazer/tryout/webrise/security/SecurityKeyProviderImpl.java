package com.github.cvazer.tryout.webrise.security;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Component
public class SecurityKeyProviderImpl implements SecurityKeyProvider {

    private RSAKey jwk;

    @PostConstruct
    public void loadKeyStore() {
        var jwkPath = Path.of("key.json");

        try {
            if (!Files.exists(jwkPath)) {
                log.info("No JWK found [{}]. Creating new one", jwkPath);

                jwk = new RSAKeyGenerator(2048)
                        .keyID(UUID.randomUUID().toString())
                        .issueTime(new Date())
                        .keyUse(KeyUse.SIGNATURE)
                        .generate();


                Files.writeString(jwkPath, jwk.toJSONString());
            } else {
                jwk = JWK.parse(Files.readString(jwkPath)).toRSAKey();
            }
        } catch (ParseException e) {
            throw new JwkException(jwkPath, e);
        } catch (JOSEException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public RSAKey getRsaKey() {
        return jwk;
    }
}
