package com.eazybytes.transactions.services;

import com.eazybytes.transactions.dtos.TransferTransactionRequestDTO;
import com.eazybytes.transactions.entities.TransactionEntity;
import com.eazybytes.transactions.entities.TransactionStatus;
import com.eazybytes.transactions.entities.TransactionType;
import com.eazybytes.transactions.repositories.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransferTransactionService {

    private final TransactionRepository repository;


    public TransferTransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public TransactionEntity transfer(UUID customerId, TransferTransactionRequestDTO dto) {
        var customer = this.repository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        var receiver = repository.findByReceiverEmail(dto.receiverEmail()
                .describeConstable().orElseThrow(() -> new IllegalArgumentException("Receiver not found")));

        // Make transaction
        var transaction = new TransactionEntity();
        transaction.setCustomerId(customer.getCustomerId());
        transaction.setAmount(dto.amount());
        transaction.setReceiverEmail(dto.receiverEmail());
        transaction.setType(TransactionType.TRANSFER);
        transaction.setStatus(TransactionStatus.SUCCESS);

        // Save the transaction
        return repository.save(transaction);
    }
}
