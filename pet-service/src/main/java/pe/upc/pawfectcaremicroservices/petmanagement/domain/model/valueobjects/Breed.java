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
    LABRADOR,
    PUG,
    BEAGLE,
    BULLDOG,
    GOLDEN_RETRIEVER,
    CHIHUAHUA,
    MIXED_BREED_DOG,
    PERUVIAN_DOG,
    POODLE,
    OTHER_DOG,

    // Cat breeds common in Peru
    DOMESTIC_SHORTHAIR,
    PERSIAN,
    SIAMESE,
    SPHYNX,
    BENGAL,
    URAL_REX,
    KORAT,
    SCOTTISH_FOLD,
    SIBERIAN,
    SOMALI,
    TONKINESE,
    HIMALAYAN,
    OTHER_CAT,

    // Parrot types
    COCKATIEL,
    BUDGERIGAR,
    AFRICAN_GREY,
    AMAZON_PARROT,
    LOVE_BIRD,
    OTHER_PARROT,

    // Hamster types
    SYRIAN_HAMSTER,
    DWARF_CAMPBELL,
    WINTER_WHITE,
    CHINESE_HAMSTER,
    OTHER_HAMSTER,

    // Chinchilla types
    STANDARD_GRAY,
    WHITE_MOSAIC,
    BLACK_VELVET,
    BEIGE,
    EBONY,
    OTHER_CHINCHILLA,

    // Other animal
    OTHER_ANIMAL
}