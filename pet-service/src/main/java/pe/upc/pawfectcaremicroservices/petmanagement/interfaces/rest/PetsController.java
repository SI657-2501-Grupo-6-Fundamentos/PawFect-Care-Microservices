package pe.upc.pawfectcaremicroservices.petmanagement.interfaces.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.commands.DeletePetCommand;
import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.queries.GetAllPetsQuery;
import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.queries.GetPetByIdQuery;
import pe.upc.pawfectcaremicroservices.petmanagement.domain.services.PetCommandService;
import pe.upc.pawfectcaremicroservices.petmanagement.domain.services.PetQueryService;
import pe.upc.pawfectcaremicroservices.petmanagement.interfaces.rest.resources.CreatePetResource;
import pe.upc.pawfectcaremicroservices.petmanagement.interfaces.rest.resources.PetResource;
import pe.upc.pawfectcaremicroservices.petmanagement.interfaces.rest.resources.UpdatePetResource;
import pe.upc.pawfectcaremicroservices.petmanagement.interfaces.rest.transform.CreatePetCommandFromResourceAssembler;
import pe.upc.pawfectcaremicroservices.petmanagement.interfaces.rest.transform.PetResourceFromEntityAssembler;
import pe.upc.pawfectcaremicroservices.petmanagement.interfaces.rest.transform.UpdatePetCommandFromResourceAssembler;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/pets", produces = APPLICATION_JSON_VALUE)
public class PetsController {

    private final PetQueryService petQueryService;
    private final PetCommandService petCommandService;
    public PetsController(PetQueryService petQueryService,PetCommandService petCommandService) {
        this.petQueryService = petQueryService;
        this.petCommandService = petCommandService;
    }

    @PostMapping
    public ResponseEntity<PetResource> createPet(@RequestBody CreatePetResource createPetResource) {
        var createPetCommand = CreatePetCommandFromResourceAssembler.toCommandFromResource(createPetResource);
        var petId = petCommandService.handle(createPetCommand);
        if (petId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getPetByIdQuery = new GetPetByIdQuery(petId);
        var pet = petQueryService.handle(getPetByIdQuery);
        if (pet.isEmpty()) return ResponseEntity.badRequest().build();
        var petResource = PetResourceFromEntityAssembler.toResourceFromEntity(pet.get());
        return new ResponseEntity<>(petResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PetResource>> getAllPets() {
        var getAllPetsQuery = new GetAllPetsQuery();
        var pets = petQueryService.handle(getAllPetsQuery);
        var petResources = pets.stream().map(PetResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(petResources);
    }

    @GetMapping("/{petId}")
    public ResponseEntity<PetResource> getPetById(@PathVariable Long petId) {
        var getPetByIdQuery = new GetPetByIdQuery(petId);
        var pet = petQueryService.handle(getPetByIdQuery);
        if (pet.isEmpty()) return ResponseEntity.badRequest().build();
        var petResource = PetResourceFromEntityAssembler.toResourceFromEntity(pet.get());
        return ResponseEntity.ok(petResource);
    }

    @PutMapping("/{petId}")
    public ResponseEntity<PetResource> updatePet(@PathVariable Long petId, @RequestBody UpdatePetResource updatePetResource) {
        var updatePetCommand = UpdatePetCommandFromResourceAssembler.toCommandFromResource(petId, updatePetResource);
        var updatedPet = petCommandService.handle(updatePetCommand);
        if (updatedPet.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var petResource = PetResourceFromEntityAssembler.toResourceFromEntity(updatedPet.get());
        return ResponseEntity.ok(petResource);
    }

    @DeleteMapping("/{petId}")
    public ResponseEntity<?> deletePet(@PathVariable Long petId) {
        var deletePetCommand = new DeletePetCommand(petId);
        petCommandService.handle(deletePetCommand);
        return ResponseEntity.ok("Pet with given id successfully deleted");
    }


}
