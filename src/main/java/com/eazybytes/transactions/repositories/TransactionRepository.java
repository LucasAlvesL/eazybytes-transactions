package com.eazybytes.transactions.repositories;

import com.eazybytes.transactions.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    Optional<Transaction> findByReceiverEmail(String receiverEmail);
}
