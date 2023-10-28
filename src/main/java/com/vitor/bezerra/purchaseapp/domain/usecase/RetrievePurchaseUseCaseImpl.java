package com.vitor.bezerra.purchaseapp.domain.usecase;

import com.vitor.bezerra.purchaseapp.domain.exception.PurchaseNotFoundException;
import com.vitor.bezerra.purchaseapp.domain.gateway.PurchaseDatabaseGateway;
import com.vitor.bezerra.purchaseapp.domain.model.FiscalDataModel;
import com.vitor.bezerra.purchaseapp.domain.model.PurchaseModel;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.antlr.v4.runtime.misc.Pair;

@AllArgsConstructor
@Log4j2
public class RetrievePurchaseUseCaseImpl implements RetrievePurchaseUseCase {

    private final PurchaseDatabaseGateway purchaseDatabaseGateway;

    //gateway pro client

    @Override
    public Pair<PurchaseModel, FiscalDataModel> retrivePurchase(final Long purchaseId) throws PurchaseNotFoundException {
        log.info("method={};message={};", "retrivePurchase",
                String.join(" ", "The process of retrieving a purchase of purchaseId", purchaseId.toString())
        );

        final var purchaseModel = purchaseDatabaseGateway.findById(purchaseId);

        return new Pair<>(purchaseModel, null);
    }
}
