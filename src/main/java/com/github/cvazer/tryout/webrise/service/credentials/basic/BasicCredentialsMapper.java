package com.github.cvazer.tryout.webrise.service.credentials.basic;

import com.github.cvazer.tryout.webrise.dao.entity.BasicCredentialsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public abstract class BasicCredentialsMapper {

    @Autowired
    private PasswordEncoder encoder;

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "password", source = "password", qualifiedByName = "password")
    public abstract BasicCredentialsEntity fromCredentials(BasicCredentials credentials);

    @Named("password")
    public String encodedPassword(String plainPassword) {
        return encoder.encode(plainPassword);
    }
}
