package com.vitor.bezerra.purchaseapp.unit.mapper;

import com.vitor.bezerra.purchaseapp.infrastructure.client.exchange.ExchangeRateResponse;
import com.vitor.bezerra.purchaseapp.infrastructure.mapper.FiscalDataMapperImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static com.vitor.bezerra.purchaseapp.unit.utils.MockHelper.createFirstExchangeRateResponsePage;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles(profiles = "test")
@SpringBootTest(classes = {FiscalDataMapperImpl.class})
public class FiscalDataMapperImplTest {

    @Autowired
    private FiscalDataMapperImpl fiscalDataMapper;

    @Test
    @DisplayName("Should convert to model")
    public void shouldConvertToModel() {
        final ExchangeRateResponse exchangeRateResponse = createFirstExchangeRateResponsePage();


        assertDoesNotThrow(
                () -> {
                    assertNotNull(fiscalDataMapper.toModel(exchangeRateResponse.getData().get(0)));
                }
        );
    }
}
