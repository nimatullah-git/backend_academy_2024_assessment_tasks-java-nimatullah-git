package org.example.module3;

import org.example.module3.streamProcessing.Animal;
import org.example.module3.streamProcessing.AnimalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class AnimalServiceTest {
    private List<Animal> animals;

    @BeforeEach
    void setUp() {
        animals = List.of(
                new Animal("Whiskers", Animal.Type.CAT, Animal.Sex.M, 5, 30, 4, true),
                new Animal("Silk", Animal.Type.SPIDER, Animal.Sex.F, 3, 20, 2, false),
                new Animal("Bubbles", Animal.Type.FISH, Animal.Sex.F, 1, 10, 1, false),
                new Animal("Maximus", Animal.Type.DOG, Animal.Sex.M, 7, 60, 20, true),
                new Animal("Tweety Bird", Animal.Type.BIRD, Animal.Sex.F, 2, 15, 2, false),
                new Animal("Charlie the Brave", Animal.Type.DOG, Animal.Sex.M, 5, 55, 10, false),
                new Animal("Lassie the Great", Animal.Type.DOG, Animal.Sex.F, 6, 70, 18, true),
                new Animal("Webster", Animal.Type.SPIDER, Animal.Sex.M, 4, 15, 6, false),
                new Animal("Unknown", Animal.Type.CAT, Animal.Sex.M, -1, 15, -1, false),
                new Animal("Rex", Animal.Type.DOG, Animal.Sex.M, 4, -1, 4, true)
        );
    }

    @Test
    @DisplayName("Should sort animals by height ascending")
    void testSortAnimalsByHeight() {
        List<Animal> sortedAnimals = AnimalService.sortAnimalsByHeight(animals);
        assertThat(sortedAnimals)
                .isSortedAccordingTo(Comparator.comparingInt(Animal::height))
                .extracting(Animal::name)
                .containsExactly("Rex",
                        "Bubbles",
                        "Tweety Bird",
                        "Webster",
                        "Unknown",
                        "Silk",
                        "Whiskers",
                        "Charlie the Brave",
                        "Maximus",
                        "Lassie the Great");
    }

    @Test
    @DisplayName("Should return top k heaviest animals")
    void testGetTopKHeaviestAnimals() {
        int k = 2;
        List<Animal> topHeaviestAnimals = AnimalService.getTopKHeaviestAnimals(animals, k);
        assertThat(topHeaviestAnimals)
                .hasSize(k)
                .extracting(Animal::name)
                .containsExactly("Maximus", "Lassie the Great");
    }

    @Test
    @DisplayName("Should count animals by type")
    void testCountAnimalsByType() {
        Map<Animal.Type, Long> animalCountByType = AnimalService.countAnimalsByType(animals);
        assertThat(animalCountByType)
                .hasSize(5)
                .containsEntry(Animal.Type.CAT, 2L)
                .containsEntry(Animal.Type.DOG, 4L)
                .containsEntry(Animal.Type.BIRD, 1L)
                .containsEntry(Animal.Type.FISH, 1L)
                .containsEntry(Animal.Type.SPIDER, 2L);
    }

    @Test
    @DisplayName("Should return animal with longest name")
    void testGetAnimalWithLongestName() {
        Optional<Animal> animalWithLongestName = AnimalService.getAnimalWithLongestName(animals);
        assertThat(animalWithLongestName)
                .isPresent()
                .get()
                .extracting(Animal::name)
                .isEqualTo("Charlie the Brave");
    }

    @Test
    @DisplayName("Should return the dominant sex of animals")
    void testGetDominantSex() {
        Optional<Animal.Sex> dominantSex = AnimalService.getDominantSex(animals);
        assertThat(dominantSex)
                .isPresent()
                .get()
                .isEqualTo(Animal.Sex.M);
    }

    @Test
    @DisplayName("Should return heaviest animal by type")
    void testGetHeaviestAnimalByType() {
        Map<Animal.Type, Animal> heaviestAnimals = AnimalService.getHeaviestAnimalByType(animals);
        assertThat(heaviestAnimals)
                .containsEntry(Animal.Type.CAT, new Animal("Whiskers", Animal.Type.CAT, Animal.Sex.M, 5, 30, 4, true));
    }

    @Test
    @DisplayName("Should return k-th oldest animal")
    void testGetKthOldestAnimal() {
        int k = 2;
        Optional<Animal> kthOldestAnimal = AnimalService.getKthOldestAnimal(animals, k);
        assertThat(kthOldestAnimal)
                .isPresent()
                .get()
                .extracting(Animal::name)
                .isEqualTo("Bubbles");
    }

    @Test
    @DisplayName("Should return the heaviest animal under a certain height")
    void testGetHeaviestAnimalUnderHeight() {
        int height = 55;
        Optional<Animal> heaviestAnimal = AnimalService.getHeaviestAnimalUnderHeight(animals, height);
        assertThat(heaviestAnimal)
                .isPresent()
                .get()
                .extracting(Animal::name)
                .isEqualTo("Webster");
    }

    @Test
    @DisplayName("Should return total number of paws of all animals")
    void testTotalPaws() {
        int totalPaws = AnimalService.totalPaws(animals);
        assertThat(totalPaws).isEqualTo(42);
    }

    @Test
    @DisplayName("Should return animals with age not matching their paws")
    void testGetAnimalsWithAgeNotMatchingPaws() {
        List<Animal> mismatchedAnimals = AnimalService.getAnimalsWithAgeNotMatchingPaws(animals);
        assertThat(mismatchedAnimals)
                .extracting(Animal::name)
                .containsExactlyInAnyOrder("Whiskers",
                        "Silk",
                        "Bubbles",
                        "Maximus",
                        "Charlie the Brave",
                        "Lassie the Great",
                        "Webster",
                        "Unknown");
    }

    @Test
    @DisplayName("Should return biting animals over a certain height")
    void testGetBitingAnimalsOverHeight() {
        int height = 50;
        List<Animal> bitingAnimals = AnimalService.getBitingAnimalsOverHeight(animals, height);
        assertThat(bitingAnimals)
                .extracting(Animal::name)
                .containsExactly("Maximus", "Lassie the Great");
    }

    @Test
    @DisplayName("Should count animals with weight greater than height")
    void testCountAnimalsWeightGreaterThanHeight() {
        long count = AnimalService.countAnimalsWeightGreaterThanHeight(animals);
        assertThat(count).isEqualTo(1);
    }

    @Test
    @DisplayName("Should return animals with names consisting of more than two words")
    void testGetAnimalsWithNamesMoreThanTwoWords() {
        List<Animal> animalsWithLongNames = AnimalService.getAnimalsWithNamesMoreThanTwoWords(animals);
        assertThat(animalsWithLongNames)
                .extracting(Animal::name)
                .containsExactly("Charlie the Brave", "Lassie the Great");
    }

    @Test
    @DisplayName("Should check if there's a dog over a certain height")
    void testIsDogOverHeight() {
        int height = 60;
        boolean exists = AnimalService.isDogOverHeight(animals, height);
        assertThat(exists).isTrue();
    }

    @Test
    @DisplayName("Should return total weight by type within age range")
    void testGetTotalWeightByTypeWithinAgeRange() {
        int k = 1, l = 6;
        Map<Animal.Type, Integer> totalWeightByType = AnimalService.getTotalWeightByTypeWithinAgeRange(animals, k, l);
        assertThat(totalWeightByType)
                .containsEntry(Animal.Type.CAT, 4)
                .containsEntry(Animal.Type.DOG, 32)
                .containsEntry(Animal.Type.BIRD, 2)
                .containsEntry(Animal.Type.FISH, 1)
                .containsEntry(Animal.Type.SPIDER, 8);
    }

    @Test
    @DisplayName("Should return a sorted list of animals by type, sex and name")
    void testSortAnimalsByTypeSexAndName() {
        List<Animal> sortedAnimals = AnimalService.sortAnimalsByTypeSexAndName(animals);
        assertThat(sortedAnimals)
                .extracting(Animal::name)
                .containsExactly("Unknown",
                        "Whiskers",
                        "Charlie the Brave",
                        "Maximus",
                        "Rex",
                        "Lassie the Great",
                        "Tweety Bird",
                        "Bubbles",
                        "Webster",
                        "Silk");
    }

    @Test
    @DisplayName("Should determine if spiders bite more than dogs")
    void testAreSpidersBitingMoreThanDogs() {
        boolean result = AnimalService.areSpidersBitingMoreThanDogs(animals);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Should return the heaviest fish from multiple lists")
    void testGetHeaviestFishFromLists() {
        List<Animal> list1 = List.of(new Animal("Bubbles", Animal.Type.FISH, Animal.Sex.F, 1, 10, 1, false));
        List<Animal> list2 = List.of(new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 2, 15, 5, false));
        List<Animal> list3 = List.of(new Animal("Dory", Animal.Type.FISH, Animal.Sex.F, 2, 15, 3, false));
        List<List<Animal>> lists = List.of(list1, list2, list3);
        Animal heaviestFish = AnimalService.getHeaviestFishFromLists(lists);
        assertThat(heaviestFish)
                .extracting(Animal::name)
                .isEqualTo("Nemo");
    }

    @Test
    @DisplayName("Should return validation errors for animals")
    void testValidateAnimals() {
        Map<String, Set<AnimalService.ValidationError>> validationErrors = AnimalService.validateAnimals(animals);
        assertThat(validationErrors).containsKeys("Rex", "Unknown");
        assertThat(validationErrors.get("Rex")).contains(AnimalService.ValidationError.INVALID_HEIGHT);
        assertThat(validationErrors.get("Unknown")).contains(AnimalService.ValidationError.INVALID_AGE).contains(AnimalService.ValidationError.INVALID_WEIGHT);
    }

    @Test
    @DisplayName("Should return readable validation errors")
    void testGetReadableValidationErrors() {
        Map<String, String> readableErrors = AnimalService.getReadableValidationErrors(animals);
        assertThat(readableErrors)
                .containsEntry("Rex", "INVALID_HEIGHT")
                .containsEntry("Unknown", "INVALID_WEIGHT, INVALID_AGE");
    }
}
