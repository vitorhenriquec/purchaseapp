package com.vitor.bezerra.purchaseapp.infrastructure.resource;

import com.vitor.bezerra.purchaseapp.domain.exception.InvalidPurchaseValueException;
import com.vitor.bezerra.purchaseapp.domain.exception.PurchaseNotFoundException;
import com.vitor.bezerra.purchaseapp.domain.model.FiscalDataModel;
import com.vitor.bezerra.purchaseapp.domain.model.PurchaseModel;
import com.vitor.bezerra.purchaseapp.domain.usecase.CreatePurchaseUseCase;
import com.vitor.bezerra.purchaseapp.domain.usecase.RetrievePurchaseUseCase;
import com.vitor.bezerra.purchaseapp.infrastructure.resource.exchange.CreatePurchaseRequest;
import com.vitor.bezerra.purchaseapp.infrastructure.resource.exchange.CreatePurchaseResponse;
import com.vitor.bezerra.purchaseapp.infrastructure.resource.exchange.RetrievePurchaseResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@RequestMapping("/v1/purchase")
@RestController
@Tag(name="Purchase Resource")
public class PurchaseResourceImpl implements PurchaseResource {

    private final CreatePurchaseUseCase createPurchaseUseCase;

    private final RetrievePurchaseUseCase retrievePurchaseUseCase;

    @PostMapping
    @Override
    public ResponseEntity<CreatePurchaseResponse> createTransaction(
            @RequestBody @Valid CreatePurchaseRequest request
    ) throws InvalidPurchaseValueException {
        var purchaseModel = new PurchaseModel();
        purchaseModel.setDescription(request.description());
        purchaseModel.setAmount(request.amount());
        purchaseModel.setCreatedAt(LocalDate.now());

        purchaseModel =  createPurchaseUseCase.createPurchase(purchaseModel);

        return ResponseEntity.ok(
                new CreatePurchaseResponse(
                        purchaseModel.getId(),
                        purchaseModel.getDescription(),
                        purchaseModel.getAmount(),
                        purchaseModel.getCreatedAt().toString()
                )
        );
    }

    @GetMapping("/{purchaseId}")
    @Override
    public ResponseEntity<RetrievePurchaseResponse> retrievePurchase(
            @PathVariable("purchaseId") final Long purchaseId
    ) throws PurchaseNotFoundException {
        final Pair<PurchaseModel, List<FiscalDataModel>> pairPurchaseFiscalDataModels =
                retrievePurchaseUseCase.retrivePurchase(purchaseId);

        final var purchaseModel = pairPurchaseFiscalDataModels.getFirst();
        final var fiscalDataModels = pairPurchaseFiscalDataModels.getSecond();

        return ResponseEntity.ok(
                new RetrievePurchaseResponse(
                    purchaseModel,
                    fiscalDataModels
                )
        );
    }
}
