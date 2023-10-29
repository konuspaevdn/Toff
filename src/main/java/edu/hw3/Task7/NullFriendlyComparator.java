package edu.hw3.Task7;

import java.util.Comparator;

public class NullFriendlyComparator<T> implements Comparator<T> {

    private final Comparator<T> comparator;

    public NullFriendlyComparator(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public int compare(T o1, T o2) {
        if (o1 == null && o2 == null) {
            return 0;
        }
        if (o1 == null) {
            return 1;
        } else if (o2 == null) {
            return -1;
        } else {
            return comparator.compare(o1, o2);
        }
    }
}
