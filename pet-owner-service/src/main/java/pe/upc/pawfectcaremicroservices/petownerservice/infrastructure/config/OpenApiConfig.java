package pe.upc.pawfectcaremicroservices.petownerservice.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Pet Owner Service API")
                        .version("1.0")
                        .description("API for Pet Owner Service"))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8010/pet-owner-service")
                                .description("Gateway Server"),
                        new Server()
                                .url("http://localhost:8094")
                                .description("Direct Service (Development)")
                ));
    }
}