package pe.upc.pawfectcaremicroservices.medicalrecordservice.infrastructure.config;

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
                        .title("Medical Record Service API")
                        .version("1.0")
                        .description("API for Medical Record Service"))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8010/medical-record-service")
                                .description("Gateway Server")/*,
                        new Server()
                                .url("http://localhost:8099")
                                .description("Direct Service (Development)")*/
                ));
    }
}