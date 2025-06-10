package pe.upc.pawfectcaremicroservices.medicalappointment.interfaces.rest.transform;

import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.aggregates.MedicalAppointment;
import pe.upc.pawfectcaremicroservices.medicalappointment.interfaces.rest.resources.MedicalAppointmentResource;

public class MedicalAppointmentResourceFromEntityAssembler {

    public static MedicalAppointmentResource toResourceFromEntity(MedicalAppointment entity) {
        return new MedicalAppointmentResource(
                entity.getId(),
                entity.getDiagnosis(),
                entity.getNotes(),
                entity.getTreatment(),
                entity.getAppointment().getId());
    }
}
