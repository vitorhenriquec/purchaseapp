package com.vitor.bezerra.purchaseapp.infrastructure.client;

import com.vitor.bezerra.purchaseapp.domain.gateway.FiscalDataApiGateway;
import com.vitor.bezerra.purchaseapp.domain.model.FiscalDataModel;

import java.util.List;

public class FiscalDataApiGatewayImpl implements FiscalDataApiGateway {


    @Override
    public List<FiscalDataModel> retriveFiscalDataByDate(String recordDate) {
        return null;
    }
}
