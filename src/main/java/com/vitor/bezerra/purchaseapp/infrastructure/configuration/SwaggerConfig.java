package com.vitor.bezerra.purchaseapp.infrastructure.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .version("1.0")
                                .title("Purchase app")
                                .description("An app to store purchase")
                                .contact(new Contact().name("Vitor Henrique Coelho Bezerr").email("vitorhenrique908@gmail.com"))
                                .license(new License().name("(c) Copyright"))
                );
    }
}
