package edu.hw7;

import edu.hw7.Task2.Factorial;
import java.math.BigInteger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FactorialTest {
    @Test
    @DisplayName("Calculate factorial parallelly")
    void calculateFactorial() {
        assertThat(Factorial.calculateParallelly(6).intValue()).isEqualTo(720);
        assertThat(Factorial.calculateParallelly(10).intValue()).isEqualTo(3628800);
        assertThat(Factorial.calculateParallelly(15)).isEqualTo(new BigInteger("1307674368000"));
        assertThat(Factorial.calculateParallelly(35)).isEqualTo(new BigInteger("10333147966386144929666651337523200000000"));

        long startTime = System.nanoTime();
        Factorial.calculate(150);
        long endTime = System.nanoTime();
        long sequential = endTime - startTime;
        startTime = System.nanoTime();
        Factorial.calculateParallelly(150);
        endTime = System.nanoTime();
        long parallel = endTime - startTime;
        assertThat(parallel).isLessThan(sequential);
    }
}
