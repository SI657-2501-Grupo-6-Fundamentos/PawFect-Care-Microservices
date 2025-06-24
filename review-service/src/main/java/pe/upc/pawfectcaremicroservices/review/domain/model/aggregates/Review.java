package pe.upc.pawfectcaremicroservices.review.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pe.upc.pawfectcaremicroservices.review.domain.model.commands.CreateReviewCommand;

@Getter
@Setter
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private Integer rating;
    private Long medicalAppointmentId;

    public Review() {
        this.content = "";
        this.rating = 1;
    }

    public Review(CreateReviewCommand command) {
        this();
        this.content = command.content();
        this.rating = command.rating();
    }

    public Review updateInformation(String content, Integer rating) {
        this.content = content;
        this.rating = rating;
        return this;
    }
}
