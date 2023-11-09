package edu.hw3.Task5;

import java.util.Comparator;

public class NameComparatorAsc implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        String delimiter = "\\s++";
        String[] initials1 = o1.split(delimiter);
        String[] initials2 = o2.split(delimiter);
        return initials1[initials1.length - 1].compareTo(initials2[initials2.length - 1]);
    }
}
