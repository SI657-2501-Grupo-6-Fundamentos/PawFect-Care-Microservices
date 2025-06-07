package pe.upc.pawfectcaremicroservices.petmanagement.domain.services;

import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.aggregates.Owner;
import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.commands.CreateOwnerCommand;
import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.commands.UpdateOwnerCommand;

import java.util.Optional;

public interface OwnerCommandService {
    Optional<Owner> handle(UpdateOwnerCommand command);
    Long handle(CreateOwnerCommand command);

}
