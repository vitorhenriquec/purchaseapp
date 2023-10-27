package com.vitor.bezerra.purchaseapp.infrastructure.resource.exchange;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record RetrivePurchaseResponse(
        Long id,

        String description,

        BigDecimal amount,

        LocalDate createdAt,

        List<AmountConversion> amountConversions
) {
}
