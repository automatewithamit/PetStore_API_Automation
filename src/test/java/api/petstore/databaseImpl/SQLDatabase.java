package api.petstore.databaseImpl;

import api.petstore.interfaces.IDatabase;
import api.petstore.utilities.ConfigManager;
import api.petstore.utilities.LoggerUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// O: Open/Closed Principle - Class is open for extension but closed for modification
public class SQLDatabase implements IDatabase {

    private static Connection connection;

    // L: Liskov Substitution Principle - Ensures this class can be used wherever the Database interface is expected
    @Override
    public void connect() {
        try {
            String dbUrl = ConfigManager.getInstance().getProperty("sqlDbUrl");
            String username = ConfigManager.getInstance().getProperty("sqlDbUsername");
            String password = ConfigManager.getInstance().getProperty("sqlDbPassword");

            connection = DriverManager.getConnection(dbUrl, username, password);
            LoggerUtils.info("SQL Database connected successfully.");
        } catch (SQLException e) {
            LoggerUtils.error("Failed to connect to the SQL database.", e);
        }
    }

    @Override
    public void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                LoggerUtils.info("SQL Database disconnected successfully.");
            }
        } catch (SQLException e) {
            LoggerUtils.error("Failed to disconnect from the SQL database.", e);
        }
    }

    @Override
    public ResultSet executeQuery(String query) {
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
