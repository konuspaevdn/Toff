package edu.hw3;
import edu.hw3.Task6.Stock;
import edu.hw3.Task6.StockMarketQueue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StockTest {
    @Test
    @DisplayName("I'm tired writing names for tests, so let's say this one is baba booey")
    void checkStockQueue() {
        Stock one = new Stock(18.);
        Stock two = new Stock(24.5);
        Stock three = new Stock(22.8);

        StockMarketQueue market = new StockMarketQueue();
        market.add(one);
        assertThat(market.mostValuableStock()).isEqualTo(one);
        market.add(two);
        assertThat(market.mostValuableStock()).isEqualTo(two);
        market.add(three);
        assertThat(market.mostValuableStock()).isEqualTo(two);
        market.remove(two);
        assertThat(market.mostValuableStock()).isEqualTo(three);
        market.remove(three);
        assertThat(market.mostValuableStock()).isEqualTo(one);
        market.add(two);
        market.add(two);
        assertThat(market.mostValuableStock()).isEqualTo(two);
        market.remove(two);
        assertThat(market.mostValuableStock()).isEqualTo(two);

    }
}
