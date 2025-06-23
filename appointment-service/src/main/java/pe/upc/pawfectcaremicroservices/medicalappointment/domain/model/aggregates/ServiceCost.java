package pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.valueobjects.ServiceName;

@Getter
@Setter
@Entity
@Table(name = "service_costs")
public class ServiceCost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ServiceName serviceName;

    private Float cost;

    private Float minimumCost;

    private Float maximumCost;

    public ServiceCost() {
        this.serviceName = ServiceName.GENERAL_MEDICINE;
        this.minimumCost = 0.0f;
        this.maximumCost = 2000.0f;
        this.cost = minimumCost;
    }

}
