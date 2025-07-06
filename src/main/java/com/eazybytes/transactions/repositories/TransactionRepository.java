package com.eazybytes.transactions.repositories;

import com.eazybytes.transactions.entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<TransactionEntity, UUID> {
    Optional<TransactionEntity> findByReceiverEmail(String receiverEmail);
}
