package pe.upc.pawfectcaremicroservices.medicalrecordservice.application.internal;

import org.springframework.stereotype.Service;
import pe.upc.pawfectcaremicroservices.medicalrecordservice.application.external.diagnostics.ExternalDiagnostic;
import pe.upc.pawfectcaremicroservices.medicalrecordservice.domain.model.aggregates.MedicalRecord;
import pe.upc.pawfectcaremicroservices.medicalrecordservice.domain.model.commands.CreateMedicalRecordCommand;
import pe.upc.pawfectcaremicroservices.medicalrecordservice.domain.model.commands.DeleteMedicalRecordCommand;
import pe.upc.pawfectcaremicroservices.medicalrecordservice.domain.model.commands.UpdateMedicalRecordCommand;
import pe.upc.pawfectcaremicroservices.medicalrecordservice.domain.services.MedicalRecordCommandService;
import pe.upc.pawfectcaremicroservices.medicalrecordservice.infrastructure.persistence.jpa.repositories.MedicalRecordRepository;

import java.util.Optional;

@Service
public class MedicalRecordCommandServicelmpl implements MedicalRecordCommandService {
    private final MedicalRecordRepository medicalRecordRepository;
    private final ExternalDiagnostic externalDiagnostic;

    public MedicalRecordCommandServicelmpl(MedicalRecordRepository medicalRecordRepository, ExternalDiagnostic externalDiagnostic) {
        this.medicalRecordRepository = medicalRecordRepository;
        this.externalDiagnostic = externalDiagnostic;
    }

    @Override
    public Long handle(CreateMedicalRecordCommand command) {
        var medicalRecord = new MedicalRecord(command);
        try {
            if (!externalDiagnostic.existsDiagnosticById(command.diagnosticId()))
                throw new IllegalArgumentException("diagnosticId does not exist");
            // Aquí podrías setear solo el diagnosticId, no el objeto Diagnostic
            medicalRecord.setDiagnosticId(command.diagnosticId());
            medicalRecordRepository.save(medicalRecord);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving medicalRecord: " + e.getMessage());
        }
        return medicalRecord.getId();
    }


    @Override
    public Optional<MedicalRecord> handle(UpdateMedicalRecordCommand command) {
        if (!medicalRecordRepository.existsById(command.id())) throw new IllegalArgumentException("medicalRecordId does not exist");
        var result = medicalRecordRepository.findById(command.id());
        if (result.isEmpty()) throw new IllegalArgumentException("MedicalRecord does not exist");
        var medicalRecordToUpdate = result.get();
        try {
            var updatedMedicalRecord = medicalRecordRepository.save(medicalRecordToUpdate.updateInformation(command.medicalRecordName(), command.birthDate(), command.registrationDate(), command.animalBreed(), command.medicalRecordGender()));
            return Optional.of(updatedMedicalRecord);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating medicalRecord: " + e.getMessage());
        }
    }


    @Override
    public void handle(DeleteMedicalRecordCommand command) {
        if (!medicalRecordRepository.existsById(command.medicalRecordId())) {
            throw new IllegalArgumentException("Pe does not exist");
        }
        try {
            medicalRecordRepository.deleteById(command.medicalRecordId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting medicalRecord: " + e.getMessage());
        }

    }
}
