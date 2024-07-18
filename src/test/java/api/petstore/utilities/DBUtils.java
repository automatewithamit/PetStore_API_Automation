package api.petstore.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {

    private static Connection connection;

    public static void connect() {
        try {
            String dbUrl = ConfigManager.getInstance().getProperty("dbUrl");
            String username = ConfigManager.getInstance().getProperty("dbUsername");
            String password = ConfigManager.getInstance().getProperty("dbPassword");

            connection = DriverManager.getConnection(dbUrl, username, password);
            LoggerUtils.info("Database connected successfully.");
        } catch (SQLException e) {
            LoggerUtils.error("Failed to connect to the database.", e);
        }
    }

    public static void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                LoggerUtils.info("Database disconnected successfully.");
            }
        } catch (SQLException e) {
            LoggerUtils.error("Failed to disconnect from the database.", e);
        }
    }

    public static ResultSet executeQuery(String query) {
        ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            LoggerUtils.error("Failed to execute query: " + query, e);
        }
        return resultSet;
    }
}
