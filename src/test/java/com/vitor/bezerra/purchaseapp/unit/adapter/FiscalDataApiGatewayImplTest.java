package com.vitor.bezerra.purchaseapp.unit.adapter;

import com.vitor.bezerra.purchaseapp.domain.model.FiscalDataModel;
import com.vitor.bezerra.purchaseapp.infrastructure.adapter.PurchaseDatabaseAdapter;
import com.vitor.bezerra.purchaseapp.infrastructure.client.FiscalDataApiGatewayImpl;
import com.vitor.bezerra.purchaseapp.infrastructure.client.FiscalDataClient;
import com.vitor.bezerra.purchaseapp.infrastructure.client.exchange.ExchangeRateResponse;
import com.vitor.bezerra.purchaseapp.infrastructure.mapper.FiscalDataMapper;
import com.vitor.bezerra.purchaseapp.infrastructure.mapper.PurchaseMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.vitor.bezerra.purchaseapp.unit.utils.MockHelper.createFirstExchangeRateResponsePage;
import static com.vitor.bezerra.purchaseapp.unit.utils.MockHelper.createNewValidPurchaseModel;
import static com.vitor.bezerra.purchaseapp.unit.utils.MockHelper.createSavedPurchaseEntity;
import static com.vitor.bezerra.purchaseapp.unit.utils.MockHelper.createSavedPurchaseModel;
import static com.vitor.bezerra.purchaseapp.unit.utils.MockHelper.createSecondExchangeRateResponsePage;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {FiscalDataApiGatewayImpl.class, FiscalDataMapper.class})
public class FiscalDataApiGatewayImplTest {

    @Autowired
    private FiscalDataApiGatewayImpl fiscalDataApiGatewayImpl;

    @MockBean
    private FiscalDataMapper fiscalDataMapper;

    @MockBean
    private FiscalDataClient fiscalDataClient;

    @Test
    @DisplayName("Should successfully save")
    public void shouldSuccessfullySave() {
        when(fiscalDataClient.getExchangeRateFilterByDate(any(), any(), 1, any())).thenReturn(
                ResponseEntity.ok(
                        createFirstExchangeRateResponsePage()
                )
        );

        when(fiscalDataClient.getExchangeRateFilterByDate(any(), any(), 2, any())).thenReturn(
                ResponseEntity.ok(
                        createSecondExchangeRateResponsePage()
                )
        );

        when(fiscalDataMapper.toModel(any())).thenReturn(
                new FiscalDataModel("United Arab Emirates-Dirham", new BigDecimal("3.673"))
        );

        assertDoesNotThrow(
                () -> {
                    assertNotNull(fiscalDataApiGatewayImpl.retriveFiscalDataByDate(LocalDate.now().toString()));
                }
        );
    }
}
