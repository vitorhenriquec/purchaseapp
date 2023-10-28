package com.vitor.bezerra.purchaseapp.infrastructure.client;

import com.vitor.bezerra.purchaseapp.domain.gateway.FiscalDataApiGateway;
import com.vitor.bezerra.purchaseapp.domain.model.FiscalDataModel;
import com.vitor.bezerra.purchaseapp.infrastructure.client.exchange.ExchangeRateResponse;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

@Log4j2
@AllArgsConstructor
public class FiscalDataApiGatewayImpl implements FiscalDataApiGateway {

    private final FiscalDataClient fiscalDataClient;

    @Override
    public List<FiscalDataModel> retriveFiscalDataByDate(final String recordDate) {
        final ResponseEntity<ExchangeRateResponse> exchangeRateResponse = fiscalDataClient.getExchangeRateFilterByDate(
                "country_currency_desc, exchange_rate",
                String.join("", "record_date:eq:", recordDate)
        );

        if (Objects.nonNull(exchangeRateResponse.getBody()) && exchangeRateResponse.getBody().getData().size() == 0) {
            return List.of();
        }

        return null;
    }
}
