package com.vitor.bezerra.purchaseapp.infrastructure.resource;

import com.vitor.bezerra.purchaseapp.infrastructure.resource.exchange.CreatePurchaseRequest;
import com.vitor.bezerra.purchaseapp.infrastructure.resource.exchange.CreatePurchaseResponse;
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
    );

    @Operation(summary = "This endpoint retrieves a purchase in a Specified Country’s" +
            "Currency")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Purchase convert to specified country's",
                            content = {
                                    @Content(
                                            mediaType = APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = CreatePurchaseResponse.class)
                                    )
                            }
                    )
            }
    )
    ResponseEntity<CreatePurchaseResponse> retrieveTransaction(
            final Long purchaseId
    );
}
