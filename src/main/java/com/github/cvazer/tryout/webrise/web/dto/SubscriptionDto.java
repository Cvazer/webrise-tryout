package com.github.cvazer.tryout.webrise.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class SubscriptionDto {
    private String serviceId;
    private String serviceName;
    private String status;
    private LocalDateTime renewDate;
}
