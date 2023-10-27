package com.vitor.bezerra.purchaseapp.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseModel {
    private Long id;

    private String description;

    private LocalDate createdAt;

    private BigDecimal amount;
}
