package com.vitor.bezerra.purchaseapp.infrastructure.mapper;

import com.vitor.bezerra.purchaseapp.domain.model.PurchaseModel;
import com.vitor.bezerra.purchaseapp.infrastructure.adapter.entity.PurchaseEntity;

public interface PurchaseMapper {
    PurchaseModel toModel(PurchaseEntity purchaseEntity);

    PurchaseEntity toEntity(PurchaseModel purchaseEntity);
}
