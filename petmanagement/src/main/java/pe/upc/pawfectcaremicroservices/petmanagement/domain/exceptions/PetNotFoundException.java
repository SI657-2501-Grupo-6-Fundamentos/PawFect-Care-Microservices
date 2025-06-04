package pe.upc.pawfectcaremicroservices.petmanagement.domain.exceptions;

public class PetNotFoundException extends RuntimeException {
    public PetNotFoundException(Long aLong) {
        super("Pet with id " + aLong + " not found");
    }
}
