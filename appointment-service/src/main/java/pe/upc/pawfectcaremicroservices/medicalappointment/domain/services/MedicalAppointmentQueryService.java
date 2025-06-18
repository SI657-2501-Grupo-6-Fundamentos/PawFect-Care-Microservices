package pe.upc.pawfectcaremicroservices.medicalappointment.domain.services;

import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.aggregates.MedicalAppointment;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.queries.GetAllMedicalAppointmentsByMedicalHistoryId;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.queries.GetMedicalAppointmentByIdQuery;

import java.util.List;
import java.util.Optional;

public interface MedicalAppointmentQueryService {
    Optional<MedicalAppointment> handle(GetMedicalAppointmentByIdQuery query);
    List<MedicalAppointment> handle(GetAllMedicalAppointmentsByMedicalHistoryId query);
}
