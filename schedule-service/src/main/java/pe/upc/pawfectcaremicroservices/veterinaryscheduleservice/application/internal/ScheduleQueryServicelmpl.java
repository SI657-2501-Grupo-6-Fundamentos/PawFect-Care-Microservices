package pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.application.internal;

import org.springframework.stereotype.Service;
import pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.domain.model.aggregates.Schedule;
import pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.domain.model.queries.GetAllSchedulesByVeterinarianIdQuery;
import pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.domain.model.queries.GetAllSchedulesQuery;
import pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.domain.model.queries.GetScheduleByIdQuery;
import pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.domain.services.ScheduleQueryService;
import pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.infrastructure.persistence.jpa.repositories.ScheduleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleQueryServicelmpl implements ScheduleQueryService {

    private final ScheduleRepository scheduleRepository;
    public ScheduleQueryServicelmpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public List<Schedule> handle(GetAllSchedulesQuery query) {
        return scheduleRepository.findAll();
    }

    @Override
    public Optional<Schedule> handle(GetScheduleByIdQuery query) {
        if (!scheduleRepository.existsById(query.scheduleId())) {
            throw new IllegalArgumentException("scheduleId not found");
        }
        return scheduleRepository.findById(query.scheduleId());
    }

    @Override
    public List<Schedule> handle(GetAllSchedulesByVeterinarianIdQuery query) {
        return scheduleRepository.findAllByVeterinarianId(query.veterinarianId());
    }



}
