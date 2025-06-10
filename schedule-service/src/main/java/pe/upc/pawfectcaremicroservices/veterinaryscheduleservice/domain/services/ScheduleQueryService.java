package pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.domain.services;


import pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.domain.model.aggregates.Schedule;
import pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.domain.model.queries.GetAllSchedulesByVeterinarianIdQuery;
import pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.domain.model.queries.GetAllSchedulesQuery;
import pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.domain.model.queries.GetScheduleByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ScheduleQueryService {
   Optional<Schedule> handle(GetScheduleByIdQuery query);
   List<Schedule> handle(GetAllSchedulesQuery query);
   List<Schedule> handle(GetAllSchedulesByVeterinarianIdQuery query);
}
