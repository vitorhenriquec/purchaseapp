package com.vitor.bezerra.purchaseapp.infrastructure.mapper;

import com.vitor.bezerra.purchaseapp.domain.model.FiscalDataModel;
import com.vitor.bezerra.purchaseapp.infrastructure.client.exchange.ExchangeRateData;

public interface FiscalDataMapper {
    FiscalDataModel toModel(ExchangeRateData exchangeRateData);
}
