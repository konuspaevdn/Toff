package edu.hw3.Task6;

import java.util.Comparator;

public class StockComparator implements Comparator<Stock> {
    @Override
    public int compare(Stock o1, Stock o2) {
        return o2.value().compareTo(o1.value());
    }
}
