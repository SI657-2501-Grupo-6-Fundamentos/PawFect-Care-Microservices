package pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.commands.CreateTariffCommand;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.valueobjects.ServiceName;

@Getter
@Setter
@Entity
@Table(name = "tariffs")
public class Tariff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ServiceName serviceName;

    private Float cost;

    private Float minimumCost;

    private Float maximumCost;

    public Tariff() {
        this.serviceName = ServiceName.GENERAL_MEDICINE;
        this.minimumCost = 0.0f;
        this.maximumCost = 2000.0f;
        this.cost = minimumCost;
    }

    public Tariff(CreateTariffCommand command) {
        this();
        this.serviceName = command.serviceName();
        this.cost = command.cost();
        this.minimumCost = command.minimumCost();
        this.maximumCost = command.maximumCost();
    }

    public Tariff updateInformation(
            ServiceName serviceName,
            Float cost,
            Float minimumCost,
            Float maximumCost
    ) {
        this.serviceName = serviceName;
        this.cost = cost;
        this.minimumCost = minimumCost;
        this.maximumCost = maximumCost;
        return this;
    }

}
