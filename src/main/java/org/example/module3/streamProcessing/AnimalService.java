package org.example.module3.streamProcessing;

import java.util.*;
import java.util.stream.Collectors;

public class AnimalService {
    // Задача 1: Отсортировать животных по росту от самого маленького к самому большому
    public static List<Animal> sortAnimalsByHeight(List<Animal> animals) {
        return animals.stream()
                .sorted(Comparator.comparingInt(Animal::height))
                .collect(Collectors.toList());
    }

    // Задача 2: Отсортировать животных по весу от самого тяжелого к самому легкому, выбрать k первых
    public static List<Animal> getTopKHeaviestAnimals(List<Animal> animals, int k) {
        return animals.stream()
                .sorted(Comparator.comparingInt(Animal::weight).reversed())
                .limit(k)
                .collect(Collectors.toList());
    }

    // Задача 3: Сколько животных каждого вида
    public static Map<Animal.Type, Long> countAnimalsByType(List<Animal> animals) {
        return animals.stream()
                .collect(Collectors.groupingBy(Animal::type, Collectors.counting()));
    }

    // Задача 4: У какого животного самое длинное имя
    public static Optional<Animal> getAnimalWithLongestName(List<Animal> animals) {
        return animals.stream()
                .max(Comparator.comparingInt(animal -> animal.name().length()));
    }

    // Задача 5: Каких животных больше: самцов или самок
    public static Optional<Animal.Sex> getDominantSex(List<Animal> animals) {
        long males = animals.stream().filter(animal -> animal.sex() == Animal.Sex.M).count();
        long females = animals.size() - males;
        return males > females ? Optional.of(Animal.Sex.M) : (females > males ? Optional.of(Animal.Sex.F) : Optional.empty());
    }

    // Задача 6: Самое тяжелое животное каждого вида
    public static Map<Animal.Type, Animal> getHeaviestAnimalByType(List<Animal> animals) {
        return animals.stream()
                .collect(Collectors.groupingBy(Animal::type,
                        Collectors.reducing((a, b) -> a.weight() > b.weight() ? a : b)))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        entry -> entry.getValue().orElse(null)));
    }

    // Задача 7: K-е самое старое животное
    public static Optional<Animal> getKthOldestAnimal(List<Animal> animals, int k) {
        return animals.stream()
                .sorted(Comparator.comparingInt(Animal::age))
                .skip(k - 1)
                .findFirst();
    }

    // Задача 8: Самое тяжелое животное среди животных ниже k см
    public static Optional<Animal> getHeaviestAnimalUnderHeight(List<Animal> animals, int height) {
        return animals.stream()
                .filter(animal -> animal.height() < height)
                .max(Comparator.comparingInt(Animal::weight));
    }

    // Задача 9: Сколько в сумме лап у животных в списке
    public static int totalPaws(List<Animal> animals) {
        return animals.stream()
                .mapToInt(Animal::paws)
                .sum();
    }

    // Задача 10: Список животных, возраст у которых не совпадает с количеством лап
    public static List<Animal> getAnimalsWithAgeNotMatchingPaws(List<Animal> animals) {
        return animals.stream()
                .filter(animal -> animal.age() != animal.paws())
                .collect(Collectors.toList());
    }

    // Задача 11: Список животных, которые могут укусить (bites == true) и рост которых превышает 100 см
    public static List<Animal> getBitingAnimalsOverHeight(List<Animal> animals, int height) {
        return animals.stream()
                .filter(animal -> animal.bites() && animal.height() > height)
                .collect(Collectors.toList());
    }

    // Задача 12: Сколько в списке животных, вес которых превышает рост
    public static long countAnimalsWeightGreaterThanHeight(List<Animal> animals) {
        return animals.stream()
                .filter(animal -> animal.weight() > animal.height())
                .count();
    }

    // Задача 13: Список животных, имена которых состоят из более чем двух слов
    public static List<Animal> getAnimalsWithNamesMoreThanTwoWords(List<Animal> animals) {
        return animals.stream()
                .filter(animal -> animal.name().split(" ").length > 2)
                .collect(Collectors.toList());
    }

    // Задача 14: Есть ли в списке собака ростом более k см
    public static boolean isDogOverHeight(List<Animal> animals, int height) {
        return animals.stream()
                .anyMatch(animal -> animal.type() == Animal.Type.DOG && animal.height() > height);
    }

    // Задача 15: Найти суммарный вес животных каждого вида, которым от k до l лет
    public static Map<Animal.Type, Integer> getTotalWeightByTypeWithinAgeRange(List<Animal> animals, int k, int l) {
        return animals.stream()
                .filter(animal -> animal.age() >= k && animal.age() <= l)
                .collect(Collectors.groupingBy(Animal::type,
                        Collectors.summingInt(Animal::weight)));
    }

    // Задача 16: Список животных, отсортированный по виду, затем по полу, затем по имени
    public static List<Animal> sortAnimalsByTypeSexAndName(List<Animal> animals) {
        return animals.stream()
                .sorted(Comparator.comparing(Animal::type)
                        .thenComparing(Animal::sex)
                        .thenComparing(Animal::name))
                .collect(Collectors.toList());
    }

    // Задача 17: Правда ли, что пауки кусаются чаще, чем собаки
    public static boolean areSpidersBitingMoreThanDogs(List<Animal> animals) {
        long spiderBites = animals.stream()
                .filter(animal -> animal.type() == Animal.Type.SPIDER && animal.bites())
                .count();
        long dogBites = animals.stream()
                .filter(animal -> animal.type() == Animal.Type.DOG && animal.bites())
                .count();
        return spiderBites > dogBites;
    }

    // Задача 18: Найти самую тяжелую рыбку в 2-х или более списках
    public static Animal getHeaviestFishFromLists(List<List<Animal>> lists) {
        return lists.stream()
                .flatMap(List::stream)
                .filter(animal -> animal.type() == Animal.Type.FISH)
                .max(Comparator.comparingInt(Animal::weight))
                .orElse(null);
    }

    // Задача 19: Животные, в записях о которых есть ошибки: вернуть имя и список ошибок
    public static Map<String, Set<ValidationError>> validateAnimals(List<Animal> animals) {
        Map<String, Set<ValidationError>> validationErrors = new HashMap<>();

        for (Animal animal : animals) {
            Set<ValidationError> errors = new HashSet<>();
            if (animal.age() < 0) {
                errors.add(ValidationError.INVALID_AGE);
            }
            if (animal.height() < 0) {
                errors.add(ValidationError.INVALID_HEIGHT);
            }
            if (animal.weight() < 0) {
                errors.add(ValidationError.INVALID_WEIGHT);
            }
            if (!errors.isEmpty()) {
                validationErrors.put(animal.name(), errors);
            }
        }

        return validationErrors;
    }

    // Задача 20: Сделать результат предыдущего задания более читабельным
    public static Map<String, String> getReadableValidationErrors(List<Animal> animals) {
        Map<String, Set<ValidationError>> errorsMap = validateAnimals(animals);
        Map<String, String> readableErrors = new HashMap<>();

        for (Map.Entry<String, Set<ValidationError>> entry : errorsMap.entrySet()) {
            String animalName = entry.getKey();
            String errorFields = entry.getValue().stream()
                    .map(ValidationError::name)
                    .collect(Collectors.joining(", "));
            readableErrors.put(animalName, errorFields);
        }

        return readableErrors;
    }

    // Внутренний класс для ошибок валидации
    public enum ValidationError {
        INVALID_AGE, INVALID_HEIGHT, INVALID_WEIGHT
    }
}
