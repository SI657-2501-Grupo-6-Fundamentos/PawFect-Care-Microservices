package pe.upc.pawfectcaremicroservices.treatmentservice.interfaces.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.upc.pawfectcaremicroservices.treatmentservice.domain.model.queries.GetAllDiagnosticByDiagnosticTypeQuery;
import pe.upc.pawfectcaremicroservices.treatmentservice.domain.model.queries.GetAllDiagnosticQuery;
import pe.upc.pawfectcaremicroservices.treatmentservice.domain.model.queries.GetDiagnosticByIdQuery;
import pe.upc.pawfectcaremicroservices.treatmentservice.domain.model.valueobjects.DiagnosticType;
import pe.upc.pawfectcaremicroservices.treatmentservice.domain.services.DiagnosticCommandService;
import pe.upc.pawfectcaremicroservices.treatmentservice.domain.services.DiagnosticQueryService;
import pe.upc.pawfectcaremicroservices.treatmentservice.interfaces.rest.resources.CreateDiagnosticResource;
import pe.upc.pawfectcaremicroservices.treatmentservice.interfaces.rest.resources.DiagnosticResource;
import pe.upc.pawfectcaremicroservices.treatmentservice.interfaces.rest.resources.UpdateDiagnosticResource;
import pe.upc.pawfectcaremicroservices.treatmentservice.interfaces.rest.transform.CreateDiagnosticCommandFromResourceAssembler;
import pe.upc.pawfectcaremicroservices.treatmentservice.interfaces.rest.transform.DiagnosticResourceFromEntityAssembler;
import pe.upc.pawfectcaremicroservices.treatmentservice.interfaces.rest.transform.UpdateDiagnosticCommandFromResourceAssembler;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "api/v1/treatments", produces = APPLICATION_JSON_VALUE)
public class TreatmentController {
    private final DiagnosticQueryService treatmentQueryService;
    private final DiagnosticCommandService treatmentCommandService;
    public TreatmentController(DiagnosticQueryService treatmentQueryService, DiagnosticCommandService treatmentCommandService) {
        this.treatmentCommandService = treatmentCommandService;
        this.treatmentQueryService = treatmentQueryService;
    }

    @GetMapping("/{treatmentId}")
    public ResponseEntity<DiagnosticResource> getDiagnosticById(@PathVariable Long treatmentId) {
        var getDiagnosticByIdQuery = new GetDiagnosticByIdQuery(treatmentId);
        var treatment = treatmentQueryService.handle(getDiagnosticByIdQuery);
        if (treatment.isEmpty()) return ResponseEntity.badRequest().build();
        var treatmentResource = DiagnosticResourceFromEntityAssembler.toResourceFromEntity(treatment.get());
        return ResponseEntity.ok(treatmentResource);
    }

    @GetMapping
    public ResponseEntity<List<DiagnosticResource>> getAllDiagnostics() {
        var getAllDiagnosticsQuery = new GetAllDiagnosticQuery();
        var treatments = treatmentQueryService.handle(getAllDiagnosticsQuery);
        var petResources = treatments.stream().map(DiagnosticResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(petResources);
    }

    @GetMapping("/treatmentType")
    public ResponseEntity<List<DiagnosticResource>> getDiagnosticsByType(@RequestParam DiagnosticType treatmentType) {
        var getDiagnosticByDiagnosticType = new GetAllDiagnosticByDiagnosticTypeQuery(treatmentType);
        var treatments = treatmentQueryService.handle(getDiagnosticByDiagnosticType);
        var treatmentResources = treatments.stream().map(DiagnosticResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(treatmentResources);
    }

    @PostMapping
    public ResponseEntity<DiagnosticResource> createDiagnostic(@RequestBody CreateDiagnosticResource createDiagnosticResource) {
        var createDiagnosticCommand = CreateDiagnosticCommandFromResourceAssembler.toCommandFromResource(createDiagnosticResource);
        var treatmentId = treatmentCommandService.handle(createDiagnosticCommand);
        if (treatmentId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getDiagnosticByIdQuery = new GetDiagnosticByIdQuery(treatmentId);
        var treatment = treatmentQueryService.handle(getDiagnosticByIdQuery);
        if (treatment.isEmpty()) return ResponseEntity.badRequest().build();
        var treatmentResource = DiagnosticResourceFromEntityAssembler.toResourceFromEntity(treatment.get());
        return new ResponseEntity<>(treatmentResource, HttpStatus.CREATED);
    }

    @PutMapping("/{treatmentId}")
    public ResponseEntity<DiagnosticResource> updateDiagnostic(@PathVariable Long treatmentId, @RequestBody UpdateDiagnosticResource updateDiagnosticResource) {
        var updateDiagnosticCommand = UpdateDiagnosticCommandFromResourceAssembler.toCommandFromResource(treatmentId, updateDiagnosticResource);
        var updatedDiagnostic = treatmentCommandService.handle(updateDiagnosticCommand);
        if (updatedDiagnostic.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var treatmentResource = DiagnosticResourceFromEntityAssembler.toResourceFromEntity(updatedDiagnostic.get());
        return ResponseEntity.ok(treatmentResource);
    }
}
