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

    public void updateVeterinarianAvailability(Long vetIdveterinarianId, Schedule schedule) {
        var url = "http://localhost:8010/veterinary-service/api/v1/veterinarians/{veterinarianId}/availability";

        var request = new HashMap<String, Object>();
        //request.put("availableDays", schedule.getAvailableDays());
        request.put("availableStartTime", schedule.getStartDateTime());
        request.put("availableEndTime", schedule.getEndDateTime());

        try {
            restTemplate.put(url, request, vetIdveterinarianId);
        } catch (HttpClientErrorException e) {
            System.err.println("HTTP Error while updating availability: " + e.getStatusCode());
            System.err.println("Response body: " + e.getResponseBodyAsString());
            throw new IllegalArgumentException("Error while saving schedule: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error while updating availability: " + e.getMessage());
            throw new IllegalArgumentException("Error while saving schedule: " + e.getMessage());
        }
    }
}
