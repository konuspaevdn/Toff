package edu.hw2.Task3.connection;

import org.apache.logging.log4j.Logger;

public class FaultyConnectionManager implements ConnectionManager {
    @Override
    public Connection getConnection(Logger logger) {
        return new FaultyConnection(logger);
    }
}
