package com.vitor.bezerra.purchaseapp.domain.usecase;

import com.vitor.bezerra.purchaseapp.domain.model.PurchaseModel;

public interface RetrivePurchaseSpecifiedCountry {
    PurchaseModel retrivePurchaseSpecifiedCountry(PurchaseModel purchaseModel);
}
