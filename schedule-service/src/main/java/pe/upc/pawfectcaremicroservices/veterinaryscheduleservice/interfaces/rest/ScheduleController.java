package pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.interfaces.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.domain.model.commands.DeleteScheduleCommand;
import pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.domain.model.queries.GetAllSchedulesQuery;
import pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.domain.model.queries.GetScheduleByIdQuery;
import pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.domain.services.ScheduleCommandService;
import pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.domain.services.ScheduleQueryService;
import pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.interfaces.rest.resources.CreateScheduleResource;
import pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.interfaces.rest.resources.ScheduleResource;
import pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.interfaces.rest.resources.UpdateScheduleResource;
import pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.interfaces.rest.transform.CreateScheduleCommandFromResourceAssembler;
import pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.interfaces.rest.transform.ScheduleResourceFromEntityAssembler;
import pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.interfaces.rest.transform.UpdateScheduleCommandFromResourceAssembler;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/schedules", produces = APPLICATION_JSON_VALUE)
public class ScheduleController {

    private final ScheduleQueryService scheduleQueryService;
    private final ScheduleCommandService scheduleCommandService;
    public ScheduleController(ScheduleQueryService scheduleQueryService, ScheduleCommandService scheduleCommandService) {
        this.scheduleQueryService = scheduleQueryService;
        this.scheduleCommandService = scheduleCommandService;
    }

    @PostMapping
    public ResponseEntity<ScheduleResource> createSchedule(@RequestBody CreateScheduleResource createScheduleResource) {
        var createScheduleCommand = CreateScheduleCommandFromResourceAssembler.toCommandFromResource(createScheduleResource);
        var scheduleId = scheduleCommandService.handle(createScheduleCommand);
        if (scheduleId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getScheduleByIdQuery = new GetScheduleByIdQuery(scheduleId);
        var schedule = scheduleQueryService.handle(getScheduleByIdQuery);
        if (schedule.isEmpty()) return ResponseEntity.badRequest().build();
        var scheduleResource = ScheduleResourceFromEntityAssembler.toResourceFromEntity(schedule.get());
        return new ResponseEntity<>(scheduleResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResource>> getAllSchedules() {
        var getAllSchedulesQuery = new GetAllSchedulesQuery();
        var schedules = scheduleQueryService.handle(getAllSchedulesQuery);
        var scheduleResources = schedules.stream().map(ScheduleResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(scheduleResources);
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResource> getScheduleById(@PathVariable Long scheduleId) {
        var getScheduleByIdQuery = new GetScheduleByIdQuery(scheduleId);
        var schedule = scheduleQueryService.handle(getScheduleByIdQuery);
        if (schedule.isEmpty()) return ResponseEntity.badRequest().build();
        var scheduleResource = ScheduleResourceFromEntityAssembler.toResourceFromEntity(schedule.get());
        return ResponseEntity.ok(scheduleResource);
    }

    @PutMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResource> updateSchedule(@PathVariable Long scheduleId, @RequestBody UpdateScheduleResource updateScheduleResource) {
        var updateScheduleCommand = UpdateScheduleCommandFromResourceAssembler.toCommandFromResource(scheduleId, updateScheduleResource);
        var updatedSchedule = scheduleCommandService.handle(updateScheduleCommand);
        if (updatedSchedule.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var scheduleResource = ScheduleResourceFromEntityAssembler.toResourceFromEntity(updatedSchedule.get());
        return ResponseEntity.ok(scheduleResource);
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<?> deleteSchedule(@PathVariable Long scheduleId) {
        var deleteScheduleCommand = new DeleteScheduleCommand(scheduleId);
        scheduleCommandService.handle(deleteScheduleCommand);
        return ResponseEntity.ok("Schedule with given id successfully deleted");
    }


}
