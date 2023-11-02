package com.vitor.bezerra.purchaseapp.domain.usecase;

import com.vitor.bezerra.purchaseapp.domain.exception.PurchaseNotFoundException;
import com.vitor.bezerra.purchaseapp.domain.gateway.FiscalDataApiGateway;
import com.vitor.bezerra.purchaseapp.domain.gateway.PurchaseDatabaseGateway;
import com.vitor.bezerra.purchaseapp.domain.model.FiscalDataModel;
import com.vitor.bezerra.purchaseapp.domain.model.PurchaseModel;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.util.Pair;

import java.util.List;

@AllArgsConstructor
@Log4j2
public class RetrievePurchaseUseCaseImpl implements RetrievePurchaseUseCase {

    private final PurchaseDatabaseGateway purchaseDatabaseGateway;

    private final FiscalDataApiGateway fiscalDataApiGateway;

    @Override
    public Pair<PurchaseModel, List<FiscalDataModel>> retrivePurchase(final Long purchaseId) throws PurchaseNotFoundException {
        log.info("method={};message={};", "retrivePurchase",
                String.join(" ", "The process of retrieving a purchase of purchaseId", purchaseId.toString())
        );

        final var purchaseModel = purchaseDatabaseGateway.findById(purchaseId);

        return Pair.of(
                purchaseModel,
                fiscalDataApiGateway.retriveFiscalDataByDate(
                        purchaseModel.getCreatedAt().toString()
                )
        );
    }
}
