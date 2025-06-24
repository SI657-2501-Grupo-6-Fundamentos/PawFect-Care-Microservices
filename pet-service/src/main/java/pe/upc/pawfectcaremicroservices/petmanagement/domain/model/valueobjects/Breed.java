package pe.upc.pawfectcaremicroservices.petmanagement.domain.model.valueobjects;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

/**
 * Enum representing pet breeds categorized by animal type.
 */
@Getter
public enum Breed {
    // Dog breeds common in Peru
    LABRADOR(AnimalType.DOG),
    PUG(AnimalType.DOG),
    BEAGLE(AnimalType.DOG),
    BULLDOG(AnimalType.DOG),
    GOLDEN_RETRIEVER(AnimalType.DOG),
    CHIHUAHUA(AnimalType.DOG),
    MIXED_BREED_DOG(AnimalType.DOG),
    PERUVIAN_DOG(AnimalType.DOG),
    POODLE(AnimalType.DOG),
    ANY_DOG(AnimalType.DOG),

    // Cat breeds common in Peru
    DOMESTIC_SHORTHAIR(AnimalType.CAT),
    PERSIAN(AnimalType.CAT),
    SIAMESE(AnimalType.CAT),
    SPHYNX(AnimalType.CAT),
    BENGAL(AnimalType.CAT),
    KHAO_MANEE(AnimalType.CAT),
    URAL_REX(AnimalType.CAT),
    KORAT(AnimalType.CAT),
    RAGDOLL(AnimalType.CAT),
    SCOTTISH_FOLD(AnimalType.CAT),
    SIBERIAN(AnimalType.CAT),
    SOMALI(AnimalType.CAT),
    TONKINESE(AnimalType.CAT),
    HIMALAYAN(AnimalType.CAT),
    ANY_CAT(AnimalType.CAT),

    // Parrot types
    COCKATIEL(AnimalType.PARROT),
    BUDGERIGAR(AnimalType.PARROT),
    AFRICAN_GREY(AnimalType.PARROT),
    AMAZON_PARROT(AnimalType.PARROT),
    LOVE_BIRD(AnimalType.PARROT),
    ANY_PARROT(AnimalType.PARROT),

    // Hamster types
    SYRIAN_HAMSTER(AnimalType.HAMSTER),
    DWARF_CAMPBELL(AnimalType.HAMSTER),
    ROBOROVSKI(AnimalType.HAMSTER),
    WINTER_WHITE(AnimalType.HAMSTER),
    CHINESE_HAMSTER(AnimalType.HAMSTER),
    ANY_HAMSTER(AnimalType.HAMSTER),

    // Chinchilla types
    STANDARD_GRAY(AnimalType.CHINCHILLA),
    WHITE_MOSAIC(AnimalType.CHINCHILLA),
    BLACK_VELVET(AnimalType.CHINCHILLA),
    BEIGE(AnimalType.CHINCHILLA),
    EBONY(AnimalType.CHINCHILLA);


    private final AnimalType animalType;

    Breed(AnimalType animalType) {
        this.animalType = animalType;
    }

    /**
     * Returns a list of breeds associated with a specific animal type.
     *
     * @param animalType the type of animal (e.g., DOG, CAT)
     * @return list of breeds for the specified animal type
     */
    public static List<Breed> getBreedsByType(AnimalType animalType) {
        return Arrays.stream(values())
                .filter(breed -> breed.animalType == animalType)
                .toList();
    }
}