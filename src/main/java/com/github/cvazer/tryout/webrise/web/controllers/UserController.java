package com.github.cvazer.tryout.webrise.web.controllers;

import com.github.cvazer.tryout.webrise.service.credentials.basic.BasicCredentials;
import com.github.cvazer.tryout.webrise.service.facade.LoginAndRegistrationFacade;
import com.github.cvazer.tryout.webrise.service.facade.UserFacade;
import com.github.cvazer.tryout.webrise.web.ApiResponse;
import com.github.cvazer.tryout.webrise.web.dto.UserDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final LoginAndRegistrationFacade loginAndRegistrationFacade;
    private final UserFacade userFacade;

    @PostMapping
    public ApiResponse<Void> register(@Valid @RequestBody BasicCredentials rq) {
        loginAndRegistrationFacade.register(rq);
        return new ApiResponse<>();
    }

    @GetMapping
    public ApiResponse<UserDto> info() {
        return new ApiResponse<>(userFacade.info());
    }

    @PutMapping
    public ApiResponse<UserDto> update(@Valid @RequestBody UserDto dto) {
        return new ApiResponse<>(userFacade.update(dto));
    }

    @DeleteMapping
    public ApiResponse<Void> delete() {
        userFacade.delete();
        return new ApiResponse<>();
    }

    @PostMapping("/login")
    public ApiResponse<String> get(@Valid @RequestBody BasicCredentials rq) {
        return new ApiResponse<>(loginAndRegistrationFacade.login(rq));
    }

}
