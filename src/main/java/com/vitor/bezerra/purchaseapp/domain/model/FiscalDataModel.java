package com.vitor.bezerra.purchaseapp.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FiscalDataModel {
    private String countryCurrencyDesc;
    private String exchangeRate;
}
