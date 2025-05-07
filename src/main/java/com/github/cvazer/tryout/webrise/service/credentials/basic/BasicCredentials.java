package com.github.cvazer.tryout.webrise.service.credentials.basic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BasicCredentials {

    @NotBlank
    @Pattern(regexp = "^(?=[a-zA-Z0-9._-]{3,}$)(?!.*[_.-]{2})[^_.-].*[^_.-]$")
    private String username;

    @NotBlank
    private String password;
}
