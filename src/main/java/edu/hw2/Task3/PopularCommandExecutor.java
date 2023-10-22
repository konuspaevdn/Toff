package edu.hw2.Task3;

import edu.hw2.Task3.connection.Connection;
import edu.hw2.Task3.connection.ConnectionException;
import edu.hw2.Task3.connection.ConnectionManager;
import edu.hw2.Task3.connection.DefaultConnectionManager;
import org.apache.logging.log4j.Logger;

public final class PopularCommandExecutor {
    private final ConnectionManager manager;
    private final int maxAttempts;
    private final Logger logger;
    private Exception cause;

    public PopularCommandExecutor(Logger logger) {
        manager = new DefaultConnectionManager();
        maxAttempts = 2;
        this.logger = logger;
    }

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts, Logger logger) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
        this.logger = logger;
    }

    public void updatePackages() {
        tryExecute("apt update && apt upgrade -y");
    }

    public void tryExecute(String command) throws ConnectionException {
        for (int i = 0; i < maxAttempts; ++i) {
            try (Connection connection = manager.getConnection(logger)) {
                connection.execute(command);
            } catch (Exception e) {
                cause = e;
                if (i == maxAttempts - 1) {
                    throw new ConnectionException();
                }
                continue;
            }
            break;
        }
    }
}
