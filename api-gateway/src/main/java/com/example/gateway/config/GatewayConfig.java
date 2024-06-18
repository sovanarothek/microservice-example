package com.example.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth-service", r -> r.path("/auth-service/**")
                        .uri("lb://AUTH-SERVICE/"))
                .route("order-service", r -> r.path("/v1/order-service/**")
                        .uri("lb://ORDER-SERVICE/"))
                .route("product-service", r -> r.path("/v1/product-service/**")
                        .uri("lb://PRODUCT-SERVICE"))
                .build();
    }
}
