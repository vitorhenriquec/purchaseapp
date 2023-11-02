package com.vitor.bezerra.purchaseapp.domain.gateway;

import com.vitor.bezerra.purchaseapp.domain.exception.PurchaseNotFoundException;
import com.vitor.bezerra.purchaseapp.domain.model.PurchaseModel;

public interface PurchaseDatabaseGateway {
    PurchaseModel save(PurchaseModel purchaseModel);

    PurchaseModel findById(final Long purchaseId) throws PurchaseNotFoundException;
}
