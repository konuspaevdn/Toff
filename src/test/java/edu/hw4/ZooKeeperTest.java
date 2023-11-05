package edu.hw4;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ZooKeeperTest {
    private static ArrayList<Animal> animals;

    @BeforeAll
    static void init() {
        animals = new ArrayList<>();
        animals.add(new Animal("Dingus the Wisest", Animal.Type.DOG, Animal.Sex.M, 4, 67, 30, false));
        animals.add(new Animal("Scratch", Animal.Type.CAT, Animal.Sex.M, 3, 25, 5, true));
        animals.add(new Animal("Garfield", Animal.Type.CAT, Animal.Sex.M, 6, 26, 8,  false));
        animals.add(new Animal("Seagull Beatrice III", Animal.Type.BIRD, Animal.Sex.F, 2, 56, 4, true ));
        animals.add(new Animal("Ichthys!", Animal.Type.FISH, Animal.Sex.M, 7, 6, 2, false));
        animals.add(new Animal("Mommy fingers", Animal.Type.SPIDER, Animal.Sex.F, 1, 0, 1, true));
    }

    @Test
    @DisplayName("Sort by height")
    void sortByHeightTest() {
        assertThat(ZooKeeper.sortByHeight(animals).stream().map(Animal::height).toList())
            .isEqualTo(List.of(0, 6, 25, 26, 56, 67));
    }

    @Test
    @DisplayName("Sort by weight in reverse order")
    void sortByWeightReversedTest() {
        assertThat(ZooKeeper.sortByWeightReversed(animals, 3).stream().map(Animal::weight).toList())
            .isEqualTo(List.of(30, 8, 5));
    }

    @Test
    @DisplayName("Count animal types")
    void countAnimalsByTypesTest() {
        HashMap<Animal.Type, Long> occurrences = new HashMap<>();
        occurrences.put(Animal.Type.DOG, 1L);
        occurrences.put(Animal.Type.CAT, 2L);
        occurrences.put(Animal.Type.BIRD, 1L);
        occurrences.put(Animal.Type.FISH, 1L);
        occurrences.put(Animal.Type.SPIDER, 1L);
        assertThat(ZooKeeper.countAnimalsByTypes(animals)).isEqualTo(occurrences);
    }

    @Test
    @DisplayName("Find animal with longest name")
    void findLongestNameTest() {
        assertThat(ZooKeeper.getLongestName(animals).name()).isEqualTo("Seagull Beatrice III");
    }

    @Test
    @DisplayName("Most occurring sex")
    void mostOccurringSexTest() {
        assertThat(ZooKeeper.mostOccuringSex(animals)).isEqualTo(Animal.Sex.M);
    }

    @Test
    @DisplayName("Heaviest animal per type")
    void heaviestAnimalPerTypeTest() {
        HashMap<Animal.Type, Integer> weights = new HashMap<>();
        weights.put(Animal.Type.DOG, 30);
        weights.put(Animal.Type.CAT, 8);
        weights.put(Animal.Type.BIRD, 4);
        weights.put(Animal.Type.FISH, 2);
        weights.put(Animal.Type.SPIDER, 1);
        assertThat(ZooKeeper.heaviestAnimalPerType(animals).entrySet().stream()
            .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().weight()))).isEqualTo(weights);
    }

    @Test
    @DisplayName("kth oldest animal")
    void kthOldestAnimalTest() {
        assertThat(ZooKeeper.kthOldestAnimal(animals, 3).age()).isEqualTo(4);
    }

    @Test
    @DisplayName("Heaviset animal which is beneath k cm")
    void heaviestAnimalBeneathHeightLevelTest() {
        assertThat(ZooKeeper.heaviestAnimalBeneathHeightLevel(animals, 60).orElseThrow().weight()).isEqualTo(8);
        assertThat(ZooKeeper.heaviestAnimalBeneathHeightLevel(animals, 20).orElseThrow().weight()).isEqualTo(2);
    }

    @Test
    @DisplayName("Count paws")
    void countPawsTest() {
        int paws = 0;
        for (Animal animal : animals) {
            paws += animal.paws();
        }
        assertThat(ZooKeeper.countPaws(animals)).isEqualTo(paws);
    }

    @Test
    @DisplayName("I've never seen a spider of age 8 in my life, I hope")
    void findWeirdosTest() {
        List<Animal> weirdos = new ArrayList<>();
        weirdos.add(animals.get(1));
        weirdos.add(animals.get(2));
        weirdos.add(animals.get(4));
        weirdos.add(animals.get(5));
        assertThat(ZooKeeper.findWeirdos(animals)).isEqualTo(weirdos);
    }

    @Test
    @DisplayName("Run if you want")
    void findTallBitersTest() {
        assertThat(ZooKeeper.findTallBiters(animals).size()).isEqualTo(0);
    }

    @Test
    @DisplayName("Animals whose weight is greater than height")
    void findOverweightAnimalsTest() {
        assertThat(ZooKeeper.findOverweightAnimals(animals)).isEqualTo(1);
    }

    @Test
    @DisplayName("Find animals with long name")
    void findAnimalsWithLongNameTest() {
        List<Animal> candidates = new ArrayList<>();
        candidates.add(animals.get(0));
        candidates.add(animals.get(3));
        assertThat(ZooKeeper.findAnimalsWithLongName(animals)).isEqualTo(candidates);
    }

    @Test
    @DisplayName("Check for a dog at least k cm high")
    void findTallDogTest() {
        assertThat(ZooKeeper.findTallDog(animals, 50)).isTrue();
        assertThat(ZooKeeper.findTallDog(animals, 70)).isFalse();
    }

    @Test
    @DisplayName("Calculate total mass per type within specific age range")
    void totalMassPerTypeAgeRangeTest() {
        Map<Animal.Type, Integer> weights = new HashMap<>();
        final int k = 2;
        final int l = 7;
        for (var animal : animals) {
            if (animal.age() >= k && animal.age() <= l) {
                weights.merge(animal.type(), animal.weight(), Integer::sum);
            }
        }
        assertThat(ZooKeeper.totalMassPerTypeWithinAgeRange(animals, k, l)).isEqualTo(weights);
    }

    @Test
    @DisplayName("Sort animals by several criteria")
    void sortBySeveralPropertiesTest() {
        animals.add(new Animal("Daddy Long Legs", Animal.Type.SPIDER, Animal.Sex.M, 3, 5, 0, false));
        List<String> list = new ArrayList<>();
        list.add("Garfield");  // CAT M "G..."
        list.add("Scratch");  // CAT M "S..."
        list.add("Dingus the Wisest");  // DOG
        list.add("Seagull Beatrice III");  // BIRD
        list.add("Ichthys!");  // FISH
        list.add("Daddy Long Legs");  // SPIDER M
        list.add("Mommy fingers");  // SPIDER F
        int idx = 0;
        for (var animal : ZooKeeper.sortBySeveralProperties(animals)) {
            assertThat(animal.name()).isEqualTo(list.get(idx++));
        }
        animals.remove(animals.size() - 1);
    }

    @Test
    @DisplayName("Spiders vs Dogs")
    void spiderBitesMoreThanDogTest() {
        assertThat(ZooKeeper.spiderBitesMoreThanDog(animals)).isTrue();
    }

    @Test
    @DisplayName("Find heaviest fish among sets of animals")
    void findSupremeFishTest() {
        ArrayList<Collection<Animal>> zoos = new ArrayList<>();
        zoos.add(animals);
        assertThat(ZooKeeper.findSupremeFish(zoos).orElseThrow().name()).isEqualTo("Ichthys!");

        ArrayList<Animal> animals2 = new ArrayList<>();
        animals2.add(new Animal("Maxwell", Animal.Type.CAT, Animal.Sex.M, 42, 10, 5, true));
        animals2.add(new Animal("Fishie", Animal.Type.FISH, Animal.Sex.F, 3, 4, 1, false));
        zoos.add(animals2);
        assertThat(ZooKeeper.findSupremeFish(zoos).orElseThrow().name()).isEqualTo("Ichthys!");
        animals2.add(new Animal("Bhark", Animal.Type.FISH, Animal.Sex.M, 6, 35, 28, true));
        assertThat(ZooKeeper.findSupremeFish(zoos).orElseThrow().name()).isEqualTo("Bhark");
    }

    @Test
    @DisplayName("Find invalid records")
    void findInvalidAnimalsTest() {
        var list = ZooKeeper.findInvalidAnimals(animals);
        assertThat(list.containsKey("Dingus the Wisest")).isFalse();
        assertThat(list.containsKey("Scratch")).isTrue();
        assertThat(list.containsKey("Garfield")).isFalse();
        assertThat(list.containsKey("Seagull Beatrice III")).isTrue();
        assertThat(list.containsKey("Ichthys!")).isTrue();
        assertThat(list.containsKey("Mommy fingers")).isFalse();

        var improvedList = ZooKeeper.findInvalidAnimalsWithDetails(animals);
        assertThat(improvedList.get("Scratch")).isEqualTo("bites height weight");
        assertThat(improvedList.get("Seagull Beatrice III")).isEqualTo("height weight");
        assertThat(improvedList.get("Ichthys!")).isEqualTo("name");
    }

}
