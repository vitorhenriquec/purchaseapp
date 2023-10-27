package com.vitor.bezerra.purchaseapp.infrastructure.resource.exchange;

import java.math.BigDecimal;

public record AmountConversion(
        String currency,

        BigDecimal exchangeRateUsed,

        BigDecimal amount
) {
}
