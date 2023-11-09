package edu.hw2;

import edu.hw2.Task2.Rectangle;
import edu.hw2.Task2.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RectTest {
    static Arguments[] rectangles() {
        return new Arguments[]{
            Arguments.of(new Rectangle()),
            Arguments.of(new Square()),
            Arguments.of(new Rectangle(123)),
            Arguments.of(new Square(47)),
            Arguments.of(new Rectangle(5, 13))
        };
    }

    @ParameterizedTest
    @DisplayName("Area calculation")
    @MethodSource("rectangles")
    void rectangleArea(Rectangle rect) {
        rect = rect.setWidth(20);
        rect = rect.setHeight(10);
        assertThat(rect.area()).isEqualTo(200.0);

        rect = rect.setWidth(10);
        assertThat(rect.area()).isEqualTo(100);

        rect = rect.setWidthHeight(30, 10);
        assertThat(rect.area()).isEqualTo(300);
    }
}
