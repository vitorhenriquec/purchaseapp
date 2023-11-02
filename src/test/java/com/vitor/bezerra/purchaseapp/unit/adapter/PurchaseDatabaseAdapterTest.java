package com.vitor.bezerra.purchaseapp.unit.adapter;

import com.vitor.bezerra.purchaseapp.domain.exception.PurchaseNotFoundException;
import com.vitor.bezerra.purchaseapp.infrastructure.adapter.PurchaseDatabaseAdapter;
import com.vitor.bezerra.purchaseapp.infrastructure.adapter.repository.PurchaseRepository;
import com.vitor.bezerra.purchaseapp.infrastructure.mapper.PurchaseMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static com.vitor.bezerra.purchaseapp.unit.utils.MockHelper.createNewValidPurchaseModel;
import static com.vitor.bezerra.purchaseapp.unit.utils.MockHelper.createSavedPurchaseEntity;
import static com.vitor.bezerra.purchaseapp.unit.utils.MockHelper.createSavedPurchaseModel;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ActiveProfiles(profiles = "test")
@SpringBootTest(classes = {PurchaseDatabaseAdapter.class, PurchaseMapper.class})
public class PurchaseDatabaseAdapterTest {

    @Autowired
    private PurchaseDatabaseAdapter purchaseDatabaseAdapter;

    @MockBean
    private PurchaseRepository purchaseRepository;

    @MockBean
    private PurchaseMapper purchaseMapper;

    @Test
    @DisplayName("Should successfully save")
    public void shouldSuccessfullySave() {
        final var newPurchaseModel = createNewValidPurchaseModel();

        when(purchaseRepository.save(any())).thenReturn(createSavedPurchaseEntity());
        when(purchaseMapper.toEntity(any())).thenReturn(createSavedPurchaseEntity());
        when(purchaseMapper.toModel(any())).thenReturn(createSavedPurchaseModel());

        assertDoesNotThrow(
                () -> {
                    assertNotNull(purchaseDatabaseAdapter.save(newPurchaseModel));
                }
        );
    }

    @Test
    @DisplayName("Should unsuccessfully save")
    public void shouldUnsuccessfullySave() {
        final var newPurchaseModel = createNewValidPurchaseModel();

        when(purchaseRepository.save(any())).thenThrow(new DataIntegrityViolationException("Error saving"));
        when(purchaseMapper.toEntity(any())).thenReturn(createSavedPurchaseEntity());
        when(purchaseMapper.toModel(any())).thenReturn(createSavedPurchaseModel());

        assertThrows(
                DataIntegrityViolationException.class,
                () -> {
                    purchaseDatabaseAdapter.save(newPurchaseModel);
                }
        );
    }

    @Test
    @DisplayName("Should successfully find a purchase")
    public void shouldSuccessfullyFindPurchase() {
        final var savedPurchaseModel = createSavedPurchaseModel();
        final var savedPurchaseEntity = createSavedPurchaseEntity();

        when(purchaseRepository.findById(anyLong())).thenReturn(Optional.of(savedPurchaseEntity));
        when(purchaseMapper.toModel(any())).thenReturn(savedPurchaseModel);

        assertDoesNotThrow(
                () -> {
                    assertNotNull(purchaseDatabaseAdapter.findById(savedPurchaseModel.getId()));
                }
        );
    }

    @Test
    @DisplayName("Should not find a purchase")
    public void shouldNotFindPurchase() {
        final var savedPurchaseModel = createSavedPurchaseModel();

        when(purchaseRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(
                PurchaseNotFoundException.class,
                () -> {
                    assertNotNull(purchaseDatabaseAdapter.findById(savedPurchaseModel.getId()));
                }
        );
    }
}
