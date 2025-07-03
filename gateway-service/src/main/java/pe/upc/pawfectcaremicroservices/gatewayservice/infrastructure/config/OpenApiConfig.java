package pe.upc.pawfectcaremicroservices.gatewayservice.infrastructure.config;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class OpenApiConfig {

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
                                .build());
                    });
        }
        return groups;
    }
}