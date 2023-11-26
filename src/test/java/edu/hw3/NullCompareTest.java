package edu.hw3;

import edu.hw3.Task7.NullFriendlyComparator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.TreeMap;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class NullCompareTest {
    @Test
    @DisplayName("Check null friendly comparator")
    void checkNullFriendlyComparator() {
        TreeMap<String, String> tree = new TreeMap<>(new NullFriendlyComparator<>(String::compareTo));
        tree.put(null, "test");
        assertThat(tree.containsKey(null)).isTrue();
        tree.put("abc", "Willy");
        tree.put(null, "Wonka");
        assertThat(tree.size()).isEqualTo(2);
    }
}
