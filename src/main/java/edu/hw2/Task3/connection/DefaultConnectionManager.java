package edu.hw2.Task3.connection;

import java.util.Random;

public class DefaultConnectionManager implements ConnectionManager {

    @SuppressWarnings("MagicNumber")
    @Override
    public Connection getConnection() {
        final int SEED = 42;
        final int PROB_RANGE = 50;
        final int FAULTY_PROB_BOUNDARY = 4;  // to simulate probability of faulty connection
        Random connection = new Random();
        connection.setSeed(SEED);
        if (connection.nextInt(PROB_RANGE) <= FAULTY_PROB_BOUNDARY) {
            return new FaultyConnection();
        }
        return new StableConnection();
    }
}
