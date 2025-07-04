package pe.upc.pawfectcaremicroservices.gatewayservice.infrastructure.config;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("PawfectCare Gateway API")
                        .version("1.0")
                        .description("API Gateway for PawfectCare Microservices"))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8010")
                                .description("Gateway Server")
                ));
    }

    @Bean
    @Lazy(false)
    public List<GroupedOpenApi> apis(
            RouteDefinitionLocator routeDefinitionLocator) {
        List<GroupedOpenApi> groups = new ArrayList<>();
        List<RouteDefinition> definitions = routeDefinitionLocator.getRouteDefinitions()
                .collectList().block();

        if (definitions != null) {
            definitions.stream()
                    .filter(routeDefinition -> routeDefinition.getId().matches(".*-service"))
                    .forEach(routeDefinition -> {
                        String name = routeDefinition.getId();
                        groups.add(GroupedOpenApi.builder()
                                .pathsToMatch("/" + name + "/**")
                                .group(name)
                                .addOpenApiCustomizer(openApiCustomizer())
                                .build());
                    });
        }
        return groups;
    }

    @Bean
    public OpenApiCustomizer openApiCustomizer() {
        return openApi -> {
            // Asegurar que todas las operaciones usen el servidor del gateway
            openApi.servers(List.of(
                    new Server()
                            .url("http://localhost:8010")
                            .description("Gateway Server")
            ));
        };
    }
}