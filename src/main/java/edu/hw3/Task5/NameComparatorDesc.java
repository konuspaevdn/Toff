package edu.hw3.Task5;

public class NameComparatorDesc extends NameComparatorAsc {
    @Override
    public int compare(String o1, String o2) {
        return -1 * super.compare(o1, o2);
    }
}
