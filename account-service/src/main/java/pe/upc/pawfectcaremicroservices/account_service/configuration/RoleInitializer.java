package pe.upc.pawfectcaremicroservices.account_service.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pe.upc.pawfectcaremicroservices.account_service.domain.model.aggregates.Role;
import pe.upc.pawfectcaremicroservices.account_service.domain.model.valueobjects.RoleName;
import pe.upc.pawfectcaremicroservices.account_service.infrastructure.persistence.jpa.repositories.JpaRoleRepository;

@Configuration
public class RoleInitializer {
    @Bean
    public CommandLineRunner initRoles(JpaRoleRepository roleRepository) {
        return args -> {
            for (RoleName roleName : RoleName.values()) {
                roleRepository.findByName(roleName)
                        .orElseGet(() -> roleRepository.save(Role.builder().name(roleName).build()));
            }
        };
    }
}