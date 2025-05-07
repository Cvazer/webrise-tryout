package com.github.cvazer.tryout.webrise.web.controllers;

import com.github.cvazer.tryout.webrise.service.TopService;
import com.github.cvazer.tryout.webrise.web.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subscriptions")
public class SubscriptionsController {
    private final TopService topService;

    @GetMapping
    @Cacheable("top")
    public ApiResponse<Set<String>> top() {
        return new ApiResponse<>(topService.threePopular());
    }

}
