package com.vitor.bezerra.purchaseapp.infrastructure.adapter;

import com.vitor.bezerra.purchaseapp.domain.exception.PurchaseNotFoundException;
import com.vitor.bezerra.purchaseapp.domain.gateway.PurchaseDatabaseGateway;
import com.vitor.bezerra.purchaseapp.domain.model.PurchaseModel;
import com.vitor.bezerra.purchaseapp.infrastructure.adapter.repository.PurchaseRepository;
import com.vitor.bezerra.purchaseapp.infrastructure.mapper.PurchaseMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Log4j2
public class PurchaseDatabaseAdapter implements PurchaseDatabaseGateway {

    private final PurchaseRepository purchaseRepository;

    private final PurchaseMapper purchaseMapper;

    @Override
    public PurchaseModel save(PurchaseModel purchaseModel) {
        log.info("method={};", "save");
        return purchaseMapper.toModel(
                purchaseRepository.save(
                purchaseMapper.toEntity(purchaseModel)
            )
        );
    }

    @Override
    public PurchaseModel findById(final Long purchaseId) throws PurchaseNotFoundException {
        final var purchaseEntity = purchaseRepository.findById(purchaseId).orElseThrow(PurchaseNotFoundException::new);
        return purchaseMapper.toModel(purchaseEntity);
    }
}
