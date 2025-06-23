package pe.upc.pawfectcaremicroservices.medicalappointment.interfaces.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.queries.GetAllAppointmentsByPetIdQuery;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.queries.GetAllAppointmentsByVeterinarianIdQuery;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.queries.GetAllAppointmentsQuery;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.queries.GetAppointmentByIdQuery;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.services.AppointmentCommandService;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.services.AppointmentQueryService;
import pe.upc.pawfectcaremicroservices.medicalappointment.interfaces.rest.resources.AppointmentResource;
import pe.upc.pawfectcaremicroservices.medicalappointment.interfaces.rest.resources.CreateAppointmentResource;
import pe.upc.pawfectcaremicroservices.medicalappointment.interfaces.rest.resources.UpdateAppointmentResource;
import pe.upc.pawfectcaremicroservices.medicalappointment.interfaces.rest.transform.AppointmentResourceFromEntityAssembler;
import pe.upc.pawfectcaremicroservices.medicalappointment.interfaces.rest.transform.CreateAppointmentCommandFromResourceAssembler;
import pe.upc.pawfectcaremicroservices.medicalappointment.interfaces.rest.transform.UpdateAppointmentCommandFromResourceAssembler;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/appointments", produces = APPLICATION_JSON_VALUE)
public class AppointmentController {

    private final AppointmentCommandService appointmentCommandService;
    private final AppointmentQueryService appointmentQueryService;
    //private final MedicalAppointmentCommandService medicalAppointmentCommandService;
    public AppointmentController(
            AppointmentQueryService appointmentQueryService,
            AppointmentCommandService appointmentCommandService
    ) {
        this.appointmentCommandService = appointmentCommandService;
        this.appointmentQueryService = appointmentQueryService;
    }

    /**
     * Creates a new appointment.
     *
     * @param createAppointmentResource the resource containing the appointment details
     * @return the created appointment resource, or a bad request response if creation fails
     */
    @PostMapping
    public ResponseEntity<AppointmentResource> createAppointment(@RequestBody CreateAppointmentResource createAppointmentResource) {
        var createAppointmentCommand = CreateAppointmentCommandFromResourceAssembler.toCommandFromResource(createAppointmentResource);
        var appointmentId = appointmentCommandService.handle(createAppointmentCommand);
        if (appointmentId == 0L) {
            return ResponseEntity.badRequest().build();
        }

      var getAppointmentById = new GetAppointmentByIdQuery(appointmentId);
       var appointment = appointmentQueryService.handle(getAppointmentById);
        if (appointment.isEmpty()) return ResponseEntity.badRequest().build();


        var appointmentResource = AppointmentResourceFromEntityAssembler.toResourceFromEntity(appointment.get());
        return new ResponseEntity<>(appointmentResource, HttpStatus.CREATED);
    }

    /**
     * Retrieves an appointment by its ID.
     *
     * @param appointmentId the ID of the appointment to retrieve
     * @return the appointment resource if found, or a bad request response if not found
     */
    @GetMapping("/{appointmentId}")
    public ResponseEntity<AppointmentResource> getAppointmentById(@PathVariable Long appointmentId) {
        var getAppointmentByIdQuery = new GetAppointmentByIdQuery(appointmentId);
        var appointment = appointmentQueryService.handle(getAppointmentByIdQuery);
        if (appointment.isEmpty()) return ResponseEntity.badRequest().build();
        var appointmentResource = AppointmentResourceFromEntityAssembler
                .toResourceFromEntity(appointment.get());
        return ResponseEntity.ok(appointmentResource);
    }

    /**
     * Retrieves all appointments.
     * @return a list of all appointment resources
     */
    @GetMapping
    public ResponseEntity<List<AppointmentResource>> getAllAppointments() {
        var getAllAppointmentsQuery = new GetAllAppointmentsQuery();
        var appointments = appointmentQueryService.handle(getAllAppointmentsQuery);
        var appointmentResources = appointments.stream().map(AppointmentResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
        return ResponseEntity.ok(appointmentResources);
    }

    /**
     * Retrieves all appointments for a specific pet.
     * @param petId the ID of the pet
     * @return a list of appointment resources for the specified pet
     */
    @GetMapping("/pet/{petId}")
    public ResponseEntity<List<AppointmentResource>> getAppointmentsByPetId(@PathVariable Long petId) {
        var getAllAppointmentsByPetQuery = new GetAllAppointmentsByPetIdQuery(petId);
        var appointments = appointmentQueryService.handle(getAllAppointmentsByPetQuery);
        var appointmentResources = appointments.stream()
                .map(AppointmentResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(appointmentResources);
    }

    /**
     * Retrieves all appointments for a specific veterinarian.
     * @param veterinarianId the ID of the veterinarian
     * @return a list of appointment resources for the specified veterinarian
     */
    @GetMapping("/veterinarian/{veterinarianId}")
    public ResponseEntity<List<AppointmentResource>> getAppointmentsByVeterinarianId(@PathVariable Long veterinarianId) {
        var getAllAppointmentsByVeterinarianQuery = new GetAllAppointmentsByVeterinarianIdQuery(veterinarianId);
        var appointments = appointmentQueryService.handle(getAllAppointmentsByVeterinarianQuery);
        var appointmentResources = appointments.stream().map(AppointmentResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
        return ResponseEntity.ok(appointmentResources);
    }


    /**
     * Updates an existing appointment.
     *
     * @param appointmentId the ID of the appointment to update
     * @param updateAppointmentResource the resource containing the updated appointment details
     * @return the updated appointment resource, or a bad request response if the update fails
     */
    @PutMapping("/{appointmentId}")
    public ResponseEntity<AppointmentResource> updateAppointment(@PathVariable Long appointmentId, @RequestBody UpdateAppointmentResource updateAppointmentResource) {
        var updateAppointmentCommand = UpdateAppointmentCommandFromResourceAssembler.toCommandFromResource(appointmentId, updateAppointmentResource);
        var updatedAppointment = appointmentCommandService.handle(updateAppointmentCommand);
        if (updatedAppointment.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var appointmentResource = AppointmentResourceFromEntityAssembler.toResourceFromEntity(updatedAppointment.get());
        return ResponseEntity.ok(appointmentResource);
    }
}