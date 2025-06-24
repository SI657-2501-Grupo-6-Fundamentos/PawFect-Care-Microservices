package pe.upc.pawfectcaremicroservices.medicalrecordservice.interfaces.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.upc.pawfectcaremicroservices.medicalrecordservice.domain.model.commands.DeleteMedicalRecordCommand;
import pe.upc.pawfectcaremicroservices.medicalrecordservice.domain.model.queries.GetAllMedicalRecordsQuery;
import pe.upc.pawfectcaremicroservices.medicalrecordservice.domain.model.queries.GetMedicalRecordByIdQuery;
import pe.upc.pawfectcaremicroservices.medicalrecordservice.domain.services.MedicalRecordCommandService;
import pe.upc.pawfectcaremicroservices.medicalrecordservice.domain.services.MedicalRecordQueryService;
import pe.upc.pawfectcaremicroservices.medicalrecordservice.interfaces.rest.resources.CreateMedicalRecordResource;
import pe.upc.pawfectcaremicroservices.medicalrecordservice.interfaces.rest.resources.MedicalRecordResource;
import pe.upc.pawfectcaremicroservices.medicalrecordservice.interfaces.rest.resources.UpdateMedicalRecordResource;
import pe.upc.pawfectcaremicroservices.medicalrecordservice.interfaces.rest.transform.CreateMedicalRecordCommandFromResourceAssembler;
import pe.upc.pawfectcaremicroservices.medicalrecordservice.interfaces.rest.transform.MedicalRecordResourceFromEntityAssembler;
import pe.upc.pawfectcaremicroservices.medicalrecordservice.interfaces.rest.transform.UpdateMedicalRecordCommandFromResourceAssembler;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/medical-records", produces = APPLICATION_JSON_VALUE)
public class MedicalRecordsController {

    private final MedicalRecordQueryService medicalRecordQueryService;
    private final MedicalRecordCommandService medicalRecordCommandService;
    public MedicalRecordsController(MedicalRecordQueryService medicalRecordQueryService, MedicalRecordCommandService medicalRecordCommandService) {
        this.medicalRecordQueryService = medicalRecordQueryService;
        this.medicalRecordCommandService = medicalRecordCommandService;
    }

    @PostMapping
    public ResponseEntity<MedicalRecordResource> createMedicalRecord(@RequestBody CreateMedicalRecordResource createMedicalRecordResource) {
        var createMedicalRecordCommand = CreateMedicalRecordCommandFromResourceAssembler.toCommandFromResource(createMedicalRecordResource);
        var medicalRecordId = medicalRecordCommandService.handle(createMedicalRecordCommand);
        if (medicalRecordId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getMedicalRecordByIdQuery = new GetMedicalRecordByIdQuery(medicalRecordId);
        var medicalRecord = medicalRecordQueryService.handle(getMedicalRecordByIdQuery);
        if (medicalRecord.isEmpty()) return ResponseEntity.badRequest().build();
        var medicalRecordResource = MedicalRecordResourceFromEntityAssembler.toResourceFromEntity(medicalRecord.get());
        return new ResponseEntity<>(medicalRecordResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MedicalRecordResource>> getAllMedicalRecords() {
        var getAllMedicalRecordsQuery = new GetAllMedicalRecordsQuery();
        var medicalRecords = medicalRecordQueryService.handle(getAllMedicalRecordsQuery);
        var medicalRecordResources = medicalRecords.stream().map(MedicalRecordResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(medicalRecordResources);
    }

    @GetMapping("/{medicalRecordId}")
    public ResponseEntity<MedicalRecordResource> getMedicalRecordById(@PathVariable Long medicalRecordId) {
        var getMedicalRecordByIdQuery = new GetMedicalRecordByIdQuery(medicalRecordId);
        var medicalRecord = medicalRecordQueryService.handle(getMedicalRecordByIdQuery);
        if (medicalRecord.isEmpty()) return ResponseEntity.badRequest().build();
        var medicalRecordResource = MedicalRecordResourceFromEntityAssembler.toResourceFromEntity(medicalRecord.get());
        return ResponseEntity.ok(medicalRecordResource);
    }

    @PutMapping("/{medicalRecordId}")
    public ResponseEntity<MedicalRecordResource> updateMedicalRecord(@PathVariable Long medicalRecordId, @RequestBody UpdateMedicalRecordResource updateMedicalRecordResource) {
        var updateMedicalRecordCommand = UpdateMedicalRecordCommandFromResourceAssembler.toCommandFromResource(medicalRecordId, updateMedicalRecordResource);
        var updatedMedicalRecord = medicalRecordCommandService.handle(updateMedicalRecordCommand);
        if (updatedMedicalRecord.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var medicalRecordResource = MedicalRecordResourceFromEntityAssembler.toResourceFromEntity(updatedMedicalRecord.get());
        return ResponseEntity.ok(medicalRecordResource);
    }

    @DeleteMapping("/{medicalRecordId}")
    public ResponseEntity<?> deleteMedicalRecord(@PathVariable Long medicalRecordId) {
        var deleteMedicalRecordCommand = new DeleteMedicalRecordCommand(medicalRecordId);
        medicalRecordCommandService.handle(deleteMedicalRecordCommand);
        return ResponseEntity.ok("MedicalRecord with given id successfully deleted");
    }


}
