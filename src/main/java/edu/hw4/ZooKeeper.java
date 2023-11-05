package edu.hw4;

import edu.hw4.errors.CatBitesError;
import edu.hw4.errors.DisproportionError;
import edu.hw4.errors.NameWithPunctuationError;
import edu.hw4.errors.ValidationError;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ZooKeeper {
    private ZooKeeper() {

    }

    public static List<Animal> sortByHeight(Collection<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::height)).toList();
    }

    public static List<Animal> sortByWeightReversed(Collection<Animal> animals, int k) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::weight).reversed()).limit(k).toList();
    }

    public static Map<Animal.Type, Long> countAnimalsByTypes(Collection<Animal> animals) {
        return animals.stream()
            .collect(Collectors.groupingBy(Animal::type, Collectors.counting()));
    }

    public static Animal getLongestName(Collection<Animal> animals) throws NoSuchElementException {
        return animals.stream()
            .max(Comparator.comparing((Animal animal) -> animal.name().length())
            .thenComparing(Animal::name, Comparator.reverseOrder())).orElseThrow();
    }

    public static Animal.Sex mostOccuringSex(Collection<Animal> animals) {
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

    public static Map<Animal.Type, Animal> heaviestAnimalPerType(Collection<Animal> animals) {
        return animals.stream()
            .collect(Collectors.toMap(Animal::type, Function.identity(),
                BinaryOperator.maxBy(Comparator.comparing(Animal::weight))));
    }

    public static Animal kthOldestAnimal(Collection<Animal> animals, int k) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::age).reversed())
            .limit(k).reduce((first, second) -> second).orElseThrow();
    }

    public static Optional<Animal> heaviestAnimalBeneathHeightLevel(Collection<Animal> animals, int k) {
        return animals.stream()
            .filter(animal -> animal.height() < k).max(Comparator.comparing(Animal::weight));
    }

    public static Integer countPaws(Collection<Animal> animals) {
        return animals.stream().mapToInt(Animal::paws).sum();
    }

    public static List<Animal> findWeirdos(Collection<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.age() != animal.paws()).toList();
    }

    public static List<Animal> findTallBiters(Collection<Animal> animals) {
        final int minHeight = 100;
        return animals.stream()
            .filter(animal -> animal.bites() && animal.height() > minHeight).toList();
    }

    public static Long findOverweightAnimals(Collection<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.height() < animal.weight()).count();
    }

    public static List<Animal> findAnimalsWithLongName(Collection<Animal> animals) {
        final Predicate<Animal> filter = animal -> {
            Pattern pattern = Pattern.compile("\\w\\s+\\w");
            Matcher matcher = pattern.matcher(animal.name());
            matcher.find();
            return matcher.find();
        };
        return animals.stream()
            .filter(filter).toList();
    }

    public static Boolean findTallDog(Collection<Animal> animals, int k) {
        return animals.stream()
            .anyMatch(animal -> animal.type() == Animal.Type.DOG && animal.height() > k);
    }

    public static Map<Animal.Type, Integer> totalMassPerTypeWithinAgeRange(Collection<Animal> animals, int k, int l) {
        return animals.stream()
            .filter(animal -> animal.age() >= k && animal.age() <= l)
            .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(Animal::weight)));
    }

    public static List<Animal> sortBySeveralProperties(Collection<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::type).thenComparing(Animal::sex).thenComparing(Animal::name)).toList();
    }

    public static Boolean spiderBitesMoreThanDog(Collection<Animal> animals) {
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

    public static Optional<Animal> findSupremeFish(ArrayList<Collection<Animal>> zoos) {
        ArrayList<Optional<Animal>> aquarium = new ArrayList<>();
        for (var animals : zoos) {
            aquarium.add(animals.stream()
                .filter(animal -> animal.type() == Animal.Type.FISH)
                .max(Comparator.comparing(Animal::weight)));
        }
        return aquarium.stream()
            .filter(Optional::isPresent).map(Optional::get).max(Comparator.comparing(Animal::weight));
    }

    public static Map<String, Set<ValidationError>> findInvalidAnimals(Collection<Animal> animals) {
        Predicate<Animal> checkForErrors = animal -> {
            ArrayList<ValidationError> errors = new ArrayList<>();
            errors.add(new CatBitesError());
            errors.add(new DisproportionError());
            errors.add(new NameWithPunctuationError());
            for (var error : errors) {
                if (error.check(animal)) {
                    return true;
                }
            }
            return false;
        };

        return animals.stream()
            .filter(checkForErrors).collect(Collectors.toMap(Animal::name, animal -> {
                Set<ValidationError> errors = new HashSet<>();
                errors.add(new CatBitesError());
                errors.add(new DisproportionError());
                errors.add(new NameWithPunctuationError());
                errors.removeIf(error -> !error.check(animal));
                return errors;
            }));
    }

    public static Map<String, String> findInvalidAnimalsWithDetails(Collection<Animal> animals) {
        Predicate<Animal> checkForErrors = animal -> {
            ArrayList<ValidationError> errors = new ArrayList<>();
            errors.add(new CatBitesError());
            errors.add(new DisproportionError());
            errors.add(new NameWithPunctuationError());
            for (var error : errors) {
                if (error.check(animal)) {
                    return true;
                }
            }
            return false;
        };

        return animals.stream()
            .filter(checkForErrors).collect(Collectors.toMap(Animal::name, animal -> {
                StringBuilder str = new StringBuilder();
                ArrayList<ValidationError> errors = new ArrayList<>();
                errors.add(new CatBitesError());
                errors.add(new DisproportionError());
                errors.add(new NameWithPunctuationError());
                for (var error : errors) {
                    if (error.check(animal)) {
                        str.append(error.getFields()).append(" ");
                    }
                }
                str.deleteCharAt(str.length() - 1);
                return str.toString();
            }));
    }

}
