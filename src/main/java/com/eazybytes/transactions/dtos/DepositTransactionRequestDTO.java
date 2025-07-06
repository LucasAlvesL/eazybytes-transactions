package com.eazybytes.transactions.dtos;

import java.math.BigDecimal;

public record DepositTransactionRequestDTO(
    BigDecimal amount,
    String receiverEmail
) {
}
