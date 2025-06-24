package pe.upc.pawfectcaremicroservices.review.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.upc.pawfectcaremicroservices.review.domain.model.aggregates.Review;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    boolean existsById(Long id);
    List<Review> findAllByMedicalAppointmentId(Long medicalAppointmentId);
}
