package edu.hw4;

import edu.hw4.errors.CatBitesError;
import edu.hw4.errors.DisproportionError;
import edu.hw4.errors.NameWithPunctuationError;
import edu.hw4.errors.ValidationError;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ZooKeeper {
    private ZooKeeper() {

    }

    private static final int MIN_HEIGHT = 100;

    private static final List<ValidationError> ERROR_COLLECTION =
        Arrays.asList(new CatBitesError(), new DisproportionError(), new NameWithPunctuationError());

    public static List<Animal> sortByHeight(Collection<Animal> animals) {  // Task1
        return animals.stream()
            .sorted(Comparator.comparing(Animal::height)).toList();
    }

    public static List<Animal> sortByWeightReversed(Collection<Animal> animals, int k) {  // Task2
        return animals.stream()
            .sorted(Comparator.comparing(Animal::weight).reversed()).limit(k).toList();
    }

    public static Map<Animal.Type, Long> countAnimalsByTypes(Collection<Animal> animals) {  // Task3
        return animals.stream()
            .collect(Collectors.groupingBy(Animal::type, Collectors.counting()));
    }

    public static Animal getLongestName(Collection<Animal> animals) throws NoSuchElementException {  // Task4
        return animals.stream()
            .max(Comparator.comparing((Animal animal) -> animal.name().length())
            .thenComparing(Animal::name, Comparator.reverseOrder())).orElseThrow();
    }

    public static Animal.Sex mostOccuringSex(Collection<Animal> animals) {  // Task5
        Map<Animal.Sex, Long> counts = animals.stream()
            .collect(Collectors.groupingBy(Animal::sex, Collectors.counting()));
        if (counts.isEmpty()) {
            throw new NoSuchElementException("No elements in collection");
        }
        if (counts.get(Animal.Sex.M) == null) {
            return Animal.Sex.F;
        } else if (counts.get(Animal.Sex.F) == null) {
            return Animal.Sex.M;
        } else {
            return counts.get(Animal.Sex.M) < counts.get(Animal.Sex.F) ? Animal.Sex.F : Animal.Sex.M;
        }

    }

    public static Map<Animal.Type, Animal> heaviestAnimalPerType(Collection<Animal> animals) {  // Task6
        return animals.stream()
            .collect(Collectors.toMap(Animal::type, Function.identity(),
                BinaryOperator.maxBy(Comparator.comparing(Animal::weight))));
    }

    public static Animal kthOldestAnimal(Collection<Animal> animals, int k) {  // Task7
        return animals.stream()
            .sorted(Comparator.comparing(Animal::age).reversed())
            .limit(k).reduce((first, second) -> second).orElseThrow();
    }

    public static Optional<Animal> heaviestAnimalBeneathHeightLevel(Collection<Animal> animals, int k) {  // Task8
        return animals.stream()
            .filter(animal -> animal.height() < k).max(Comparator.comparing(Animal::weight));
    }

    public static Integer countPaws(Collection<Animal> animals) {  // Task9
        return animals.stream().mapToInt(Animal::paws).sum();
    }

    public static List<Animal> findWeirdos(Collection<Animal> animals) {  // Task10
        return animals.stream()
            .filter(animal -> animal.age() != animal.paws()).toList();
    }

    public static List<Animal> findTallBiters(Collection<Animal> animals) {  // Task11
        return animals.stream()
            .filter(animal -> animal.bites() && animal.height() > MIN_HEIGHT).toList();
    }

    public static Long findOverweightAnimals(Collection<Animal> animals) {  // Task12
        return animals.stream()
            .filter(animal -> animal.height() < animal.weight()).count();
    }

    public static List<Animal> findAnimalsWithLongName(Collection<Animal> animals) {  // Task13
        final Predicate<Animal> filter = animal -> animal.name().split("\\s+").length > 2;
        return animals.stream()
            .filter(filter).toList();
    }

    public static Boolean findTallDog(Collection<Animal> animals, int k) {  // Task14
        return animals.stream()
            .anyMatch(animal -> animal.type() == Animal.Type.DOG && animal.height() > k);
    }

    public static Map<Animal.Type, Integer> totalMassPerTypeWithinAgeRange(Collection<Animal> animals,
        int k, int l) {  // Task15
        return animals.stream()
            .filter(animal -> animal.age() >= k && animal.age() <= l)
            .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(Animal::weight)));
    }

    public static List<Animal> sortBySeveralProperties(Collection<Animal> animals) {  // Task16
        return animals.stream()
            .sorted(Comparator.comparing(Animal::type).thenComparing(Animal::sex).thenComparing(Animal::name)).toList();
    }

    public static Boolean spiderBitesMoreThanDog(Collection<Animal> animals) {  // Task17
        Map<Animal.Type, Long> dict1 = animals.stream()
            .filter(animal -> animal.type() == Animal.Type.DOG || animal.type() == Animal.Type.SPIDER)
            .collect(Collectors.groupingBy(Animal::type, Collectors.counting()));

        Map<Animal.Type, Long> dict2 = animals.stream()
            .filter(animal -> animal.bites()
                && (animal.type() == Animal.Type.DOG || animal.type() == Animal.Type.SPIDER))
            .collect(Collectors.groupingBy(Animal::type, Collectors.counting()));

        if (dict1.size() < 2) {
            return false;
        }

        if (dict2.size() == 2) {
            return dict2.get(Animal.Type.SPIDER) > dict2.get(Animal.Type.DOG);
        } else if (dict2.size() == 1) {
            return dict2.containsKey(Animal.Type.SPIDER);
        }

        return false;
    }

    @SafeVarargs
    public static Optional<Animal> findSupremeFish(List<Animal>... zoos) {  // Task18
        return Arrays.stream(zoos).flatMap(List::stream)
            .filter(animal -> animal.type() == Animal.Type.FISH)
            .max(Comparator.comparing(Animal::weight));
    }

    public static Map<String, Set<ValidationError>> findInvalidAnimals(Collection<Animal> animals) {  // Task19
        return animals.stream()
            .filter(animal -> ERROR_COLLECTION.stream().anyMatch(error -> error.check(animal)))
            .collect(Collectors.toMap(Animal::name,
                animal -> ERROR_COLLECTION.stream().filter(error -> error.check(animal)).collect(Collectors.toSet())
            ));
    }

    public static Map<String, String> findInvalidAnimalsWithDetails(Collection<Animal> animals) {  // Task20

        return animals.stream()
            .filter(animal -> ERROR_COLLECTION.stream().anyMatch(error -> error.check(animal)))
            .collect(Collectors.toMap(Animal::name, animal -> {
                StringBuilder str = new StringBuilder();
                List<ValidationError> errors = ERROR_COLLECTION
                    .stream().filter(error -> error.check(animal)).toList();
                for (var error : errors) {
                    str.append(error.getFields()).append(" ");
                }
                str.deleteCharAt(str.length() - 1);
                return str.toString();
            }));
    }

}
