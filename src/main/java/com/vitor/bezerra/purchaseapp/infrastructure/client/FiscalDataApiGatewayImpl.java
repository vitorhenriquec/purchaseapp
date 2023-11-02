package com.vitor.bezerra.purchaseapp.infrastructure.client;

import com.vitor.bezerra.purchaseapp.domain.gateway.FiscalDataApiGateway;
import com.vitor.bezerra.purchaseapp.domain.model.FiscalDataModel;
import com.vitor.bezerra.purchaseapp.infrastructure.client.exchange.ExchangeRateResponse;
import com.vitor.bezerra.purchaseapp.infrastructure.mapper.FiscalDataMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.vitor.bezerra.purchaseapp.infrastructure.configuration.CacheConfig.FISCAL_DATA_CACHE_NAME;

@Log4j2
@AllArgsConstructor
public class FiscalDataApiGatewayImpl implements FiscalDataApiGateway {

    private final FiscalDataClient fiscalDataClient;

    private final FiscalDataMapper fiscalDataMapper;

    private static final int PAGE_NUMBER = 1;

    private static final int PAGE_SIZE = 100;

    private static final String FISCAL_DATA_FIELDS = "country_currency_desc, exchange_rate";

    public static final String FISCAL_DATA_FILTER = "record_date:eq:";

    @Override
    @Cacheable(FISCAL_DATA_CACHE_NAME)
    public List<FiscalDataModel> retriveFiscalDataByDate(final String recordDate) {
        log.info("method={}; message={};", "retriveFiscalDataByDate", String.join(" ", "The process of retrieving fiscal data info from", recordDate));

        int actualPage = PAGE_NUMBER;

        final ResponseEntity<ExchangeRateResponse> exchangeRateResponse = fiscalDataClient.getExchangeRateFilterByDate(
                FISCAL_DATA_FIELDS,
                String.join("", FISCAL_DATA_FILTER, recordDate),
                actualPage,
                PAGE_SIZE
        );

        if (Objects.nonNull(exchangeRateResponse.getBody()) && exchangeRateResponse.getBody().getData().size() == 0) {
            return List.of();
        }

        final int totalPages = (Integer) exchangeRateResponse.getBody().getMeta().get("total-pages");

        List<FiscalDataModel> fiscalDataModels = new ArrayList<>(exchangeRateResponse.getBody().getData().stream().map(
                fiscalDataMapper::toModel
        ).toList());

        addPaginatedItems(recordDate, actualPage, totalPages, fiscalDataModels);

        return fiscalDataModels;
    }

    private void addPaginatedItems(final String recordDate, int actualPage, final int totalPages, List<FiscalDataModel> fiscalDataModels) {
        while(actualPage < totalPages) {
            actualPage +=1;
            fiscalDataModels.addAll(
                    Objects.requireNonNull(fiscalDataClient.getExchangeRateFilterByDate(
                            FISCAL_DATA_FIELDS,
                            String.join("", FISCAL_DATA_FILTER, recordDate),
                            actualPage,
                            PAGE_SIZE
                    ).getBody()).getData().stream().map(
                    fiscalDataMapper::toModel
            ).toList());
        }
    }
}
