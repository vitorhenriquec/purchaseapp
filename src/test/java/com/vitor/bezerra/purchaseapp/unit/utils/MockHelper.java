package com.vitor.bezerra.purchaseapp.unit.utils;

import com.vitor.bezerra.purchaseapp.domain.model.PurchaseModel;
import com.vitor.bezerra.purchaseapp.infrastructure.adapter.entity.PurchaseEntity;
import com.vitor.bezerra.purchaseapp.infrastructure.client.exchange.ExchangeRateData;
import com.vitor.bezerra.purchaseapp.infrastructure.client.exchange.ExchangeRateResponse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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

    public static ExchangeRateResponse createFirstExchangeRateResponsePage() {
        return new ExchangeRateResponse(
                List.of(
                        new ExchangeRateData("Trinidad & Tobago-Dollar", "6.747"),
                        new ExchangeRateData("Tunisia-Dinar", "3.166")
                ),
                Map.of("total-pages", 2, "total-count", 169),
                Map.of()
        );
    }

    public static ExchangeRateResponse createSecondExchangeRateResponsePage() {
        return new ExchangeRateResponse(
                List.of(
                        new ExchangeRateData("Turkey-New Lira", "27.427"),
                        new ExchangeRateData("Ukraine-Hryvnia", "36.929")
                ),
                Map.of("total-pages", 2, "total-count", 169),
                Map.of()
        );
    }
}
