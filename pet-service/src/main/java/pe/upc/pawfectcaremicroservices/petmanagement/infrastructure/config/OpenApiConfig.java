package pe.upc.pawfectcaremicroservices.petmanagement.infrastructure.config;

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
                        .title("Pet Service API")
                        .version("1.0")
                        .description("API for Pet Service"))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8010/pet-service")
                                .description("Gateway Server")/*,
                        new Server()
                                .url("http://localhost:8093")
                                .description("Direct Service (Development)")*/
                ));
    }
}
