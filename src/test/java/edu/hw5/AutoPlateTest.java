package edu.hw5;

import edu.hw5.Task5.AutoPlate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AutoPlateTest {
    @Test
    @DisplayName("Check auto plate checker")
    void autoPlateCheckTest() {
        assertThat(AutoPlate.check("А123ВЕ777")).isTrue();
        assertThat(AutoPlate.check("О777ОО177")).isTrue();
        assertThat(AutoPlate.check("О777ОО05")).isTrue();
        assertThat(AutoPlate.check("123АВЕ777")).isFalse();
        assertThat(AutoPlate.check("А123ВГ77")).isFalse();
        assertThat(AutoPlate.check("А123ВЕ7777")).isFalse();

    }
}
