package edu.hw2.Task3.connection;

public class FaultyConnectionManager implements ConnectionManager {

    @Override
    public Connection getConnection() {
        return null;
    }
}
