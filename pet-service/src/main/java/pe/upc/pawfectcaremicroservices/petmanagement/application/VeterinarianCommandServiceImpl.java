package pe.upc.pawfectcaremicroservices.petmanagement.application;

import org.springframework.stereotype.Service;
import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.aggregates.Veterinarian;
import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.commands.CreateVeterinarianCommand;
import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.commands.UpdateVeterinarianCommand;
import pe.upc.pawfectcaremicroservices.petmanagement.domain.model.valueobjects.VeterinarianSpeciality;
import pe.upc.pawfectcaremicroservices.petmanagement.domain.services.VeterinarianCommandService;
import pe.upc.pawfectcaremicroservices.petmanagement.infrastructure.persistence.jpa.repositories.VeterinarianRepository;

import java.util.Optional;

@Service
public class VeterinarianCommandServiceImpl implements VeterinarianCommandService {
    private final VeterinarianRepository veterinarianRepository;

    public VeterinarianCommandServiceImpl(VeterinarianRepository veterinarianRepository) {
        this.veterinarianRepository = veterinarianRepository;
    }

    @Override
    public Long handle(CreateVeterinarianCommand command) {
        if (veterinarianRepository.existsByDni(command.dni())) {
            throw new IllegalArgumentException("Veterinarian with same dni already exists");
        }
        var veterinarian = new Veterinarian(command);
        try {
            veterinarianRepository.save(veterinarian);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving veterinarian: " + e.getMessage());
        }
        return veterinarian.getId();
    }
    
    @Override
    public Optional<Veterinarian> handle(UpdateVeterinarianCommand command) {
        if (!veterinarianRepository.existsById(command.id())) throw new IllegalArgumentException("veterinarianId does not exist");
        var result = veterinarianRepository.findById(command.id());
        var veterinarianToUpdate = result.get();
        try {
            var updatedVeterinarian = veterinarianRepository.save(veterinarianToUpdate
                    .updateInformation(
                            command.fullName(),
                            command.phoneNumber(),
                            command.email(),
                            command.dni(),
                            command.veterinarianSpeciality() != null ?
                                    VeterinarianSpeciality.valueOf(String.valueOf(command.veterinarianSpeciality())) :
                                    veterinarianToUpdate.getVeterinarianSpeciality()
                    )
            );
            return Optional.of(updatedVeterinarian);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating veterinarian: " + e.getMessage());
        }
    }
}
