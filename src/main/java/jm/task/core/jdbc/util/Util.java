package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    private final String DB_URL = "jdbc:mysql://localhost/kata";
    private final String DB_USERNAME = "kata";
    private final String DB_PASSWORD = "KataExJM123";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }
}
