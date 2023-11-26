package edu.hw3;

import edu.hw3.Task8.BackwardIterator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.NoSuchElementException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class BackwardItTest {
    @Test
    @DisplayName("Backward iterator test")
    void checkBackwardIterator() {
        BackwardIterator<Integer> bit = new BackwardIterator<>(List.of(1,2,3));
        assertThat(bit.hasNext()).isTrue();
        assertThat(bit.next()).isEqualTo(3);
        assertThat(bit.hasNext()).isTrue();
        assertThat(bit.next()).isEqualTo(2);
        assertThat(bit.hasNext()).isTrue();
        assertThat(bit.next()).isEqualTo(1);
        assertThat(bit.hasNext()).isFalse();
        assertThatThrownBy(bit::next).isInstanceOf(NoSuchElementException.class);
    }
}
