package com.vitor.bezerra.purchaseapp.domain.usecase;

import com.vitor.bezerra.purchaseapp.domain.exception.InvalidPurchaseValueException;
import com.vitor.bezerra.purchaseapp.domain.model.PurchaseModel;

public interface CreatePurchaseUseCase {
    PurchaseModel createPurchase(PurchaseModel purchaseModel) throws InvalidPurchaseValueException;
}
