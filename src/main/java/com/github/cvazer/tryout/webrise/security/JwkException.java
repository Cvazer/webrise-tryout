package com.github.cvazer.tryout.webrise.security;

import com.github.cvazer.tryout.webrise.web.ApiException;

import java.nio.file.Path;
import java.text.ParseException;

public class JwkException extends ApiException {

    public JwkException(Path jwkPath, ParseException e) {
        super(String.format("Unable to load JWK from path [%s]", jwkPath), e);
    }
}
