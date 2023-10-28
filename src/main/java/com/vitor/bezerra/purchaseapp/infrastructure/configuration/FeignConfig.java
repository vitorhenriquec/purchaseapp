package com.vitor.bezerra.purchaseapp.infrastructure.configuration;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients("com.vitor.bezerra.purchaseapp")
public class FeignConfig {
}
