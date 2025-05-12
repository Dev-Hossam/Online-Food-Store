package test1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnections {
    private static final String URL = "jdbc:mysql://localhost:3306/Talabat?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "123dodomg";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Ensure driver is loaded
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // Optional: better logging
        }

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
