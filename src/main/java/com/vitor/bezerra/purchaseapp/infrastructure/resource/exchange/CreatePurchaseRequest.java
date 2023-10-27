package com.vitor.bezerra.purchaseapp.infrastructure.resource.exchange;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record CreatePurchaseRequest(
        @NotNull
        @Size(max = 50)
        String description,

        @NotNull
        BigDecimal amount
) {
}
