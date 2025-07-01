package com.learn.e_commerce;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI ecommerceOpenAPI(){
        return new OpenAPI()
                .info(
                        new Info()
                                .title("E-Commerce API")
                                .description("API documentation for root and product-categories module")
                                .version("1.0")
                );
    }
}
