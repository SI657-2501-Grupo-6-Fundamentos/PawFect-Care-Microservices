package pe.upc.pawfectcaremicroservices.medicalappointment.interfaces.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.upc.pawfectcaremicroservices.medicalappointment.application.internal.TariffCommandServiceImpl;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.aggregates.Tariff;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.queries.*;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.valueobjects.ServiceName;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.services.TariffCommandService;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.services.TariffQueryService;
import pe.upc.pawfectcaremicroservices.medicalappointment.infrastructure.persistence.jpa.repositories.TariffRepository;
import pe.upc.pawfectcaremicroservices.medicalappointment.interfaces.rest.resources.CreateTariffResource;
import pe.upc.pawfectcaremicroservices.medicalappointment.interfaces.rest.resources.TariffResource;
import pe.upc.pawfectcaremicroservices.medicalappointment.interfaces.rest.resources.UpdateTariffResource;
import pe.upc.pawfectcaremicroservices.medicalappointment.interfaces.rest.transform.CreateTariffCommandFromResourceAssembler;
import pe.upc.pawfectcaremicroservices.medicalappointment.interfaces.rest.transform.TariffResourceFromEntityAssembler;
import pe.upc.pawfectcaremicroservices.medicalappointment.interfaces.rest.transform.UpdateTariffCommandFromResourceAssembler;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/tariffs", produces = APPLICATION_JSON_VALUE)
public class TariffController {
    private final TariffCommandService tariffCommandService;
    private final TariffQueryService tariffQueryService;

    public TariffController(
            TariffQueryService tariffQueryService,
            TariffCommandService tariffCommandService
    ) {
        this.tariffCommandService = tariffCommandService;
        this.tariffQueryService = tariffQueryService;
    }

    /**
     * Creates a new tariff.
     *
     * @param createTariffResource the resource containing the tariff details
     * @return the created tariff resource, or a bad request response if creation fails
     */
    @PostMapping
    public ResponseEntity<TariffResource> createTariff(@RequestBody CreateTariffResource createTariffResource) {
        var createTariffCommand = CreateTariffCommandFromResourceAssembler.toCommandFromResource(createTariffResource);
        var tariffId = tariffCommandService.handle(createTariffCommand);
        if (tariffId == 0L) {
            return ResponseEntity.badRequest().build();
        }

        var getTariffById = new GetTariffByIdQuery(tariffId);
        var tariff = tariffQueryService.handle(getTariffById);
        if (tariff.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var tariffResource = TariffResourceFromEntityAssembler.toResourceFromEntity(tariff.get());
        return new ResponseEntity<>(tariffResource, HttpStatus.CREATED);
    }

    /**
     * Retrieves all tariffs.
     * @return a list of tariff resources
     */
    @GetMapping
    public ResponseEntity<List<TariffResource>> getAllTariffs() {
        var getAllTariffsQuery = new GetAllTariffsQuery();
        var tariffs = tariffQueryService.handle(getAllTariffsQuery);
        var tariffResources = tariffs.stream()
                .map(TariffResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(tariffResources);
    }

    /**
     * Retrieves a tariff by its ID.
     *
     * @param tariffId the ID of the tariff to retrieve
     * @return the tariff resource if found, or a bad request response if not found
     */
    @GetMapping("/{tariffId}")
    public ResponseEntity<TariffResource> getTariffById(@PathVariable Long tariffId) {
        var getTariffByIdQuery = new GetTariffByIdQuery(tariffId);
        var tariff = tariffQueryService.handle(getTariffByIdQuery);
        if (tariff.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var tariffResource = TariffResourceFromEntityAssembler
                .toResourceFromEntity(tariff.get());
        return ResponseEntity.ok(tariffResource);
    }

    /**
     * Retrieves all tariffs for a specific service name.
     *
     * @param serviceName the name of the service
     * @return a list of tariff resources for the specified service
     */
    @GetMapping("/service/{serviceName}")
    public ResponseEntity<List<TariffResource>> getTariffsByServiceName(@PathVariable ServiceName serviceName) {
        var getAllTariffsByServiceName = new GetTariffByServiceNameQuery(serviceName);
        var tariffs = tariffQueryService.handle(getAllTariffsByServiceName);
        var tariffResources = tariffs.stream()
                .map(TariffResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(tariffResources);
    }

    /**
     * Retrieves tariffs within a specified cost range.
     *
     * @param minimumCost the minimum cost of the tariffs to retrieve
     * @param maximumCost the maximum cost of the tariffs to retrieve
     * @return a list of tariff resources within the specified cost range
     */
    @GetMapping("/cost-range/{minimumCost}/{maximumCost}")
    public ResponseEntity<List<TariffResource>> getTariffsByCostRange(@RequestParam Float minimumCost, @RequestParam Float maximumCost) {
        var getTariffsByCostRangeQuery = new GetTariffByCostBetweenQuery(minimumCost, maximumCost);
        var tariffs = tariffQueryService.handle(getTariffsByCostRangeQuery);
        var tariffResources = tariffs.stream()
                .map(TariffResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(tariffResources);
    }

    /**
     * Updates an existing tariff.
     *
     * @param tariffId the ID of the tariff to update
     * @param updateTariffResource the resource containing the updated tariff details
     * @return the updated tariff resource, or a bad request response if the update fails
     */
    @PutMapping("/{tariffId}")
    public ResponseEntity<TariffResource> updateTariff(@PathVariable Long tariffId, @RequestBody UpdateTariffResource updateTariffResource) {
        var updateTariffCommand = UpdateTariffCommandFromResourceAssembler.toCommandFromResource(tariffId, updateTariffResource);
        var updatedTariff = tariffCommandService.handle(updateTariffCommand);
        if (updatedTariff.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var tariffResource = TariffResourceFromEntityAssembler.toResourceFromEntity(updatedTariff.get());
        return ResponseEntity.ok(tariffResource);
    }
}
