package edu.hw8.Task1;

import java.util.HashMap;
import java.util.Map;

public class Citations {
    private Citations() {
    }

    private static final Map<String, String> DICT = new HashMap<>();

    static {
        DICT.put("личности", "Не переходи на личности там, где их нет");
        DICT.put("оскорбления", "Если твои противники перешли на личные оскорбления, будь уверена — "
            + "твоя победа не за горами");
        DICT.put("глупый", "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... "
            + "Ты просто бог идиотизма.");
        DICT.put("интеллект", "Чем ниже интеллект, тем громче оскорбления");
    }

    public static String search(String key) {
        return DICT.getOrDefault(key, "С вами не поспоришь");
    }
}
