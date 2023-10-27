package com.vitor.bezerra.purchaseapp.infrastructure.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "fiscalDataApi", url="${feign.client.config.fiscalData.url}")
public interface FiscalDataApi {

}
