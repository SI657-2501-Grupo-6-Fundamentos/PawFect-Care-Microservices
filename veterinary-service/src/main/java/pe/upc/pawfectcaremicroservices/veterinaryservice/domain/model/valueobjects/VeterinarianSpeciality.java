package pe.upc.pawfectcaremicroservices.veterinaryservice.domain.model.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum VeterinarianSpeciality {
    GENERAL_MEDICINE("GENERAL MEDICINE"),
    VETERINARY_SURGERY("VETERINARY SURGERY"),
    VETERINARY_PATHOLOGY("VETERINARY PATHOLOGY"),
    VETERINARY_RADIOLOGY("VETERINARY RADIOLOGY"),
    VETERINARY_NUTRITION("VETERINARY NUTRITION"),
    VETERINARY_BEHAVIOR("VETERINARY BEHAVIOR"),
    VETERINARY_OPHTHALMOLOGY("VETERINARY OPHTHALMOLOGY"),
    VETERINARY_DERMATOLOGY("VETERINARY DERMATOLOGY"),
    VETERINARY_CARDIOLOGY("VETERINARY CARDIOLOGY"),
    VETERINARY_ONCOLOGY("VETERINARY ONCOLOGY"),
    VETERINARY_NEUROLOGY("VETERINARY NEUROLOGY"),
    VETERINARY_ORTHOPEDICS("VETERINARY ORTHOPEDICS"),
    VETERINARY_PHYSIOTHERAPY("VETERINARY PHYSIOTHERAPY"),
    EMERGENCY_AND_CRITICAL_CARE("EMERGENCY AND CRITICAL CARE");

    private final String value;

    VeterinarianSpeciality(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static VeterinarianSpeciality fromValue(String value) {
        for (VeterinarianSpeciality speciality : values()) {
            if (speciality.value.equalsIgnoreCase(value)) {
                return speciality;
            }
        }
        throw new IllegalArgumentException("Invalid speciality: " + value);
    }
}