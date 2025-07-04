package pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.infrastructure.config;

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
                        .title("Schedule Service API")
                        .version("1.0")
                        .description("API for Schedule Service"))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8010/schedule-service")
                                .description("Gateway Server")/*,
                        new Server()
                                .url("http://localhost:8097")
                                .description("Direct Service (Development)")*/
                ));
    }
}
