/**
 * CreatePetCommand
 * @Summary
 *  CreatePetCommand is a record class that represents the command create
 **/

package pe.upc.pawfectcaremicroservices.medicalrecordservice.domain.model.commands;

public record CreateMedicalRecordCommand(String title, String notes, Long diagnosticId) {
}
