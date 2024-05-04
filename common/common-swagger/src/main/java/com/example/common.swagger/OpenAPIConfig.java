package com.example.common.swagger;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@SecurityScheme(
        name = "Authorization",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
@Configuration
public class OpenAPIConfig {
    private String localUrl = "http://127.0.0.1:9770";

    @Bean
    public OpenAPI myOpenAPI() {
        Server localServer = new Server();
        localServer.setUrl(localUrl);
        localServer.setDescription("Local Server URL in Development environment");

        Contact contact = new Contact();
        contact.setEmail("limchansamnang@gmail.com");
        contact.setName("Samnang ( Software Engineer )");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("Microservice Example")
                .version("1.0")
                .contact(contact)
                .description("The multiple game result display system.").termsOfService("https://www.s.com/terms")
                .license(mitLicense);

        return new OpenAPI().info(info);
    }
}
