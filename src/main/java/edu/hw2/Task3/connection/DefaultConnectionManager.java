package edu.hw2.Task3.connection;

import java.util.Random;
import org.apache.logging.log4j.Logger;

public class DefaultConnectionManager implements ConnectionManager {

    @SuppressWarnings("MagicNumber")
    @Override
    public Connection getConnection(Logger logger) {
        final int FAULTY_PROB_BOUNDARY = 4;  // to simulate probability of faulty connection
        Random connection = new Random();
        connection.setSeed(42);
        if (connection.nextInt(50) <= FAULTY_PROB_BOUNDARY) {
            return new FaultyConnection(logger);
        }
        return new StableConnection(logger);
    }
}
