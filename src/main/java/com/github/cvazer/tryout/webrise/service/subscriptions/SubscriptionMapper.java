package com.github.cvazer.tryout.webrise.service.subscriptions;

import com.github.cvazer.tryout.webrise.dao.entity.SubscriptionEntity;
import com.github.cvazer.tryout.webrise.web.dto.SubscriptionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface SubscriptionMapper {

    @Mapping(target = "serviceName", source = "service.name")
    @Mapping(target = "serviceId", source = "service.id")
    @Mapping(target = "renewDate", source = "renewDueDate")
    SubscriptionDto fromEntity(SubscriptionEntity entity);

}
