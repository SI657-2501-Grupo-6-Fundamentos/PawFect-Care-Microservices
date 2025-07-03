package pe.upc.pawfectcaremicroservices.medicalappointment.application.external.veterinarians;

import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import pe.upc.pawfectcaremicroservices.medicalappointment.domain.model.valueobjects.ServiceName;

import java.util.Optional;

@Component
public class ExternalVeterinarian {
    private final RestTemplate restTemplate;

    public ExternalVeterinarian(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean existsVeterinarianById(Long veterinarianId) {
        try {
            restTemplate.getForObject("http://localhost:8010/veterinary-service/api/v1/veterinarians/{veterinarianId}", Object.class, veterinarianId);
            return true;
        } catch (HttpClientErrorException.NotFound e) {
            return false;
        }
    }

    public boolean existsVeterinarianBySpeciality(ServiceName speciality) {
        try {
            restTemplate.getForObject("http://localhost:8010/veterinary-service/api/v1/veterinarians/speciality?speciality={speciality}", Object.class, speciality.name());
            return true;
        } catch (HttpClientErrorException.NotFound e) {
            return false;
        }
    }

    public Optional<VeterinarianAvailability> getVeterinarianAvailabilityById(Long veterinarianId) {
        try {
            VeterinarianAvailability availability = restTemplate.getForObject(
                    "http://localhost:8010/veterinary-service/api/v1/veterinarians/{veterinarianId}/availability",
                    VeterinarianAvailability.class,
                    veterinarianId
            );
            return Optional.ofNullable(availability);
        } catch (HttpClientErrorException.NotFound e) {
            return Optional.empty(); // Veterinarian not found
        } catch (Exception e) {
            throw new RuntimeException("Error while fetching veterinarian availability: " + e.getMessage(), e);
        }
    }
}
