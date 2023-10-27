package com.vitor.bezerra.purchaseapp.domain.gateway;

import com.vitor.bezerra.purchaseapp.domain.model.PurchaseModel;

public interface PurchaseDatabaseGateway {
    PurchaseModel save(PurchaseModel purchaseModel);
}
