package com.vitor.bezerra.purchaseapp.unit.usecase;

import com.vitor.bezerra.purchaseapp.domain.exception.InvalidPurchaseValueException;
import com.vitor.bezerra.purchaseapp.domain.gateway.PurchaseDatabaseGateway;
import com.vitor.bezerra.purchaseapp.domain.model.PurchaseModel;
import com.vitor.bezerra.purchaseapp.domain.usecase.CreatePurchaseUseCaseImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static com.vitor.bezerra.purchaseapp.unit.utils.Constants.INVALID_DESCRIPTION;
import static com.vitor.bezerra.purchaseapp.unit.utils.Constants.INVALID_NEGATIVE_AMOUNT;
import static com.vitor.bezerra.purchaseapp.unit.utils.MockHelper.createNewValidPurchaseModel;
import static com.vitor.bezerra.purchaseapp.unit.utils.MockHelper.createSavedPurchaseModel;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ActiveProfiles(profiles = "test")
@SpringBootTest(classes = {CreatePurchaseUseCaseImpl.class, PurchaseDatabaseGateway.class})
public class CreatePurchaseUseCaseImplTest {

    @Autowired
    private CreatePurchaseUseCaseImpl createPurchaseUseCase;

    @MockBean
    private PurchaseDatabaseGateway purchaseDatabaseGateway;

    @Test
    @DisplayName("Should successfully create purchase")
    public void shouldSuccessfullyCreatePurchase() {
        final PurchaseModel newPurchaseModel = createNewValidPurchaseModel();

        when(purchaseDatabaseGateway.save(any())).thenReturn(
                createSavedPurchaseModel()
        );

        assertDoesNotThrow(
                () -> {
                    assertNotNull(createPurchaseUseCase.createPurchase(newPurchaseModel));
                }
        );

        verify(purchaseDatabaseGateway, times(1)).save(any());
    }

    @Test
    @DisplayName("Should unsuccessfully create purchase when amount is negative")
    public void shouldUnsuccessfullyCreatePurchaseWhenAmountNegative() {
        PurchaseModel newPurchaseModel = createNewValidPurchaseModel();
        newPurchaseModel.setAmount(INVALID_NEGATIVE_AMOUNT);

        assertThrows(
                InvalidPurchaseValueException.class,
                () -> {
                   createPurchaseUseCase.createPurchase(newPurchaseModel);
                }
        );

        verify(purchaseDatabaseGateway, times(0)).save(any());
    }

    @Test
    @DisplayName("Should unsuccessfully create purchase when description length is invalid")
    public void shouldUnsuccessfullyCreatePurchaseWhenDescriptionLengthInvalid() {
        PurchaseModel newPurchaseModel = createNewValidPurchaseModel();
        newPurchaseModel.setDescription(INVALID_DESCRIPTION);

        assertThrows(
                InvalidPurchaseValueException.class,
                () -> {
                    createPurchaseUseCase.createPurchase(newPurchaseModel);
                }
        );

        verify(purchaseDatabaseGateway, times(0)).save(any());
    }

    @Test
    @DisplayName("Should successfully create purchase with amount rounded to the nearest cent")
    public void shouldSuccessfullyCreatePurchaseAmountRounded() {
        PurchaseModel newPurchaseModel = createNewValidPurchaseModel();
        newPurchaseModel.setAmount(new BigDecimal("10.333"));

        when(purchaseDatabaseGateway.save(any())).thenReturn(
                createSavedPurchaseModel()
        );

        assertDoesNotThrow(
                () -> {
                    final var savedPurchaseModel = createPurchaseUseCase.createPurchase(newPurchaseModel);
                    assertEquals(new BigDecimal("10.33"), savedPurchaseModel.getAmount());
                }
        );

        verify(purchaseDatabaseGateway, times(1)).save(any());
    }
}
