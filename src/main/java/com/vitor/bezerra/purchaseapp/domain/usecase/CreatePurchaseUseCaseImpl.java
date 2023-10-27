package com.vitor.bezerra.purchaseapp.domain.usecase;

import com.vitor.bezerra.purchaseapp.domain.gateway.PurchaseDatabaseGateway;
import com.vitor.bezerra.purchaseapp.domain.model.PurchaseModel;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@AllArgsConstructor
@Log4j2
public class CreatePurchaseUseCaseImpl implements CreatePurchaseUseCase {

    private final PurchaseDatabaseGateway purchaseDatabaseGateway;

    @Override
    public PurchaseModel createPurchase(PurchaseModel purchaseModel) {
        log.info("method={};", "createPurchase");
        return purchaseDatabaseGateway.save(purchaseModel);
    }
}
