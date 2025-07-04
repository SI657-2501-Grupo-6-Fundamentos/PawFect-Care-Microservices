package pe.upc.pawfectcaremicroservices.review.infrastructure.config;

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
                        .title("Review Service API")
                        .version("1.0")
                        .description("API for Review Service"))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8010/review-service")
                                .description("Gateway Server")/*,
                        new Server()
                                .url("http://localhost:8095")
                                .description("Direct Service (Development)")*/
                ));
    }
}
