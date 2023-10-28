package com.vitor.bezerra.purchaseapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.commons.httpclient.HttpClientConfiguration;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;

@SpringBootApplication
@ImportAutoConfiguration(classes = {FeignAutoConfiguration.class, HttpClientConfiguration.class})
public class PurchaseappApplication {

	public static void main(String[] args) {
		SpringApplication.run(PurchaseappApplication.class, args);
	}

}
