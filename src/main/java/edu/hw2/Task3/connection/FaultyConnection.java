package edu.hw2.Task3.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection {

    public FaultyConnection() {
    }

    @Override
    public void execute(String command) throws ConnectionException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command(command.split("\\s+"));
        throw new ConnectionException();  // for testing purposes
    }

    @Override
    public void close() {
        Logger logger = LogManager.getLogger();
        logger.info("Connection closed");
    }
}
