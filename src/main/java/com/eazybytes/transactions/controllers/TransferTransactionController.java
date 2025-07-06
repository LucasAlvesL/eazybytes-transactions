package com.eazybytes.transactions.controllers;

import com.eazybytes.transactions.dtos.TransferTransactionRequestDTO;
import com.eazybytes.transactions.services.TransferTransactionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/customers/balance")
public class TransferTransactionController {

    private final TransferTransactionService transferTransactionService;

    public TransferTransactionController(TransferTransactionService transferTransactionService) {
        this.transferTransactionService = transferTransactionService;
    }

    @PostMapping("/transfer")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<Object> transfer(HttpServletRequest request, @Valid @RequestBody TransferTransactionRequestDTO transfer) {
        var customerId = request.getAttribute("customer_id");

        try {
            var result = transferTransactionService.transfer((UUID.fromString(customerId.toString())), transfer);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Transfer failed: " + e.getMessage());
        }
    }
}
