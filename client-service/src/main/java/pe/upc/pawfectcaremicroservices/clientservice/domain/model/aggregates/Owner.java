package pe.upc.pawfectcaremicroservices.clientservice.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;
import pe.upc.pawfectcaremicroservices.clientservice.domain.model.commands.CreateOwnerCommand;

@Getter
@Setter
@Entity
@Table(name = "owners")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String phoneNumber;
    private String email;
    /*private String dni;*/
    private String address;

    public Owner() {
        this.fullName = Strings.EMPTY;
        this.phoneNumber = Strings.EMPTY;
        this.email = Strings.EMPTY;
        this.address = Strings.EMPTY;
    }

    public Owner(CreateOwnerCommand command) {
        this();
        this.fullName = command.fullName();
        this.phoneNumber = command.phoneNumber();
        this.email = command.email();
        this.address = command.address();
    }
    public Owner updateInformation(String fullName, String phoneNumber, String email, String address) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        return this;
    }
}
