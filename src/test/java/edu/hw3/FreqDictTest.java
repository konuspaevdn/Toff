package edu.hw3;

import edu.hw3.Task3.FrequencyDict;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FreqDictTest {
    @Test
    @DisplayName("Check result dict")
    void checkFreqDict() {
        String[] arr1 = new String[]{"this", "and", "that", "and"};
        HashMap<Object, Integer> a = FrequencyDict.freqDict(arr1);
        assertThat(a.get("this")).isEqualTo(1);
        assertThat(a.get("that")).isEqualTo(1);
        assertThat(a.get("and")).isEqualTo(2);

        Integer[] arr2 = new Integer[]{1, 2, 1, 1, 7, 1, 2, 7, 7};
        HashMap<Object, Integer> b = FrequencyDict.freqDict(arr2);
        assertThat(b.get(1)).isEqualTo(4);
        assertThat(b.get(2)).isEqualTo(2);
        assertThat(b.get(7)).isEqualTo(3);
    }
}
