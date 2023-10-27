package com.vitor.bezerra.purchaseapp.domain.gateway;

import com.vitor.bezerra.purchaseapp.domain.model.FiscalDataModel;

import java.util.List;

public interface FiscalDataApiGateway {
    List<FiscalDataModel> retriveFiscalDataByDate(String recordDate);
}
