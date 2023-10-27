package com.vitor.bezerra.purchaseapp.infrastructure.configuration;

import com.vitor.bezerra.purchaseapp.domain.gateway.PurchaseDatabaseGateway;
import com.vitor.bezerra.purchaseapp.domain.usecase.CreatePurchaseUseCase;
import com.vitor.bezerra.purchaseapp.domain.usecase.CreatePurchaseUseCaseImpl;
import com.vitor.bezerra.purchaseapp.infrastructure.adapter.PurchaseDatabaseAdapter;
import com.vitor.bezerra.purchaseapp.infrastructure.adapter.repository.PurchaseRepository;
import com.vitor.bezerra.purchaseapp.infrastructure.mapper.PurchaseMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class PurchaseConfig {

    @Primary
    @Bean
    public PurchaseDatabaseGateway createPurchaseDatabaseGateway(final PurchaseRepository purchaseRepository, final PurchaseMapper purchaseMapper) {
        return new PurchaseDatabaseAdapter(
                purchaseRepository,
                purchaseMapper
        );
    }

    @Primary
    @Bean
    public CreatePurchaseUseCase createPurchaseUseCase(final PurchaseDatabaseGateway purchaseDatabaseGateway) {
        return new CreatePurchaseUseCaseImpl(
           purchaseDatabaseGateway
        );
    }
}
