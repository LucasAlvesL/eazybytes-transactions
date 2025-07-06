package com.eazybytes.transactions.dtos;

import java.math.BigDecimal;

public record TransferTransactionRequestDTO(
    BigDecimal amount,
    String receiverEmail
) {
}
