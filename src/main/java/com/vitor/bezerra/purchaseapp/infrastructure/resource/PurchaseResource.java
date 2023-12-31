package com.vitor.bezerra.purchaseapp.infrastructure.resource;

import com.vitor.bezerra.purchaseapp.domain.exception.InvalidPurchaseValueException;
import com.vitor.bezerra.purchaseapp.domain.exception.PurchaseNotFoundException;
import com.vitor.bezerra.purchaseapp.infrastructure.resource.exchange.CreatePurchaseRequest;
import com.vitor.bezerra.purchaseapp.infrastructure.resource.exchange.CreatePurchaseResponse;
import com.vitor.bezerra.purchaseapp.infrastructure.resource.exchange.RetrievePurchaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
public interface PurchaseResource {

    @Operation(summary = "This endpoint creates a purchase transaction")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Purchase created",
                            content = {
                                    @Content(
                                            mediaType = APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = CreatePurchaseResponse.class)
                                    )
                            }
                    )
            }
    )
    ResponseEntity<CreatePurchaseResponse> createTransaction(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Purchase data to be store")
            CreatePurchaseRequest request
    ) throws InvalidPurchaseValueException;

    @Operation(summary = "This endpoint retrieves a purchase")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Purchase retrieved",
                            content = {
                                    @Content(
                                            mediaType = APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = RetrievePurchaseResponse.class)
                                    )
                            }
                    )
            }
    )
    ResponseEntity<RetrievePurchaseResponse> retrievePurchase(
            final Long purchaseId
    ) throws PurchaseNotFoundException;
}
