package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;
import java.util.ArrayList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AnimalCrossingTest {

    static Arguments[] rectangles() {
        return new Arguments[]{
            Arguments.of(),
            Arguments.of(),
            Arguments.of(),
            Arguments.of(),
            Arguments.of()
        };
    }

    @Test
    @DisplayName("Sort by height")
    void AnimalCrossing() {

    }
}
