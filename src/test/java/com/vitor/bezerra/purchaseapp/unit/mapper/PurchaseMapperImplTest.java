package com.vitor.bezerra.purchaseapp.unit.mapper;

import com.vitor.bezerra.purchaseapp.infrastructure.mapper.PurchaseMapperImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.vitor.bezerra.purchaseapp.unit.utils.MockHelper.createSavedPurchaseEntity;
import static com.vitor.bezerra.purchaseapp.unit.utils.MockHelper.createSavedPurchaseModel;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {PurchaseMapperImpl.class})
public class PurchaseMapperImplTest {

    @Autowired
    private PurchaseMapperImpl purchaseMapper;

    @Test
    @DisplayName("Should convert to model")
    public void shouldConvertToModel() {
        final var purchaseEntity = createSavedPurchaseEntity();

        assertDoesNotThrow(
                () -> {
                    assertNotNull(purchaseMapper.toModel(purchaseEntity));
                }
        );
    }

    @Test
    @DisplayName("Should convert to entity")
    public void shouldConvertToEntity() {
        final var purchaseModel = createSavedPurchaseModel();

        assertDoesNotThrow(
                () -> {
                    assertNotNull(purchaseMapper.toEntity(purchaseModel));
                }
        );
    }
}
