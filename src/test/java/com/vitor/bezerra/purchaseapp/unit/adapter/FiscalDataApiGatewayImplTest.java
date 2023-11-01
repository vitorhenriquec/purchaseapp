package com.vitor.bezerra.purchaseapp.unit.adapter;

import com.vitor.bezerra.purchaseapp.domain.model.FiscalDataModel;
import com.vitor.bezerra.purchaseapp.infrastructure.client.FiscalDataApiGatewayImpl;
import com.vitor.bezerra.purchaseapp.infrastructure.client.FiscalDataClient;
import com.vitor.bezerra.purchaseapp.infrastructure.mapper.FiscalDataMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.vitor.bezerra.purchaseapp.unit.utils.MockHelper.createFirstExchangeRateResponsePage;
import static com.vitor.bezerra.purchaseapp.unit.utils.MockHelper.createSecondExchangeRateResponsePage;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.anyString;
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
    @DisplayName("Should successfully retrieve fiscal data from date")
    public void shouldSuccessfullyRetriveFiscalDataFromDate() {
        when(fiscalDataClient.getExchangeRateFilterByDate(anyString(), anyString(), eq(1), eq(100))).thenReturn(
                ResponseEntity.ok(
                        createFirstExchangeRateResponsePage()
                )
        );

        when(fiscalDataClient.getExchangeRateFilterByDate(anyString(), anyString(), eq(2), eq(100))).thenReturn(
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
