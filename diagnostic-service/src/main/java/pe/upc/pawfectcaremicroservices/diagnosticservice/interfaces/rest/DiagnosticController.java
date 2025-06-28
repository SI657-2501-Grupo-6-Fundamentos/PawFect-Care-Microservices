package pe.upc.pawfectcaremicroservices.diagnosticservice.interfaces.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.upc.pawfectcaremicroservices.diagnosticservice.domain.model.queries.GetAllDiagnosticByDiagnosticTypeQuery;
import pe.upc.pawfectcaremicroservices.diagnosticservice.domain.model.queries.GetAllDiagnosticQuery;
import pe.upc.pawfectcaremicroservices.diagnosticservice.domain.model.queries.GetDiagnosticByIdQuery;
import pe.upc.pawfectcaremicroservices.diagnosticservice.domain.model.valueobjects.DiagnosticType;
import pe.upc.pawfectcaremicroservices.diagnosticservice.domain.services.DiagnosticCommandService;
import pe.upc.pawfectcaremicroservices.diagnosticservice.domain.services.DiagnosticQueryService;
import pe.upc.pawfectcaremicroservices.diagnosticservice.interfaces.rest.resources.CreateDiagnosticResource;
import pe.upc.pawfectcaremicroservices.diagnosticservice.interfaces.rest.resources.DiagnosticResource;
import pe.upc.pawfectcaremicroservices.diagnosticservice.interfaces.rest.resources.UpdateDiagnosticResource;
import pe.upc.pawfectcaremicroservices.diagnosticservice.interfaces.rest.transform.CreateDiagnosticCommandFromResourceAssembler;
import pe.upc.pawfectcaremicroservices.diagnosticservice.interfaces.rest.transform.DiagnosticResourceFromEntityAssembler;
import pe.upc.pawfectcaremicroservices.diagnosticservice.interfaces.rest.transform.UpdateDiagnosticCommandFromResourceAssembler;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "api/v1/diagnostics", produces = APPLICATION_JSON_VALUE)
public class DiagnosticController {
    private final DiagnosticQueryService diagnosticQueryService;
    private final DiagnosticCommandService diagnosticCommandService;
    public DiagnosticController(DiagnosticQueryService diagnosticQueryService, DiagnosticCommandService diagnosticCommandService) {
        this.diagnosticCommandService = diagnosticCommandService;
        this.diagnosticQueryService = diagnosticQueryService;
    }

    @GetMapping("/{diagnosticId}")
    public ResponseEntity<DiagnosticResource> getDiagnosticById(@PathVariable Long diagnosticId) {
        var getDiagnosticByIdQuery = new GetDiagnosticByIdQuery(diagnosticId);
        var diagnostic = diagnosticQueryService.handle(getDiagnosticByIdQuery);
        if (diagnostic.isEmpty()) return ResponseEntity.badRequest().build();
        var diagnosticResource = DiagnosticResourceFromEntityAssembler.toResourceFromEntity(diagnostic.get());
        return ResponseEntity.ok(diagnosticResource);
    }

    @GetMapping
    public ResponseEntity<List<DiagnosticResource>> getAllDiagnostics() {
        var getAllDiagnosticsQuery = new GetAllDiagnosticQuery();
        var diagnostics = diagnosticQueryService.handle(getAllDiagnosticsQuery);
        var petResources = diagnostics.stream().map(DiagnosticResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(petResources);
    }

    @GetMapping("/diagnosticType")
    public ResponseEntity<List<DiagnosticResource>> getDiagnosticsByType(@RequestParam DiagnosticType diagnosticType) {
        var getDiagnosticByDiagnosticType = new GetAllDiagnosticByDiagnosticTypeQuery(diagnosticType);
        var diagnostics = diagnosticQueryService.handle(getDiagnosticByDiagnosticType);
        var diagnosticResources = diagnostics.stream().map(DiagnosticResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(diagnosticResources);
    }

    @PostMapping
    public ResponseEntity<DiagnosticResource> createDiagnostic(@RequestBody CreateDiagnosticResource createDiagnosticResource) {
        var createDiagnosticCommand = CreateDiagnosticCommandFromResourceAssembler.toCommandFromResource(createDiagnosticResource);
        var diagnosticId = diagnosticCommandService.handle(createDiagnosticCommand);
        if (diagnosticId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getDiagnosticByIdQuery = new GetDiagnosticByIdQuery(diagnosticId);
        var diagnostic = diagnosticQueryService.handle(getDiagnosticByIdQuery);
        if (diagnostic.isEmpty()) return ResponseEntity.badRequest().build();
        var diagnosticResource = DiagnosticResourceFromEntityAssembler.toResourceFromEntity(diagnostic.get());
        return new ResponseEntity<>(diagnosticResource, HttpStatus.CREATED);
    }

    @PutMapping("/{diagnosticId}")
    public ResponseEntity<DiagnosticResource> updateDiagnostic(@PathVariable Long diagnosticId, @RequestBody UpdateDiagnosticResource updateDiagnosticResource) {
        var updateDiagnosticCommand = UpdateDiagnosticCommandFromResourceAssembler.toCommandFromResource(diagnosticId, updateDiagnosticResource);
        var updatedDiagnostic = diagnosticCommandService.handle(updateDiagnosticCommand);
        if (updatedDiagnostic.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var diagnosticResource = DiagnosticResourceFromEntityAssembler.toResourceFromEntity(updatedDiagnostic.get());
        return ResponseEntity.ok(diagnosticResource);
    }
}
