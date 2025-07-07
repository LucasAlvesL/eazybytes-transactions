package com.eazybytes.transactions.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.UUID;

@FeignClient(name = "service-users")
public interface UsersClient {

    @GetMapping("/customers/{customerId}")
    void getUserById(UUID customerId);
}
