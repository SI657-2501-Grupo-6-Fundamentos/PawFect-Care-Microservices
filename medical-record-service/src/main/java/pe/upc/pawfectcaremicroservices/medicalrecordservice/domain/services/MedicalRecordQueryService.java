package pe.upc.pawfectcaremicroservices.medicalrecordservice.domain.services;

import pe.upc.pawfectcaremicroservices.medicalrecordservice.domain.model.aggregates.MedicalRecord;
import pe.upc.pawfectcaremicroservices.medicalrecordservice.domain.model.queries.GetAllMedicalRecordsByDiagnosticIdQuery;
import pe.upc.pawfectcaremicroservices.medicalrecordservice.domain.model.queries.GetAllMedicalRecordsByMedicalAppointmentIdQuery;
import pe.upc.pawfectcaremicroservices.medicalrecordservice.domain.model.queries.GetAllMedicalRecordsQuery;
import pe.upc.pawfectcaremicroservices.medicalrecordservice.domain.model.queries.GetMedicalRecordByIdQuery;

import java.util.List;
import java.util.Optional;

public interface MedicalRecordQueryService {
   Optional<MedicalRecord> handle(GetMedicalRecordByIdQuery query);
   List<MedicalRecord> handle(GetAllMedicalRecordsQuery query);
   List<MedicalRecord> handle(GetAllMedicalRecordsByDiagnosticIdQuery query);
   List<MedicalRecord> handle(GetAllMedicalRecordsByMedicalAppointmentIdQuery query);
}
