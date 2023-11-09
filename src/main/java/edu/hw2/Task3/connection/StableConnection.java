package edu.hw2.Task3.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StableConnection implements Connection {

    public StableConnection() {
    }

    @Override
    public void execute(String command) {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command(command.split("\\s+"));
    }

    @Override
    public void close() {
        Logger logger = LogManager.getLogger();
        logger.info("Connection closed");
    }
}
