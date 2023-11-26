package edu.hw7;

import edu.hw7.Task4.MonteCarlo;
import edu.hw7.Task4.Report;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MonteCarloTest {
    @Test
    @DisplayName("Comparing sequential and parallel Monte Carlo execution")
    void SpeedTest() {
        Report seq1 = MonteCarlo.runOneThread(10_000_000);
        System.out.println("(1 thread) Monte Carlo for 10_000_000 samples calculated pi = " + seq1.calc());
        System.out.println("Error: " + Math.abs(seq1.calc() - Math.PI));
        System.out.println("-----------------------------------------------");
        Report seq2 = MonteCarlo.runOneThread(100_000_000);
        System.out.println("(1 thread) Monte Carlo for 100_000_000 samples calculated pi = " + seq2.calc());
        System.out.println("Error: " + Math.abs(seq2.calc() - Math.PI));
        System.out.println("-----------------------------------------------");
        Report seq3 = MonteCarlo.runOneThread(1_000_000_000);
        System.out.println("(1 thread) Monte Carlo for 1_000_000_000 samples calculated pi = " + seq3.calc());
        System.out.println("Error: " + Math.abs(seq3.calc() - Math.PI));
        System.out.println("#######################################################################");
        Report par1a = MonteCarlo.runMultiThread(10_000_000, 2);
        System.out.println("(2 threads) Monte Carlo for 10_000_000 samples calculated pi = " + par1a.calc());
        System.out.println("Acceleration is " + (seq1.time() + 0.) / par1a.time());
        System.out.println("-----------------------------------------------");
        Report par1b = MonteCarlo.runMultiThread(10_000_000, 4);
        System.out.println("(4 threads) Monte Carlo for 10_000_000 samples calculated pi = " + par1b.calc());
        System.out.println("Acceleration is " + (seq1.time() + 0.) / par1b.time());
        System.out.println("-----------------------------------------------");
        int cores = Runtime.getRuntime().availableProcessors();
        Report par1c = MonteCarlo.runMultiThread(10_000_000, cores);
        System.out.println("(" + cores + " threads) Monte Carlo for 10_000_000 samples calculated pi = " + par1c.calc());
        System.out.println("Error: " + Math.abs(par1c.calc() - Math.PI));
        System.out.println("Acceleration is " + (seq1.time() + 0.) / par1c.time());
        System.out.println("-----------------------------------------------");
        Report par2 = MonteCarlo.runMultiThread(100_000_000, cores);
        System.out.println("(" + cores + " threads) Monte Carlo for 100_000_000 samples calculated pi = " + par2.calc());
        System.out.println("Acceleration is " + (seq2.time() + 0.) / par2.time());
        System.out.println("-----------------------------------------------");
        Report par3 = MonteCarlo.runMultiThread(1_000_000_000, cores);
        System.out.println("(" + cores + " threads) Monte Carlo for 1_000_000_000 samples calculated pi = " + par3.calc());
        System.out.println("Acceleration is " + (seq3.time() + 0.) / par3.time());
    }
}
