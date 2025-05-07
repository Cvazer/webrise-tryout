package com.github.cvazer.tryout.webrise.web.controllers;

import com.github.cvazer.tryout.webrise.service.credentials.basic.BasicCredentials;
import com.github.cvazer.tryout.webrise.service.facade.LoginAndRegistrationFacade;
import com.github.cvazer.tryout.webrise.web.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final LoginAndRegistrationFacade loginAndRegistrationFacade;

    @PostMapping
    public void register(@Valid @RequestBody BasicCredentials rq) {
        loginAndRegistrationFacade.register(rq);
    }
    
    @PostMapping("/login")
    public ApiResponse<String> get(@Valid @RequestBody BasicCredentials rq) {
        return new ApiResponse<>(loginAndRegistrationFacade.login(rq));
    }

}
