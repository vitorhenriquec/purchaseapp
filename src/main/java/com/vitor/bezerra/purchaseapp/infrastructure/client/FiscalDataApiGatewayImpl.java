package com.vitor.bezerra.purchaseapp.infrastructure.client;

import com.vitor.bezerra.purchaseapp.domain.gateway.FiscalDataApiGateway;
import com.vitor.bezerra.purchaseapp.domain.model.FiscalDataModel;
import com.vitor.bezerra.purchaseapp.infrastructure.client.exchange.ExchangeRateResponse;
import com.vitor.bezerra.purchaseapp.infrastructure.mapper.FiscalDataMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static com.vitor.bezerra.purchaseapp.infrastructure.configuration.CacheConfig.FISCAL_DATA_CACHE_NAME;

@Log4j2
@AllArgsConstructor
public class FiscalDataApiGatewayImpl implements FiscalDataApiGateway {

    private final FiscalDataClient fiscalDataClient;

    private final FiscalDataMapper fiscalDataMapper;

    @Override
    @Cacheable(FISCAL_DATA_CACHE_NAME)
    public List<FiscalDataModel> retriveFiscalDataByDate(final String recordDate) {
        log.info("method={}; message={};", "retriveFiscalDataByDate", String.join(" ", "The process of retrieving fiscal data info from", recordDate));
        final ResponseEntity<ExchangeRateResponse> exchangeRateResponse = fiscalDataClient.getExchangeRateFilterByDate(
                "country_currency_desc, exchange_rate",
                String.join("", "record_date:eq:", recordDate)
        );

        if (Objects.nonNull(exchangeRateResponse.getBody()) && exchangeRateResponse.getBody().getData().size() == 0) {
            return List.of();
        }

        return exchangeRateResponse.getBody().getData().stream().map(
                fiscalDataMapper::toModel
        ).toList();
    }
}
