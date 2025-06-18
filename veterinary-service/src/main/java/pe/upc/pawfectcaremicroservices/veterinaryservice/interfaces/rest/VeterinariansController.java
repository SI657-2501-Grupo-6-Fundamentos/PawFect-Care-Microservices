package pe.upc.pawfectcaremicroservices.veterinaryservice.interfaces.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.upc.pawfectcaremicroservices.veterinaryservice.domain.model.queries.GetAllVeterinariansBySpecialityQuery;
import pe.upc.pawfectcaremicroservices.veterinaryservice.domain.model.queries.GetAllVeterinariansQuery;
import pe.upc.pawfectcaremicroservices.veterinaryservice.domain.model.queries.GetVeterinariansByIdQuery;
import pe.upc.pawfectcaremicroservices.veterinaryservice.domain.model.valueobjects.VeterinarianSpeciality;
import pe.upc.pawfectcaremicroservices.veterinaryservice.domain.services.VeterinarianCommandService;
import pe.upc.pawfectcaremicroservices.veterinaryservice.domain.services.VeterinarianQueryService;
import pe.upc.pawfectcaremicroservices.veterinaryservice.interfaces.rest.resources.CreateVeterinarianResource;
import pe.upc.pawfectcaremicroservices.veterinaryservice.interfaces.rest.resources.UpdateVeterinarianResource;
import pe.upc.pawfectcaremicroservices.veterinaryservice.interfaces.rest.resources.VeterinarianResource;
import pe.upc.pawfectcaremicroservices.veterinaryservice.interfaces.rest.transform.CreateVeterinarianCommandFromResourceAssembler;
import pe.upc.pawfectcaremicroservices.veterinaryservice.interfaces.rest.transform.UpdateVeterinarianCommandFromResourceAssembler;
import pe.upc.pawfectcaremicroservices.veterinaryservice.interfaces.rest.transform.VeterinarianResourceFromEntityAssembler;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/veterinarians", produces = APPLICATION_JSON_VALUE)
public class VeterinariansController {
    private final VeterinarianQueryService veterinarianQueryService;
    private final VeterinarianCommandService veterinarianCommandService;
    public VeterinariansController(VeterinarianQueryService veterinarianQueryService,VeterinarianCommandService veterinarianCommandService) {
        this.veterinarianCommandService = veterinarianCommandService;
        this.veterinarianQueryService = veterinarianQueryService;
    }

    @GetMapping("/{veterinarianId}")
    public ResponseEntity<VeterinarianResource> getVeterinarianById(@PathVariable Long veterinarianId) {
        var getVeterinarianByIdQuery = new GetVeterinariansByIdQuery(veterinarianId);
        var veterinarian = veterinarianQueryService.handle(getVeterinarianByIdQuery);
        if (veterinarian.isEmpty()) return ResponseEntity.badRequest().build();
        var veterinarianResource = VeterinarianResourceFromEntityAssembler.toResourceFromEntity(veterinarian.get());
        return ResponseEntity.ok(veterinarianResource);
    }

    @GetMapping
    public ResponseEntity<List<VeterinarianResource>> getAllVeterinarians() {
        var getAllVeterinariansQuery = new GetAllVeterinariansQuery();
        var veterinarians = veterinarianQueryService.handle(getAllVeterinariansQuery);
        var petResources = veterinarians.stream().map(VeterinarianResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(petResources);
    }

    @GetMapping("/speciality")
    public ResponseEntity<List<VeterinarianResource>> getVeterinariansBySpeciality(@RequestParam VeterinarianSpeciality speciality) {
        var getVeterinariansBySpecialityQuery = new GetAllVeterinariansBySpecialityQuery(speciality);
        var veterinarians = veterinarianQueryService.handle(getVeterinariansBySpecialityQuery);
        var veterinarianResources = veterinarians.stream().map(VeterinarianResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(veterinarianResources);
    }

    @PostMapping
    public ResponseEntity<VeterinarianResource> createVeterinarian(@RequestBody CreateVeterinarianResource createVeterinarianResource) {
        var createVeterinarianCommand = CreateVeterinarianCommandFromResourceAssembler.toCommandFromResource(createVeterinarianResource);
        var veterinarianId = veterinarianCommandService.handle(createVeterinarianCommand);
        if (veterinarianId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getVeterinarianByIdQuery = new GetVeterinariansByIdQuery(veterinarianId);
        var veterinarian = veterinarianQueryService.handle(getVeterinarianByIdQuery);
        if (veterinarian.isEmpty()) return ResponseEntity.badRequest().build();
        var veterinarianResource = VeterinarianResourceFromEntityAssembler.toResourceFromEntity(veterinarian.get());
        return new ResponseEntity<>(veterinarianResource, HttpStatus.CREATED);
    }

    @PutMapping("/{veterinarianId}")
    public ResponseEntity<VeterinarianResource> updateVeterinarian(@PathVariable Long veterinarianId, @RequestBody UpdateVeterinarianResource updateVeterinarianResource) {
        var updateVeterinarianCommand = UpdateVeterinarianCommandFromResourceAssembler.toCommandFromResource(veterinarianId, updateVeterinarianResource);
        var updatedVeterinarian = veterinarianCommandService.handle(updateVeterinarianCommand);
        if (updatedVeterinarian.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var veterinarianResource = VeterinarianResourceFromEntityAssembler.toResourceFromEntity(updatedVeterinarian.get());
        return ResponseEntity.ok(veterinarianResource);
    }
}
