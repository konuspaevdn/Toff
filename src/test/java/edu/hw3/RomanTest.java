package edu.hw3;

import edu.hw3.Task4.RomanNumbers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RomanTest {

    @Test
    @DisplayName("Convert some numbers")
    void checkConvertToRoman() {
        int a = 2;
        assertThat(RomanNumbers.convertToRoman(a)).isEqualTo("II");
        int b = 8;
        assertThat(RomanNumbers.convertToRoman(b)).isEqualTo("VIII");
        int c = 16;
        assertThat(RomanNumbers.convertToRoman(c)).isEqualTo("XVI");
        int d = 30;
        assertThat(RomanNumbers.convertToRoman(d)).isEqualTo("XXX");
        int e = 54;
        assertThat(RomanNumbers.convertToRoman(e)).isEqualTo("LIV");
        int f = 299;
        assertThat(RomanNumbers.convertToRoman(f)).isEqualTo("CCXCIX");
        int g = 303;
        assertThat(RomanNumbers.convertToRoman(g)).isEqualTo("CCCIII");
        int h = 2600;
        assertThat(RomanNumbers.convertToRoman(h)).isEqualTo("MMDC");
        int i = 10347;
        assertThat(RomanNumbers.convertToRoman(i)).isEqualTo("MMMMMMMMMMCCCXLVII");

    }
}
