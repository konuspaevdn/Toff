package edu.hw5;

import edu.hw5.Task2.Fridays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FridaysTest {
    @Test
    @DisplayName("get all fridays the 13th")
    void getFridaysTest() {
        List<String> list = new ArrayList<>();
        list.add("1925-02-13");
        list.add("1925-03-13");
        list.add("1925-11-13");
        assertThat(Fridays.get(1925)).isEqualTo(list);

        list.clear();
        list.add("2024-09-13");
        list.add("2024-12-13");
        assertThat(Fridays.get(2024)).isEqualTo(list);
    }

    @Test
    @DisplayName("get next friday the 13th")
    void getNextTest() {
        assertThat(Fridays.next("2023-10-01")).isEqualTo("2023-10-13");
        assertThat(Fridays.next("2024-01-01")).isEqualTo("2024-09-13");
    }
}
