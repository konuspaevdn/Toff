package edu.hw2;

import edu.hw2.Task3.PopularCommandExecutor;
import edu.hw2.Task3.connection.ConnectionException;
import edu.hw2.Task3.connection.ConnectionManager;
import edu.hw2.Task3.connection.FaultyConnectionManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ConsoleTest {

    @Test
    @DisplayName("Testing local env")
    void consoleConnection() {
        PopularCommandExecutor pce1 = new PopularCommandExecutor();
        assertDoesNotThrow(pce1::updatePackages);

        String command = "all your base are belong to us";
        assertDoesNotThrow(() -> pce1.tryExecute(command));


        ConnectionManager fcm = new FaultyConnectionManager();
        PopularCommandExecutor pce2 = new PopularCommandExecutor(fcm, 5);
        assertThrows(ConnectionException.class, pce2::updatePackages);
    }

}
