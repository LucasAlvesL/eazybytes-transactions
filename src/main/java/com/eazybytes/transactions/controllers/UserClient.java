package com.eazybytes.transactions.controllers;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.UUID;

@FeignClient(name = "service-users")
public interface UserClient {

    @PostMapping("/customers/deposit")
    void depositToEmail(UUID customerId, @RequestBody BigDecimal amount, @RequestBody String receiverEmail);
}
