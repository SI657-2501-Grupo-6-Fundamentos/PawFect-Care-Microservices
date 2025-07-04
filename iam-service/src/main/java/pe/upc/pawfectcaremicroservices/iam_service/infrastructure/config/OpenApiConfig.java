package pe.upc.pawfectcaremicroservices.iam_service.infrastructure.config;

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
                        .title("IAM Service API")
                        .version("1.0")
                        .description("API for IAM Service"))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8010/iam-service")
                                .description("Gateway Server")/*,
                        new Server()
                                .url("http://localhost:8122")
                                .description("Direct Service (Development)")*/
                ));
    }
}