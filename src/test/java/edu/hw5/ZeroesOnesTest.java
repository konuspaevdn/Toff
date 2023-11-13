package edu.hw5;

import edu.hw5.Task7_8.ZeroesOnes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ZeroesOnesTest {
    @Test
    @DisplayName("Beep-beep")
    void zeroesOnesTest() {
        String str = "00";
        assertThat(ZeroesOnes.checkLengthAndThirdS(str)).isFalse();
        str = "001";
        assertThat(ZeroesOnes.checkLengthAndThirdS(str)).isFalse();
        str = "0001";
        assertThat(ZeroesOnes.checkLengthAndThirdS(str)).isTrue();

        str = "10011";
        assertThat(ZeroesOnes.checkSameFirstAndLastS(str)).isTrue();
        str = "1000";
        assertThat(ZeroesOnes.checkSameFirstAndLastS(str)).isFalse();

        str = "";
        assertThat(ZeroesOnes.checkLengthBetween1and3(str)).isFalse();
        str = "0";
        assertThat(ZeroesOnes.checkLengthBetween1and3(str)).isTrue();
        str = "01";
        assertThat(ZeroesOnes.checkLengthBetween1and3(str)).isTrue();
        str = "010";
        assertThat(ZeroesOnes.checkLengthBetween1and3(str)).isTrue();
        str = "0101";
        assertThat(ZeroesOnes.checkLengthBetween1and3(str)).isFalse();
    }
}
