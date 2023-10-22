package edu.hw2;

import edu.hw2.Task3.PopularCommandExecutor;
import edu.hw2.Task3.connection.ConnectionException;
import edu.hw2.Task3.connection.ConnectionManager;
import edu.hw2.Task3.connection.FaultyConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ConsoleTest {

    @Test
    @DisplayName("Testing local env")
    void consoleConnection() {
        final Logger LOGGER = LogManager.getLogger();
        PopularCommandExecutor pce1 = new PopularCommandExecutor(LOGGER);
        assertDoesNotThrow(pce1::updatePackages);

        String command = "all your base are belong to us";
        assertDoesNotThrow(() -> pce1.tryExecute(command));


        ConnectionManager fcm = new FaultyConnectionManager();
        PopularCommandExecutor pce2 = new PopularCommandExecutor(fcm, 5, LOGGER);
        assertThrows(ConnectionException.class, pce2::updatePackages);
    }

}
