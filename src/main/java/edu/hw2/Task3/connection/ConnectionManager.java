package edu.hw2.Task3.connection;

import org.apache.logging.log4j.Logger;

public interface ConnectionManager {
    Connection getConnection(Logger logger);
}
