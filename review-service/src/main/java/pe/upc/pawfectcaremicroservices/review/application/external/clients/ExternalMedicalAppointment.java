package pe.upc.pawfectcaremicroservices.review.application.external.clients;

import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class ExternalMedicalAppointment {
    private final RestTemplate restTemplate;

    public ExternalMedicalAppointment(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean existsMedicalAppointmentById(Long medicalAppointmentId) {
        try {
            restTemplate.getForObject("http://localhost:8010/appointment-service/api/v1/appointments/{medicalAppointmentId}", Object.class, medicalAppointmentId);
            return true;
        } catch (HttpClientErrorException e) {
            System.err.println("HTTP Error: " + e.getStatusCode());
            return false;
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            return false;
        }
    }
}
