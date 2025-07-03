package pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.application.external.veterinarians;

import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import pe.upc.pawfectcaremicroservices.veterinaryscheduleservice.domain.model.aggregates.Schedule;

import java.util.HashMap;

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
        } catch (HttpClientErrorException e) {
            System.err.println("HTTP Error: " + e.getStatusCode());
            return false;
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            return false;
        }
    }

    public void updateVeterinarianAvailability(Long vetId, Schedule schedule) {
        var url = "http://localhost:8010/veterinary-service/api/v1/veterinarians/{id}/availability";

        var request = new HashMap<String, Object>();
        request.put("availableDays", schedule.getAvailableDays());
        request.put("availableStartTime", schedule.getStartDateTime());
        request.put("availableEndTime", schedule.getEndDateTime());

        restTemplate.put(url, request, vetId);
    }
}
