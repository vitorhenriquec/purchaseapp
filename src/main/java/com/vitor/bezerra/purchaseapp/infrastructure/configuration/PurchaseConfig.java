package com.vitor.bezerra.purchaseapp.infrastructure.configuration;

import com.vitor.bezerra.purchaseapp.domain.gateway.PurchaseDatabaseGateway;
import com.vitor.bezerra.purchaseapp.domain.usecase.CreatePurchaseUseCase;
import com.vitor.bezerra.purchaseapp.domain.usecase.CreatePurchaseUseCaseImpl;
import com.vitor.bezerra.purchaseapp.domain.usecase.RetrievePurchaseUseCase;
import com.vitor.bezerra.purchaseapp.domain.usecase.RetrievePurchaseUseCaseImpl;
import com.vitor.bezerra.purchaseapp.infrastructure.adapter.PurchaseDatabaseAdapter;
import com.vitor.bezerra.purchaseapp.infrastructure.adapter.repository.PurchaseRepository;
import com.vitor.bezerra.purchaseapp.infrastructure.mapper.PurchaseMapper;
import com.vitor.bezerra.purchaseapp.infrastructure.resource.exchange.RetrievePurchaseResponse;
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

    @Primary
    @Bean
    public RetrievePurchaseUseCase retrievePurchaseUseCase(final PurchaseDatabaseGateway purchaseDatabaseGateway) {
        return new RetrievePurchaseUseCaseImpl(
                purchaseDatabaseGateway
        );
    }
}
