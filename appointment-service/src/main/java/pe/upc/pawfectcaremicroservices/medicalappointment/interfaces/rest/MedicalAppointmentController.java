package pe.upc.pawfectcaremicroservices.medicalappointment.interfaces.rest;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.queries.GetAllMedicalAppointmentsByMedicalHistoryId;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.services.MedicalAppointmentCommandService;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.services.MedicalAppointmentQueryService;
import pe.upc.pawfectcaremicroservices.medicalappointment.interfaces.rest.resources.MedicalAppointmentResource;
import pe.upc.pawfectcaremicroservices.medicalappointment.interfaces.rest.resources.UpdateMedicalAppointmentResource;
import pe.upc.pawfectcaremicroservices.medicalappointment.interfaces.rest.transform.MedicalAppointmentResourceFromEntityAssembler;
import pe.upc.pawfectcaremicroservices.medicalappointment.interfaces.rest.transform.UpdateMedicalAppointmentCommandFromResourceAssembler;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/medical_appointments", produces = APPLICATION_JSON_VALUE)
public class MedicalAppointmentController {

    private final MedicalAppointmentQueryService medicalAppointmentQueryService;
    private final MedicalAppointmentCommandService medicalAppointmentCommandService;
    public MedicalAppointmentController(
            MedicalAppointmentQueryService medicalAppointmentQueryService,
            MedicalAppointmentCommandService medicalAppointmentCommandService
    ) {
        this.medicalAppointmentQueryService = medicalAppointmentQueryService;
        this.medicalAppointmentCommandService=medicalAppointmentCommandService;

    }


    @GetMapping("/medical_history/{medicalHistoryId}")
    public ResponseEntity<List<MedicalAppointmentResource>> getAllMedicalAppointmentsByMedicalHistoryId(@PathVariable Long medicalHistoryId) {
        var getAllMedicalAppointmentsByMedicalHistoryIdQuery = new GetAllMedicalAppointmentsByMedicalHistoryId(medicalHistoryId);
        var medicalAppointments = medicalAppointmentQueryService.handle(getAllMedicalAppointmentsByMedicalHistoryIdQuery);
        var medicalAppointmentResources = medicalAppointments.stream().map(MedicalAppointmentResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
        return ResponseEntity.ok(medicalAppointmentResources);
    }

    @PutMapping("/{medicalAppointmentId}")
    public ResponseEntity<MedicalAppointmentResource> updateMedicalAppointment(@PathVariable Long medicalAppointmentId, @RequestBody UpdateMedicalAppointmentResource updateMedicalAppointmentResource) {
        var updateMedicalAppointmentCommand = UpdateMedicalAppointmentCommandFromResourceAssembler.toCommandFromResource(medicalAppointmentId, updateMedicalAppointmentResource);
        var updatedMedicalAppointment = medicalAppointmentCommandService.handle(updateMedicalAppointmentCommand);
        if (updatedMedicalAppointment.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var medicalAppointmentResource = MedicalAppointmentResourceFromEntityAssembler.toResourceFromEntity(updatedMedicalAppointment.get());
        return ResponseEntity.ok(medicalAppointmentResource);
    }



}
