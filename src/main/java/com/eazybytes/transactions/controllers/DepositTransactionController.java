package com.eazybytes.transactions.controllers;

import com.eazybytes.transactions.dtos.DepositTransactionRequestDTO;
import com.eazybytes.transactions.services.DepositTransactionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers/balance")
public class DepositTransactionController {

    @Autowired
    private DepositTransactionService depositTransactionService;

    @PostMapping("/deposit")
    @PreAuthorize("hasRole('CUSTOMER')")
    public void deposit(HttpServletRequest request, @Valid @RequestBody DepositTransactionRequestDTO deposit) {
        var customerId = request.getAttribute("customer_id");

        depositTransactionService.deposit((HttpServletRequest) customerId, deposit);
    }
}
