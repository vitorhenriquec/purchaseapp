package com.vitor.bezerra.purchaseapp.infrastructure.resource.exchange;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record RetrievePurchaseResponse(
        Long id,

        String description,

        BigDecimal amount,

        String createdAt,

        List<AmountConversion> amountConversions
) {
}
