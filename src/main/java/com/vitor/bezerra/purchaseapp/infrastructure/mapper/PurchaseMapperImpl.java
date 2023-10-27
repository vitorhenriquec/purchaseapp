package com.vitor.bezerra.purchaseapp.infrastructure.mapper;

import com.vitor.bezerra.purchaseapp.domain.model.PurchaseModel;
import com.vitor.bezerra.purchaseapp.infrastructure.adapter.entity.PurchaseEntity;
import org.springframework.stereotype.Component;

@Component
public class PurchaseMapperImpl implements PurchaseMapper {
    @Override
    public PurchaseModel toModel(final PurchaseEntity purchaseEntity) {
        return new PurchaseModel(
                purchaseEntity.getId(),
                purchaseEntity.getDescription(),
                purchaseEntity.getCreatedAt(),
                purchaseEntity.getAmount()
        );
    }

    @Override
    public PurchaseEntity toEntity(final PurchaseModel purchaseModel) {
        return new PurchaseEntity(
                purchaseModel.getId(),
                purchaseModel.getDescription(),
                purchaseModel.getCreatedAt(),
                purchaseModel.getAmount()
        );
    }
}
