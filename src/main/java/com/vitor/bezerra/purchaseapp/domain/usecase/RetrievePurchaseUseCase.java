package com.vitor.bezerra.purchaseapp.domain.usecase;

import com.vitor.bezerra.purchaseapp.domain.exception.PurchaseNotFoundException;
import com.vitor.bezerra.purchaseapp.domain.model.FiscalDataModel;
import com.vitor.bezerra.purchaseapp.domain.model.PurchaseModel;
import org.antlr.v4.runtime.misc.Pair;

public interface RetrievePurchaseUseCase {
    Pair<PurchaseModel, FiscalDataModel> retrivePurchase(final Long purchaseId) throws PurchaseNotFoundException;
}
