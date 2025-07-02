package pe.upc.pawfectcaremicroservices.petmanagement.interfaces.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.queries.GetAllPetsByOwnerIdQuery;
import pe.upc.pawfectcaremicroservices.petmanagement.domain.services.PetQueryService;
import pe.upc.pawfectcaremicroservices.petmanagement.interfaces.rest.resources.PetResource;
import pe.upc.pawfectcaremicroservices.petmanagement.interfaces.rest.transform.PetResourceFromEntityAssembler;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/pet-owners/{ownerId}/pets", produces = APPLICATION_JSON_VALUE)
public class OwnerPetsController {
    private final PetQueryService petQueryService;

    public OwnerPetsController(PetQueryService petQueryService) {
        this.petQueryService = petQueryService;
    }

    @GetMapping
    public ResponseEntity<List<PetResource>> getAllPetsByOwnerId(@PathVariable Long ownerId) {
        var getAllPetsByOwnerIdQuery = new GetAllPetsByOwnerIdQuery(ownerId);
        var pets = petQueryService.handle(getAllPetsByOwnerIdQuery);
        var petResources = pets.stream().map(PetResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(petResources);

    }

}
