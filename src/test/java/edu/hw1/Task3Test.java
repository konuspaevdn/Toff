package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {
    @Test
    void isNestable() {
        int[] a1 = {1, 2, 3, 4};
        int[] a2 = {0, 6};
        int[] b1 = {3, 1};
        int[] b2 = {4, 0};
        int[] c1 = {9, 9, 8};
        int[] c2 = {8, 9};
        int[] d1 = {1, 2, 3, 4};
        int[] d2 = {2, 3};
        assertThat(Task3.isNestable(a1, a2)).isTrue();
        assertThat(Task3.isNestable(b1, b2)).isTrue();
        assertThat(Task3.isNestable(a1, a2)).isTrue();
        assertThat(Task3.isNestable(a1, b2)).isFalse();
        assertThat(Task3.isNestable(c1, c2)).isFalse();
        assertThat(Task3.isNestable(d1, d2)).isFalse();
        assertThat(Task3.isNestable(a2, c2)).isFalse();
        assertThat(Task3.isNestable(c2, a2)).isFalse();
    }
}
