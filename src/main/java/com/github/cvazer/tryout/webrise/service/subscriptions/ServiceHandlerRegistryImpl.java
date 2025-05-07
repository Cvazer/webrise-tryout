package com.github.cvazer.tryout.webrise.service.subscriptions;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ServiceHandlerRegistryImpl implements ServiceHandlerRegistry {
    private final Map<String, SubscriptionServiceHandler> handlers;

    public ServiceHandlerRegistryImpl(Set<SubscriptionServiceHandler> handlers) {
        this.handlers = handlers.stream()
                .collect(Collectors.toMap(
                        SubscriptionServiceHandler::getServiceId,
                        it -> it,
                        (first, second) -> {
                            throw new MultipleHandlersException(
                                    first.getServiceId(),
                                    first.getClass(),
                                    second.getClass()
                            );
                        }
                ));
    }

    @Override
    public SubscriptionServiceHandler get(String serviceId) throws UnknownService {
        if (!handlers.containsKey(serviceId)) throw new UnknownService(serviceId);
        return handlers.get(serviceId);
    }

}
