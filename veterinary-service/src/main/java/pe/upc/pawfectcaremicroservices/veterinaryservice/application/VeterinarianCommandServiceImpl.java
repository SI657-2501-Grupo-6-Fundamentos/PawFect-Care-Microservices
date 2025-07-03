package pe.upc.pawfectcaremicroservices.veterinaryservice.application;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pe.upc.pawfectcaremicroservices.veterinaryservice.domain.model.aggregates.Veterinarian;
import pe.upc.pawfectcaremicroservices.veterinaryservice.domain.model.commands.CreateVeterinarianCommand;
import pe.upc.pawfectcaremicroservices.veterinaryservice.domain.model.commands.UpdateVeterinarianAvailabilityCommand;
import pe.upc.pawfectcaremicroservices.veterinaryservice.domain.model.commands.UpdateVeterinarianCommand;
import pe.upc.pawfectcaremicroservices.veterinaryservice.domain.model.valueobjects.VeterinarianSpeciality;
import pe.upc.pawfectcaremicroservices.veterinaryservice.domain.services.VeterinarianCommandService;
import pe.upc.pawfectcaremicroservices.veterinaryservice.infrastructure.persistence.jpa.repositories.VeterinarianRepository;

import java.time.LocalDateTime;
import java.time.LocalTime;
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
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Veterinarian with same dni already exists");
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
                                    VeterinarianSpeciality.fromValue(command.veterinarianSpeciality()) :
                                    veterinarianToUpdate.getVeterinarianSpeciality()
                    )
            );
            return Optional.of(updatedVeterinarian);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating veterinarian: " + e.getMessage());
        }
    }

    @Override
    public Optional<Veterinarian> handle(UpdateVeterinarianAvailabilityCommand command) {
        var optionalVeterinarian = veterinarianRepository.findById(command.id());

        if (optionalVeterinarian.isEmpty()) {
            throw new IllegalArgumentException("Veterinarian not found with ID: " + command.id());
        }

        var vet = optionalVeterinarian.get();
        vet.setAvailableStartTime(command.availableStartTime());
        vet.setAvailableEndTime(command.availableEndTime());

        var updated = veterinarianRepository.save(vet);
        return Optional.of(updated);
    }



}
