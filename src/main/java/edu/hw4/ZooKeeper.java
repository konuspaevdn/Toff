package edu.hw4;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class ZooKeeper {
    private ZooKeeper() {

    }

    public static List<Animal> sortByHeight(Collection<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::height)).toList();
    }


}
