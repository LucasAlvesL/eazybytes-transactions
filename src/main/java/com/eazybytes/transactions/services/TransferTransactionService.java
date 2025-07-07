package com.eazybytes.transactions.services;

import com.eazybytes.transactions.dtos.TransferTransactionRequestDTO;
import com.eazybytes.transactions.entities.TransactionEntity;
import com.eazybytes.transactions.entities.TransactionStatus;
import com.eazybytes.transactions.entities.TransactionType;
import com.eazybytes.transactions.repositories.TransactionRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransferTransactionService {

    private final TransactionRepository repository;
    private final CustomerValidatorService customerValidatorService;


    public TransferTransactionService(TransactionRepository repository, CustomerValidatorService customerValidatorService) {
        this.repository = repository;
        this.customerValidatorService = customerValidatorService;
    }

    @Transactional
    public TransactionEntity transfer(UUID customerId, TransferTransactionRequestDTO dto) {
        customerValidatorService.isValidCustomer(customerId);

        // Make transaction
        var transaction = new TransactionEntity();
        transaction.setCustomerId(customerId);
        transaction.setAmount(dto.amount());
        transaction.setReceiverEmail(dto.receiverEmail());
        transaction.setType(TransactionType.TRANSFER);
        transaction.setStatus(TransactionStatus.SUCCESS);

        // Save the transaction
        return repository.save(transaction);
    }
}
