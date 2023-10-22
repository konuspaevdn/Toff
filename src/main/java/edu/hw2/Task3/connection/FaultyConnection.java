package edu.hw2.Task3.connection;

import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection {
    private final Logger logger;

    public FaultyConnection(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void execute(String command) throws ConnectionException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command(command.split(" "));
        throw new ConnectionException();  // for testing purposes
    }

    @Override
    public void close() throws Exception {
        logger.info("Connection closed");
    }
}
