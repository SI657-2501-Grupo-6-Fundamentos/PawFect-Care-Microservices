package pe.upc.pawfectcaremicroservices.profile_service.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.upc.pawfectcaremicroservices.profile_service.domain.model.valueobjects.ProfileType;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long iamUserId;

    @Enumerated(EnumType.STRING)
    private ProfileType type;

    // Otros campos de perfil...

    public Profile(Long iamUserId, ProfileType type) {
        this.iamUserId = iamUserId;
        this.type = type;
    }
}