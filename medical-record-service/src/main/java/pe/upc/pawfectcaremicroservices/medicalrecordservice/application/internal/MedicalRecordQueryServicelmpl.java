package pe.upc.pawfectcaremicroservices.medicalrecordservice.application.internal;

import org.springframework.stereotype.Service;
import pe.upc.pawfectcaremicroservices.medicalrecordservice.domain.model.aggregates.MedicalRecord;
import pe.upc.pawfectcaremicroservices.medicalrecordservice.domain.model.queries.GetAllMedicalRecordsByDiagnosticIdQuery;
import pe.upc.pawfectcaremicroservices.medicalrecordservice.domain.model.queries.GetAllMedicalRecordsByMedicalAppointmentIdQuery;
import pe.upc.pawfectcaremicroservices.medicalrecordservice.domain.model.queries.GetAllMedicalRecordsQuery;
import pe.upc.pawfectcaremicroservices.medicalrecordservice.domain.model.queries.GetMedicalRecordByIdQuery;
import pe.upc.pawfectcaremicroservices.medicalrecordservice.domain.services.MedicalRecordQueryService;
import pe.upc.pawfectcaremicroservices.medicalrecordservice.infrastructure.persistence.jpa.repositories.MedicalRecordRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MedicalRecordQueryServicelmpl implements MedicalRecordQueryService {

    private final MedicalRecordRepository medicalRecordRepository;
    public MedicalRecordQueryServicelmpl(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    @Override
    public List<MedicalRecord> handle(GetAllMedicalRecordsQuery query) {
        return medicalRecordRepository.findAll();
    }

    @Override
    public Optional<MedicalRecord> handle(GetMedicalRecordByIdQuery query) {
        if (!medicalRecordRepository.existsById(query.medicalRecordId())) {
            throw new IllegalArgumentException("medicalRecordId not found");
        }
        return medicalRecordRepository.findById(query.medicalRecordId());
    }

    @Override
    public List<MedicalRecord> handle(GetAllMedicalRecordsByDiagnosticIdQuery query) {
        return medicalRecordRepository.findAllByDiagnosticId(query.diagnosticId());
    }

    @Override
    public List<MedicalRecord> handle(GetAllMedicalRecordsByMedicalAppointmentIdQuery query) {
        return medicalRecordRepository.findAllByMedicalAppointmentId(query.medicalAppointmentId());
    }



}
