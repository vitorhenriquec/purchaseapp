package com.vitor.bezerra.purchaseapp.infrastructure.resource.exchange;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreatePurchaseResponse(
        Long id,

        String description,

        BigDecimal amount,

        LocalDate createdAt
) {
}
