package pe.upc.pawfectcaremicroservices.medicalrecordservice.application.external.diagnostics;

import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class ExternalDiagnostic {
    private final RestTemplate restTemplate;

    public ExternalDiagnostic(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean existsDiagnosticById(Long treatmentId) {
        try {
            restTemplate.getForObject("http://localhost:8010/treatment-service/api/v1/treatments/{treatmentId}", Object.class, treatmentId);
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
