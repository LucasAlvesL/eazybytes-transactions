package com.eazybytes.transactions.services;

import com.eazybytes.transactions.client.UsersClient;
import feign.FeignException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerValidatorService {

    private final UsersClient usersClient;

    public CustomerValidatorService(UsersClient usersClient) {
        this.usersClient = usersClient;
    }

    public void isValidCustomer(UUID customerId) {
        try {
            usersClient.getUserById(customerId);
        } catch (FeignException.NotFound e) {
            throw new EntityNotFoundException("Customer not found in user service");
        }
    }

}
