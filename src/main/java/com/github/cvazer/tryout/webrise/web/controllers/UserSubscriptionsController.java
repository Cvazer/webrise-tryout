package com.github.cvazer.tryout.webrise.web.controllers;

import com.github.cvazer.tryout.webrise.service.facade.SubscriptionsFacade;
import com.github.cvazer.tryout.webrise.web.ApiResponse;
import com.github.cvazer.tryout.webrise.web.dto.SubscriptionDto;
import com.github.cvazer.tryout.webrise.web.dto.rq.SubscribeRq;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/users/subscriptions")
@RequiredArgsConstructor
public class UserSubscriptionsController {
    private final SubscriptionsFacade subscriptionsFacade;

    @GetMapping
    public ApiResponse<Set<SubscriptionDto>> listSubscriptions() {
        return new ApiResponse<>(subscriptionsFacade.list());
    }

    @PostMapping
    public ApiResponse<Void> subscribe(@Valid @RequestBody SubscribeRq rq) {
        subscriptionsFacade.subscribe(rq.serviceId());
        return new ApiResponse<>();
    }

    @DeleteMapping("/{serviceId}")
    public ApiResponse<Void> unsubscribe(@PathVariable String serviceId) {
        subscriptionsFacade.unsubscribe(serviceId);
        return new ApiResponse<>();
    }
}
