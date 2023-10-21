package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {

    @Test
    void countDigits() {
        assertThat(Task2.countDigits(0)).isEqualTo(1);
        assertThat(Task2.countDigits(123)).isEqualTo(3);
        assertThat(Task2.countDigits(100000)).isEqualTo(6);
        assertThat(Task2.countDigits(5)).isEqualTo(1);
    }
}
