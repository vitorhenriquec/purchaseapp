package com.vitor.bezerra.purchaseapp.infrastructure.client;

import com.vitor.bezerra.purchaseapp.infrastructure.client.exchange.ExchangeRateResponse;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(name = "fiscalDataClient", url = "${feign.client.config.fiscalDataApi.url}")
public interface FiscalDataClient {
    @GetMapping(
            path = "",
            produces = APPLICATION_JSON_VALUE
    )
    ResponseEntity<ExchangeRateResponse> getExchangeRateFilterByDate(
            @RequestParam(name = "fields") final String fields,
            @RequestParam(name = "filter") final String filter
    );
}
