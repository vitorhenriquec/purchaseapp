package com.vitor.bezerra.purchaseapp.infrastructure.client.exchange;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ExchangeRateResponse {
    private List<ExchangeRateData> data;
    private Map<String, Object> meta;
    private Map<String, String> links;
}
