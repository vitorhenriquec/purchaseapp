package com.vitor.bezerra.purchaseapp.infrastructure.client.exchange;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ExchangeRateData {
     private String countryCurrencyDesc;

    private String exchangeRate;
}
