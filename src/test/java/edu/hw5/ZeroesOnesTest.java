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

        str = "1";
        assertThat(ZeroesOnes.checkOddLength(str)).isTrue();
        str = "10";
        assertThat(ZeroesOnes.checkOddLength(str)).isFalse();
        str = "001";
        assertThat(ZeroesOnes.checkOddLength(str)).isTrue();
        str = "0011";
        assertThat(ZeroesOnes.checkOddLength(str)).isFalse();

        str = "01";
        assertThat(ZeroesOnes.checkFirstSAndLength(str)).isFalse();
        str = "101";
        assertThat(ZeroesOnes.checkFirstSAndLength(str)).isFalse();
        str = "01001";
        assertThat(ZeroesOnes.checkFirstSAndLength(str)).isTrue();
        str = "1111";
        assertThat(ZeroesOnes.checkFirstSAndLength(str)).isTrue();

        str = "";
        assertThat(ZeroesOnes.checkZeroesNumMultipleOf3(str)).isTrue();
        str = "1111";
        assertThat((ZeroesOnes.checkZeroesNumMultipleOf3(str))).isTrue();
        str = "1011";
        assertThat(ZeroesOnes.checkZeroesNumMultipleOf3(str)).isFalse();
        str = "01101";
        assertThat(ZeroesOnes.checkZeroesNumMultipleOf3(str)).isFalse();
        str = "11110";
        assertThat(ZeroesOnes.checkZeroesNumMultipleOf3(str)).isFalse();
        str = "101001";
        assertThat(ZeroesOnes.checkZeroesNumMultipleOf3(str)).isTrue();
        str = "00100";
        assertThat(ZeroesOnes.checkZeroesNumMultipleOf3(str)).isFalse();

        str = "";
        assertThat(ZeroesOnes.checkForForbiddenStrings(str)).isTrue();
        str = "0";
        assertThat(ZeroesOnes.checkForForbiddenStrings(str)).isTrue();
        str = "11";
        assertThat(ZeroesOnes.checkForForbiddenStrings(str)).isFalse();
        str = "110";
        assertThat(ZeroesOnes.checkForForbiddenStrings(str)).isTrue();
        str = "111";
        assertThat(ZeroesOnes.checkForForbiddenStrings(str)).isFalse();
        str = "1110";
        assertThat(ZeroesOnes.checkForForbiddenStrings(str)).isTrue();

        str = "";
        assertThat(ZeroesOnes.checkOddSIsOne(str)).isTrue();
        str = "0";
        assertThat(ZeroesOnes.checkOddSIsOne(str)).isFalse();
        str = "11";
        assertThat(ZeroesOnes.checkOddSIsOne(str)).isTrue();
        str = "101";
        assertThat(ZeroesOnes.checkOddSIsOne(str)).isTrue();
        str = "11100";
        assertThat(ZeroesOnes.checkOddSIsOne(str)).isFalse();

        str = "011";
        assertThat(ZeroesOnes.checkTwoZeroesOneOne(str)).isFalse();
        str = "1";
        assertThat(ZeroesOnes.checkTwoZeroesOneOne(str)).isFalse();
        str = "1001";
        assertThat(ZeroesOnes.checkTwoZeroesOneOne(str)).isFalse();
        str = "010";
        assertThat(ZeroesOnes.checkTwoZeroesOneOne(str)).isTrue();
        str = "00010";
        assertThat(ZeroesOnes.checkTwoZeroesOneOne(str)).isTrue();

        str = "11";
        assertThat(ZeroesOnes.checkForConsequentialOnes(str)).isFalse();
        str = "001110";
        assertThat(ZeroesOnes.checkForConsequentialOnes(str)).isFalse();
        str = "010001";
        assertThat(ZeroesOnes.checkForConsequentialOnes(str)).isTrue();
    }
}
