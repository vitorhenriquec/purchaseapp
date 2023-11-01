package com.vitor.bezerra.purchaseapp.infrastructure.client.exchange;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ExchangeRateData {
     private String countryCurrencyDesc;

    private String exchangeRate;
}
