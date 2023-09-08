package com.codecool.marsexploration.mapexplorer.repository.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnectionManager {
    private static final String JDBC_URL = "jdbc:sqlite:src/main/resources/dbs/MarsExploration.db";
    private Connection connection;

    public DBConnectionManager() {
        try {
            connection = DriverManager.getConnection(JDBC_URL);
            createTableIfNotExists();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTableIfNotExists() {
        //there is no boolean value in sqlite, use int 0 or 1
        //BLOB is a catch-all that returns data as it was entered - we need to figure out how to log resources first, before defining this field
        try (Statement statement = connection.createStatement()) {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS logs (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "timestamp TEXT" +
                    "number_of_steps INTEGER," +
                    "resources BLOB" +
                    "successful_outcome INTEGER)";
            statement.executeUpdate(createTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            if(connection != null) {
                connection.close();
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}