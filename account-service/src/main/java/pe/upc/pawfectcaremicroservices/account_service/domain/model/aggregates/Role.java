package pe.upc.pawfectcaremicroservices.account_service.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.*;
import pe.upc.pawfectcaremicroservices.account_service.domain.model.valueobjects.RoleName;

@Entity
@Table(name = "roles")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private RoleName name;
}