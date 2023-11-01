package com.vitor.bezerra.purchaseapp.infrastructure.mapper;

import com.vitor.bezerra.purchaseapp.domain.model.FiscalDataModel;
import com.vitor.bezerra.purchaseapp.infrastructure.client.exchange.ExchangeRateData;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class FiscalDataMapperImpl implements FiscalDataMapper {
    @Override
    public FiscalDataModel toModel(final ExchangeRateData exchangeRateData) {
        return new FiscalDataModel(
            exchangeRateData.getCountryCurrencyDesc(),
            new BigDecimal(exchangeRateData.getExchangeRate())
        );
    }
}
