package edu.hw5;

import edu.hw5.Task1.Stats;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DurationTest {
    @Test
    @DisplayName("average time test")
    void averageTimeTest() {
        List<String> periods = new ArrayList<>();
        periods.add("2022-03-12, 20:20 - 2022-03-12, 23:50");
        periods.add("2022-04-01, 21:30 - 2022-04-02, 01:20");
        assertThat(Stats.averageDuration(periods)).isEqualTo("0д 3ч 40м");

        periods.clear();
        periods.add("2022-03-12, 16:00 - 2022-03-13, 16:00");
        periods.add("2022-04-01, 21:30 - 2022-04-02, 21:20");
        periods.add("2022-04-01, 18:50 - 2022-04-02, 19:00");
        assertThat(Stats.averageDuration(periods)).isEqualTo("1д 0ч 0м");
    }
}
