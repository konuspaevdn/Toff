package edu.hw2.Task3.connection;

import org.apache.logging.log4j.Logger;

public class StableConnection implements Connection {
    private final Logger logger;

    public StableConnection(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void execute(String command) {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command(command.split(" "));
    }

    @Override
    public void close() throws Exception {
        logger.info("Connection closed");
    }
}
