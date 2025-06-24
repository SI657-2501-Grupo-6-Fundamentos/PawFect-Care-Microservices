package pe.upc.pawfectcaremicroservices.account_service.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Email {
    private String value;
}