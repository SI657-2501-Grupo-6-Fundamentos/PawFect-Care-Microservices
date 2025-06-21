package pe.upc.pawfectcaremicroservices.medicalrecordservice.interfaces.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.upc.pawfectcaremicroservices.medicalrecordservice.domain.model.queries.GetAllMedicalRecordsByDiagnosticIdQuery;
import pe.upc.pawfectcaremicroservices.medicalrecordservice.domain.services.MedicalRecordQueryService;
import pe.upc.pawfectcaremicroservices.medicalrecordservice.interfaces.rest.resources.MedicalRecordResource;
import pe.upc.pawfectcaremicroservices.medicalrecordservice.interfaces.rest.transform.MedicalRecordResourceFromEntityAssembler;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/diagnostics/{diagnosticId}/medicalRecords", produces = APPLICATION_JSON_VALUE)
public class DiagnosticsMedicalRecordController {
    private final MedicalRecordQueryService medicalRecordQueryService;

    public DiagnosticsMedicalRecordController(MedicalRecordQueryService medicalRecordQueryService) {
        this.medicalRecordQueryService = medicalRecordQueryService;
    }

    @GetMapping
    public ResponseEntity<List<MedicalRecordResource>> getAllMedicalRecordsByDiagnosticId(@PathVariable Long diagnosticId) {
        var getAllMedicalRecordsByDiagnosticIdQuery = new GetAllMedicalRecordsByDiagnosticIdQuery(diagnosticId);
        var medicalRecords = medicalRecordQueryService.handle(getAllMedicalRecordsByDiagnosticIdQuery);
        var medicalRecordResources = medicalRecords.stream().map(MedicalRecordResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(medicalRecordResources);

    }

}
