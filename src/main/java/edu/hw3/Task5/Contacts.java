package edu.hw3.Task5;

import java.util.Arrays;
import java.util.TreeSet;

public class Contacts {
    private Contacts() {

    }

    public static String[] parseContacts(String[] arr, Order ord) {
        if (arr == null) {
            return new String[]{};
        }
        TreeSet<String> contacts = switch (ord) {
            case ASC -> new TreeSet<>(new NameComparatorAsc());
            case DESC -> new TreeSet<>(new NameComparatorDesc());
        };
        contacts.addAll(Arrays.asList(arr));
        return contacts.toArray(new String[0]);
    }
}
