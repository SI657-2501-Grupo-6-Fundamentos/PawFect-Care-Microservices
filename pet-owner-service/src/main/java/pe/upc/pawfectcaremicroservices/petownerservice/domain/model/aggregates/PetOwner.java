package pe.upc.pawfectcaremicroservices.petownerservice.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;
import pe.upc.pawfectcaremicroservices.petownerservice.domain.model.commands.CreatePetOwnerCommand;

@Getter
@Setter
@Entity
@Table(name = "pet_owners")
public class PetOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String fullName;
    private String phoneNumber;
    private String email;
    /*private String dni;*/
    private String address;

    public PetOwner() {
        this.fullName = Strings.EMPTY;
        this.phoneNumber = Strings.EMPTY;
        this.email = Strings.EMPTY;
        this.address = Strings.EMPTY;
    }

    public PetOwner(CreatePetOwnerCommand command) {
        this();
        this.userId = command.userId();
        this.fullName = command.fullName();
        this.phoneNumber = command.phoneNumber();
        this.email = command.email();
        this.address = command.address();
    }
    public PetOwner updateInformation(String fullName, String phoneNumber, String email, String address) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        return this;
    }
}
