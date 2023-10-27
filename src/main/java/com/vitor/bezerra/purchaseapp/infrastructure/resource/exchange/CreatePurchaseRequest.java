package com.vitor.bezerra.purchaseapp.infrastructure.resource.exchange;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record CreatePurchaseRequest(
        @NotNull
        @Size(max = 50, message = "Description must not exceed 50 characters")
        String description,

        @NotNull
        @Positive(message = "Amount must be a valid positive amount")
        BigDecimal amount
) {
}
