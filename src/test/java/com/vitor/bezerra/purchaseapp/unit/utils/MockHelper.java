package com.vitor.bezerra.purchaseapp.unit.utils;

import com.vitor.bezerra.purchaseapp.domain.model.PurchaseModel;
import com.vitor.bezerra.purchaseapp.infrastructure.adapter.entity.PurchaseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MockHelper {
    public static PurchaseModel createNewValidPurchaseModel() {
        var purchaseModel =  new PurchaseModel();
        purchaseModel.setAmount(new BigDecimal("10.33"));
        purchaseModel.setDescription("A beautiful description");
        return purchaseModel;
    }

    public static PurchaseModel createSavedPurchaseModel() {
        return new PurchaseModel(
                1L,
                "A beautiful description",
                LocalDate.now(),
                new BigDecimal("10.33")
        );
    }

    public static PurchaseEntity createSavedPurchaseEntity() {
        return new PurchaseEntity(
                1L,
                "A beautiful description",
                LocalDate.now(),
                new BigDecimal("10.33")
        );
    }
}
