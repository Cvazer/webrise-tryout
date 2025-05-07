package com.github.cvazer.tryout.webrise.service.users;

import com.github.cvazer.tryout.webrise.dao.entity.UserEntity;
import com.github.cvazer.tryout.webrise.web.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = SPRING, nullValuePropertyMappingStrategy = IGNORE)
public interface UserMapper {

    UserDto fromEntity(UserEntity entity);

    @Mapping(target = "subscriptions", ignore = true)
    @Mapping(target = "id", ignore = true)
    void mapFields(@MappingTarget UserEntity entity, UserDto dto);
}
