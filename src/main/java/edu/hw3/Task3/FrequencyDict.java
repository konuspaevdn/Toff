package edu.hw3.Task3;

import java.util.HashMap;

public class FrequencyDict {
    private FrequencyDict() {

    }

    public static HashMap<Object, Integer> freqDict(Object[] arr) {
        HashMap<Object, Integer> map = new HashMap<>();
        for (Object a : arr) {
            int count = map.getOrDefault(a, 0) + 1;
            map.put(a, count);
        }
        return map;
    }
}
