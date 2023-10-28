package com.vitor.bezerra.purchaseapp.domain.usecase;

import com.vitor.bezerra.purchaseapp.domain.InvalidPurchaseValueException;
import com.vitor.bezerra.purchaseapp.domain.gateway.PurchaseDatabaseGateway;
import com.vitor.bezerra.purchaseapp.domain.model.PurchaseModel;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.math.BigDecimal;
import java.math.RoundingMode;

@AllArgsConstructor
@Log4j2
public class CreatePurchaseUseCaseImpl implements CreatePurchaseUseCase {

    private final PurchaseDatabaseGateway purchaseDatabaseGateway;

    private static final int NUMBER_DECIMAL_PLACES = 2;

    @Override
    public PurchaseModel createPurchase(PurchaseModel purchaseModel) throws InvalidPurchaseValueException {
        if(purchaseModel.getAmount().compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidPurchaseValueException();
        }


        if(purchaseModel.getDescription().length() > 50) {
            throw new InvalidPurchaseValueException();
        }

        purchaseModel.setAmount(
                purchaseModel.getAmount().setScale(NUMBER_DECIMAL_PLACES, RoundingMode.HALF_UP)
        );

        log.info("method={};", "createPurchase");
        return purchaseDatabaseGateway.save(purchaseModel);
    }
}
