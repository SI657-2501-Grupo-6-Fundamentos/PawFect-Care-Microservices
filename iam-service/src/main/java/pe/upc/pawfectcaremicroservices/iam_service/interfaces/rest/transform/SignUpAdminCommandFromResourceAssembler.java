package pe.upc.pawfectcaremicroservices.iam_service.interfaces.rest.transform;

import pe.upc.pawfectcaremicroservices.iam_service.domain.model.commands.SignUpAdminCommand;
import pe.upc.pawfectcaremicroservices.iam_service.interfaces.rest.resources.RegisterAdminRequest;

public class SignUpAdminCommandFromResourceAssembler {
    public static SignUpAdminCommand toCommandFromResource(RegisterAdminRequest request) {
        //var role = request.role() != null ? request.role().stream().map(name -> Role.toRoleFromName(name)).toList() : new ArrayList<Role>();
        //var role = request.getRole();
        return new SignUpAdminCommand(request.getUserName(), request.getPassword(), request.getRole(),
                request.getFullName(), request.getPhoneNumber(), request.getEmail(), request.getDni(), request.getVeterinarianSpeciality(),
                request.getAvailableStartTime(), request.getAvailableEndTime());
    }
}
