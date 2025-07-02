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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/pets", produces = APPLICATION_JSON_VALUE)
public class PetsController {

    private final PetQueryService petQueryService;
    private final PetCommandService petCommandService;

    public PetsController(PetQueryService petQueryService, PetCommandService petCommandService) {
        this.petQueryService = petQueryService;
        this.petCommandService = petCommandService;
    }

    @PostMapping
    public ResponseEntity<?> createPet(@RequestBody CreatePetResource createPetResource) {
        try {
            var createPetCommand = CreatePetCommandFromResourceAssembler.toCommandFromResource(createPetResource);
            var petId = petCommandService.handle(createPetCommand);

            if (petId == 0L) {
                return ResponseEntity.badRequest()
                        .body(createErrorResponse("Failed to create pet", "Invalid pet data provided"));
            }

            var getPetByIdQuery = new GetPetByIdQuery(petId);
            var pet = petQueryService.handle(getPetByIdQuery);

            if (pet.isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(createErrorResponse("Pet creation failed", "Could not retrieve created pet"));
            }

            var petResource = PetResourceFromEntityAssembler.toResourceFromEntity(pet.get());
            return new ResponseEntity<>(petResource, HttpStatus.CREATED);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(createErrorResponse("Invalid input", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(createErrorResponse("Internal server error", "An unexpected error occurred"));
        }
    }

    @GetMapping
    public ResponseEntity<List<PetResource>> getAllPets() {
        try {
            var getAllPetsQuery = new GetAllPetsQuery();
            var pets = petQueryService.handle(getAllPetsQuery);
            var petResources = pets.stream()
                    .map(PetResourceFromEntityAssembler::toResourceFromEntity)
                    .toList();
            return ResponseEntity.ok(petResources);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @GetMapping("/{petId}")
    public ResponseEntity<?> getPetById(@PathVariable Long petId) {
        try {
            if (petId == null || petId <= 0) {
                return ResponseEntity.badRequest()
                        .body(createErrorResponse("Invalid pet ID", "Pet ID must be a positive number"));
            }

            var getPetByIdQuery = new GetPetByIdQuery(petId);
            var pet = petQueryService.handle(getPetByIdQuery);

            if (pet.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(createErrorResponse("Pet not found", "No pet found with ID: " + petId));
            }

            var petResource = PetResourceFromEntityAssembler.toResourceFromEntity(pet.get());
            return ResponseEntity.ok(petResource);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(createErrorResponse("Internal server error", "An unexpected error occurred"));
        }
    }

    @PutMapping("/{petId}")
    public ResponseEntity<?> updatePet(@PathVariable Long petId, @RequestBody UpdatePetResource updatePetResource) {
        try {
            if (petId == null || petId <= 0) {
                return ResponseEntity.badRequest()
                        .body(createErrorResponse("Invalid pet ID", "Pet ID must be a positive number"));
            }

            var updatePetCommand = UpdatePetCommandFromResourceAssembler.toCommandFromResource(petId, updatePetResource);
            var updatedPet = petCommandService.handle(updatePetCommand);

            if (updatedPet.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(createErrorResponse("Update failed", "Could not update pet with ID: " + petId));
            }

            var petResource = PetResourceFromEntityAssembler.toResourceFromEntity(updatedPet.get());
            return ResponseEntity.ok(petResource);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(createErrorResponse("Invalid input", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(createErrorResponse("Internal server error", "An unexpected error occurred"));
        }
    }

    @DeleteMapping("/{petId}")
    public ResponseEntity<?> deletePet(@PathVariable Long petId) {
        try {
            if (petId == null || petId <= 0) {
                return ResponseEntity.badRequest()
                        .body(createErrorResponse("Invalid pet ID", "Pet ID must be a positive number"));
            }

            var deletePetCommand = new DeletePetCommand(petId);
            petCommandService.handle(deletePetCommand);

            Map<String, String> response = new HashMap<>();
            response.put("message", "Pet with ID " + petId + " successfully deleted");
            response.put("status", "success");

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(createErrorResponse("Delete failed", "Could not delete pet with ID: " + petId));
        }
    }

    /**
     * Helper method to create consistent error response format
     */
    private Map<String, String> createErrorResponse(String error, String message) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", error);
        errorResponse.put("message", message);
        errorResponse.put("status", "error");
        return errorResponse;
    }
}