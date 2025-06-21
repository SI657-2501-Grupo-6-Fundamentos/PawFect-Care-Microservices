package pe.upc.pawfectcaremicroservices.medicalrecordservice.domain.services;

import pe.upc.pawfectcaremicroservices.medicalrecordservice.domain.model.aggregates.MedicalRecord;
import pe.upc.pawfectcaremicroservices.medicalrecordservice.domain.model.commands.CreateMedicalRecordCommand;
import pe.upc.pawfectcaremicroservices.medicalrecordservice.domain.model.commands.DeleteMedicalRecordCommand;
import pe.upc.pawfectcaremicroservices.medicalrecordservice.domain.model.commands.UpdateMedicalRecordCommand;

import java.util.Optional;

public interface MedicalRecordCommandService {
    Optional<MedicalRecord> handle(UpdateMedicalRecordCommand command);
    Long handle(CreateMedicalRecordCommand command);
    void handle(DeleteMedicalRecordCommand command);
}
