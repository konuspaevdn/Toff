package edu.hw5;

import edu.hw5.Task3.DateParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DateParserTest {
    @Test
    @DisplayName("Parse the date")
    void dateParserTest() {
        assertThat(DateParser.parse("2020-11-3").get()).isEqualTo(LocalDate.of(2020, 11, 3));
        assertThat(DateParser.parse("2020-11-13").get()).isEqualTo(LocalDate.of(2020, 11, 13));
        assertThat(DateParser.parse("10/13/2023").get()).isEqualTo(LocalDate.of(2023, 10, 13));
        assertThat(DateParser.parse("10/3/2023").get()).isEqualTo(LocalDate.of(2023, 10, 3));
        assertThat(DateParser.parse("10/13/23").get()).isEqualTo(LocalDate.of(2023, 10, 13));
        assertThat(DateParser.parse("today").get()).isEqualTo(LocalDate.of(2023, 11, 13));
        assertThat(DateParser.parse("tomorrow").get()).isEqualTo(LocalDate.of(2023, 11, 14));
        assertThat(DateParser.parse("yesterday").get()).isEqualTo(LocalDate.of(2023, 11, 12));
        assertThat(DateParser.parse("42 day ago").get()).isEqualTo(LocalDate.of(2023, 10, 2));
        assertThat(DateParser.parse("02.03.2015").isEmpty()).isTrue();
    }
}
