package com.eazybytes.transactions.services;

import com.eazybytes.transactions.controllers.UserClient;
import com.eazybytes.transactions.dtos.DepositTransactionRequestDTO;
import com.eazybytes.transactions.entities.Transaction;
import com.eazybytes.transactions.repositories.TransactionRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class DepositTransactionService {

    private final TransactionRepository repository;

    private final UserClient userClient;

    public DepositTransactionService(TransactionRepository repository, UserClient userClient) {
        this.repository = repository;
        this.userClient = userClient;
    }

    @Transactional
    public void deposit(HttpServletRequest request, DepositTransactionRequestDTO deposit) {
        if (deposit.amount() == null || deposit.amount().compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Amount must be positive");

        String customerId = request.getUserPrincipal().getName();

        try {
            userClient.depositToEmail(UUID.fromString(customerId), deposit.amount(), deposit.receiverEmail());
        } catch (Exception e) {
            throw new RuntimeException("Failed to deposit amount: " + e.getMessage(), e);
        }
    }
}
