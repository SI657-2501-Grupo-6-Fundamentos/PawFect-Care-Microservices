package pe.upc.pawfectcaremicroservices.iam_service.interfaces.rest.transform;

import pe.upc.pawfectcaremicroservices.iam_service.domain.model.commands.SignUpCommand;
import pe.upc.pawfectcaremicroservices.iam_service.interfaces.rest.resources.SignUpRequest;

public class SignUpCommandFromResourceAssembler {
    public static SignUpCommand toCommandFromResource(SignUpRequest request) {
        //var role = request.role() != null ? request.role().stream().map(name -> Role.toRoleFromName(name)).toList() : new ArrayList<Role>();
        //var role = request.getRole();
        return new SignUpCommand(request.getUserName(), request.getPassword(), request.getRole(),
                request.getFullName(), request.getPhoneNumber(), request.getEmail(), request.getAddress());
    }
}
