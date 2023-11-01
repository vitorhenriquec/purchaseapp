package com.vitor.bezerra.purchaseapp.domain.usecase;

import com.vitor.bezerra.purchaseapp.domain.exception.PurchaseNotFoundException;
import com.vitor.bezerra.purchaseapp.domain.model.FiscalDataModel;
import com.vitor.bezerra.purchaseapp.domain.model.PurchaseModel;
import org.springframework.data.util.Pair;

import java.util.List;

public interface RetrievePurchaseUseCase {
    Pair<PurchaseModel, List<FiscalDataModel>> retrivePurchase(final Long purchaseId) throws PurchaseNotFoundException;
}
