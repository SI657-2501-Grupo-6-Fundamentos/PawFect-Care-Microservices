package pe.upc.pawfectcaremicroservices.petownerservice.interfaces.rest;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.upc.pawfectcaremicroservices.petownerservice.domain.model.queries.GetAllPetOwnersQuery;
import pe.upc.pawfectcaremicroservices.petownerservice.domain.model.queries.GetPetOwnerByIdQuery;
import pe.upc.pawfectcaremicroservices.petownerservice.domain.services.PetOwnerCommandService;
import pe.upc.pawfectcaremicroservices.petownerservice.domain.services.PetOwnerQueryService;
import pe.upc.pawfectcaremicroservices.petownerservice.interfaces.rest.resources.CreatePetOwnerResource;
import pe.upc.pawfectcaremicroservices.petownerservice.interfaces.rest.resources.PetOwnerResource;
import pe.upc.pawfectcaremicroservices.petownerservice.interfaces.rest.resources.UpdatePetOwnerResource;
import pe.upc.pawfectcaremicroservices.petownerservice.interfaces.rest.transform.CreatePetOwnerCommandFromResourceAssembler;
import pe.upc.pawfectcaremicroservices.petownerservice.interfaces.rest.transform.PetOwnerResourceFromEntityAssembler;
import pe.upc.pawfectcaremicroservices.petownerservice.interfaces.rest.transform.UpdatePetOwnerCommandFromResourceAssembler;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/pet-owners", produces = APPLICATION_JSON_VALUE)
public class PetOwnersController {

    private final PetOwnerQueryService petOwnerQueryService;
    private final PetOwnerCommandService petOwnerCommandService;
    public PetOwnersController(PetOwnerQueryService petOwnerQueryService, PetOwnerCommandService petOwnerCommandService) {
        this.petOwnerCommandService = petOwnerCommandService;
        this.petOwnerQueryService = petOwnerQueryService;
    }

    @GetMapping("/{petOwnerId}")
    public ResponseEntity<PetOwnerResource> getOwnerById(@PathVariable Long petOwnerId) {
        var getOwnerByIdQuery = new GetPetOwnerByIdQuery(petOwnerId);
        var owner = petOwnerQueryService.handle(getOwnerByIdQuery);
        if (owner.isEmpty()) return ResponseEntity.badRequest().build();
        var ownerResource = PetOwnerResourceFromEntityAssembler.toResourceFromEntity(owner.get());
        return ResponseEntity.ok(ownerResource);
    }

    @GetMapping
    public ResponseEntity<List<PetOwnerResource>> getAllOwners() {
        var getAllOwnersQuery = new GetAllPetOwnersQuery();
        var owners = petOwnerQueryService.handle(getAllOwnersQuery);
        var petResources = owners.stream().map(PetOwnerResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(petResources);
    }

    @PostMapping
    public ResponseEntity<PetOwnerResource> createOwner(@RequestBody CreatePetOwnerResource createPetOwnerResource) {
        var createOwnerCommand = CreatePetOwnerCommandFromResourceAssembler.toCommandFromResource(createPetOwnerResource);
        var ownerId = petOwnerCommandService.handle(createOwnerCommand);
        if (ownerId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getOwnerByIdQuery = new GetPetOwnerByIdQuery(ownerId);
        var owner = petOwnerQueryService.handle(getOwnerByIdQuery);
     if (owner.isEmpty()) return ResponseEntity.badRequest().build();
     var ownerResource = PetOwnerResourceFromEntityAssembler.toResourceFromEntity(owner.get());
        return new ResponseEntity<>(ownerResource, HttpStatus.CREATED);
    }

    @PutMapping("/{petOwnerId}")
    public ResponseEntity<PetOwnerResource> updateOwner(@PathVariable Long petOwnerId, @RequestBody UpdatePetOwnerResource updatePetOwnerResource) {
        var updateOwnerCommand = UpdatePetOwnerCommandFromResourceAssembler.toCommandFromResource(petOwnerId, updatePetOwnerResource);
        var updatedOwner = petOwnerCommandService.handle(updateOwnerCommand);
        if (updatedOwner.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var ownerResource = PetOwnerResourceFromEntityAssembler.toResourceFromEntity(updatedOwner.get());
        return ResponseEntity.ok(ownerResource);
    }

}
