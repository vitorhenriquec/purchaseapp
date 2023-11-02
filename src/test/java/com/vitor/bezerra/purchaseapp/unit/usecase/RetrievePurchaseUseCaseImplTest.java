package com.vitor.bezerra.purchaseapp.unit.usecase;

import com.vitor.bezerra.purchaseapp.domain.exception.PurchaseNotFoundException;
import com.vitor.bezerra.purchaseapp.domain.gateway.FiscalDataApiGateway;
import com.vitor.bezerra.purchaseapp.domain.gateway.PurchaseDatabaseGateway;
import com.vitor.bezerra.purchaseapp.domain.model.PurchaseModel;
import com.vitor.bezerra.purchaseapp.domain.usecase.CreatePurchaseUseCaseImpl;
import com.vitor.bezerra.purchaseapp.domain.usecase.RetrievePurchaseUseCase;
import com.vitor.bezerra.purchaseapp.domain.usecase.RetrievePurchaseUseCaseImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.test.context.ActiveProfiles;

import static com.vitor.bezerra.purchaseapp.unit.utils.MockHelper.createEmptyListOfFiscalDataModel;
import static com.vitor.bezerra.purchaseapp.unit.utils.MockHelper.createListOfFiscalDataModel;
import static com.vitor.bezerra.purchaseapp.unit.utils.MockHelper.createSavedPurchaseModel;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ActiveProfiles(profiles = "test")
@SpringBootTest(classes = {RetrievePurchaseUseCaseImpl.class, PurchaseDatabaseGateway.class, FiscalDataApiGateway.class})
public class RetrievePurchaseUseCaseImplTest {

    @Autowired
    private RetrievePurchaseUseCaseImpl retrievePurchaseUseCase;

    @MockBean
    private PurchaseDatabaseGateway purchaseDatabaseGateway;

    @MockBean
    private FiscalDataApiGateway fiscalDataApiGateway;

    @Test
    @DisplayName("Should successfully retrieve a purchase with some fiscal data")
    public void shouldSuccessfullyRetrievePurchaseWithSomeFiscalData() throws PurchaseNotFoundException {
        final PurchaseModel savedPurchaseModel = createSavedPurchaseModel();

        when(purchaseDatabaseGateway.findById(anyLong())).thenReturn(
             savedPurchaseModel
        );

        final var listOfFiscalData = createListOfFiscalDataModel();

        when(fiscalDataApiGateway.retriveFiscalDataByDate(savedPurchaseModel.getCreatedAt().toString())).thenReturn(
                listOfFiscalData
        );

        assertDoesNotThrow(
                () -> {
                    final var response = retrievePurchaseUseCase.retrivePurchase(savedPurchaseModel.getId());
                    Assertions.assertNotNull(response);
                    Assertions.assertEquals(response.getFirst(), savedPurchaseModel);
                    Assertions.assertEquals(response.getSecond(), listOfFiscalData);
                }
        );

        verify(purchaseDatabaseGateway, times(1)).findById(anyLong());
        verify(fiscalDataApiGateway, times(1)).retriveFiscalDataByDate(any());
    }


    @Test
    @DisplayName("Should successfully retrieve a purchase with no fiscal data")
    public void shouldSuccessfullyRetrievePurchaseNoFiscalData() throws PurchaseNotFoundException {
        final PurchaseModel savedPurchaseModel = createSavedPurchaseModel();

        when(purchaseDatabaseGateway.findById(anyLong())).thenReturn(
                savedPurchaseModel
        );

        final var listOfFiscalData = createEmptyListOfFiscalDataModel();

        when(fiscalDataApiGateway.retriveFiscalDataByDate(savedPurchaseModel.getCreatedAt().toString())).thenReturn(
                listOfFiscalData
        );

        assertDoesNotThrow(
                () -> {
                    final var response = retrievePurchaseUseCase.retrivePurchase(savedPurchaseModel.getId());
                    Assertions.assertNotNull(response);
                    Assertions.assertEquals(response.getFirst(), savedPurchaseModel);
                    Assertions.assertEquals(response.getSecond(), listOfFiscalData);
                }
        );

        verify(purchaseDatabaseGateway, times(1)).findById(anyLong());
        verify(fiscalDataApiGateway, times(1)).retriveFiscalDataByDate(any());
    }

    @Test
    @DisplayName("Should throw with no purchase found")
    public void shouldThrowExceptionNoPurchaseFound() throws PurchaseNotFoundException {
        final PurchaseModel savedPurchaseModel = createSavedPurchaseModel();

        when(purchaseDatabaseGateway.findById(anyLong())).thenThrow(new PurchaseNotFoundException());

        assertThrows(
               PurchaseNotFoundException.class,
                () -> {
                    retrievePurchaseUseCase.retrivePurchase(savedPurchaseModel.getId());
                }
        );

        verify(purchaseDatabaseGateway, times(1)).findById(anyLong());
        verify(fiscalDataApiGateway, times(0)).retriveFiscalDataByDate(any());
    }
}
