package pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.interfaces.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.domain.model.queries.GetAllSchedulesByVeterinarianIdQuery;
import pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.domain.services.ScheduleQueryService;
import pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.interfaces.rest.resources.ScheduleResource;
import pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.interfaces.rest.transform.ScheduleResourceFromEntityAssembler;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/veterinarians/{veterinarianId}/schedules", produces = APPLICATION_JSON_VALUE)
public class VeterinarianScheduleController {
    private final ScheduleQueryService scheduleQueryService;

    public VeterinarianScheduleController(ScheduleQueryService scheduleQueryService) {
        this.scheduleQueryService = scheduleQueryService;
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResource>> getAllSchedulesByVeterinarianId(@PathVariable Long veterinarianId) {
        var getAllSchedulesByVeterinarianIdQuery = new GetAllSchedulesByVeterinarianIdQuery(veterinarianId);
        var schedules = scheduleQueryService.handle(getAllSchedulesByVeterinarianIdQuery);
        var scheduleResources = schedules.stream().map(ScheduleResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(scheduleResources);
    }

}
