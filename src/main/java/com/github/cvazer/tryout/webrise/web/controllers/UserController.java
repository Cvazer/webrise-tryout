package com.github.cvazer.tryout.webrise.web.controllers;

import com.github.cvazer.tryout.webrise.service.credentials.basic.BasicCredentials;
import com.github.cvazer.tryout.webrise.service.facade.LoginAndRegistrationFacade;
import com.github.cvazer.tryout.webrise.web.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final LoginAndRegistrationFacade loginAndRegistrationFacade;

    @PostMapping
    public ApiResponse<Void> register(@Valid @RequestBody BasicCredentials rq) {
        loginAndRegistrationFacade.register(rq);
        return new ApiResponse<>();
    }

    @PostMapping("/login")
    public ApiResponse<String> get(@Valid @RequestBody BasicCredentials rq) {
        return new ApiResponse<>(loginAndRegistrationFacade.login(rq));
    }

}
