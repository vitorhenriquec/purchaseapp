package com.vitor.bezerra.purchaseapp.infrastructure.resource;

import com.vitor.bezerra.purchaseapp.domain.model.PurchaseModel;
import com.vitor.bezerra.purchaseapp.domain.usecase.CreatePurchaseUseCase;
import com.vitor.bezerra.purchaseapp.infrastructure.resource.exchange.CreatePurchaseRequest;
import com.vitor.bezerra.purchaseapp.infrastructure.resource.exchange.CreatePurchaseResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@AllArgsConstructor
@RestController
@RequestMapping(name = "/v1")
public class PurchaseResourceImpl implements PurchaseResource {

    private final CreatePurchaseUseCase createPurchaseUseCase;

    @PostMapping(
            name = "/purchase",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    @Override
    public ResponseEntity<CreatePurchaseResponse> createTransaction(
            @RequestBody @Valid CreatePurchaseRequest request
    ) {
        var purchaseModel = new PurchaseModel();
        purchaseModel.setDescription(request.description());
        purchaseModel.setAmount(request.amount());

        purchaseModel =  createPurchaseUseCase.createPurchase(purchaseModel);

        return ResponseEntity.ok(
                new CreatePurchaseResponse(
                        purchaseModel.getId(),
                        purchaseModel.getDescription(),
                        purchaseModel.getAmount(),
                        purchaseModel.getCreatedAt()
                )
        );
    }
}
