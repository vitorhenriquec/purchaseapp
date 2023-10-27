package com.vitor.bezerra.purchaseapp.infrastructure.resource.exchange;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreatePurchaseRequest(
        @NotNull
        String description,

        @NotNull
        BigDecimal amount
) {
}
