package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    void convertTime() {
        assertThat(Task1.convertTime("01:00")).isEqualTo(60);
        assertThat(Task1.convertTime("00:47")).isEqualTo(47);
        assertThat(Task1.convertTime("13:56")).isEqualTo(836);
        assertThat(Task1.convertTime("999:00")).isEqualTo(59940);
        assertThat(Task1.convertTime("10:60")).isEqualTo(-1);
        assertThat(Task1.convertTime("3:-1")).isEqualTo(-1);
        assertThat(Task1.convertTime("05:050")).isEqualTo(-1);
        assertThat(Task1.convertTime("4m:42")).isEqualTo(-1);
    }
}
