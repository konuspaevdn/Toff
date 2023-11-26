package edu.hw2.Task3;

import edu.hw2.Task3.connection.Connection;
import edu.hw2.Task3.connection.ConnectionException;
import edu.hw2.Task3.connection.ConnectionManager;
import edu.hw2.Task3.connection.DefaultConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class PopularCommandExecutor {
    private final ConnectionManager manager;
    private final int maxAttempts;
    private Exception cause;

    public PopularCommandExecutor() {
        this.manager = new DefaultConnectionManager();
        this.maxAttempts = 2;
    }

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages() {
        tryExecute("apt update && apt upgrade -y");
    }

    public void tryExecute(String command) throws ConnectionException {
        for (int i = 0; i < maxAttempts; ++i) {
            try (Connection connection = manager.getConnection()) {
                connection.execute(command);
            } catch (Exception e) {
                cause = e;
                if (i == maxAttempts - 1) {
                    Logger logger = LogManager.getLogger();
                    logger.error("Couldn't execute command, tried " + maxAttempts + " times");
                    throw new ConnectionException();
                }
                continue;
            }
            break;
        }
    }
}
