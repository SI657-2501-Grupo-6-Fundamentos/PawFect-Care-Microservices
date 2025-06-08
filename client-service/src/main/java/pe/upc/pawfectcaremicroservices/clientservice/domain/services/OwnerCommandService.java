package pe.upc.pawfectcaremicroservices.clientservice.domain.services;

import pe.upc.pawfectcaremicroservices.clientservice.domain.model.aggregates.Owner;
import pe.upc.pawfectcaremicroservices.clientservice.domain.model.commands.CreateOwnerCommand;
import pe.upc.pawfectcaremicroservices.clientservice.domain.model.commands.UpdateOwnerCommand;

import java.util.Optional;

public interface OwnerCommandService {
    Optional<Owner> handle(UpdateOwnerCommand command);
    Long handle(CreateOwnerCommand command);

}
