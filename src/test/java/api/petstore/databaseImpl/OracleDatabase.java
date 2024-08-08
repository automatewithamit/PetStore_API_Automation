package api.petstore.databaseImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import api.petstore.interfaces.IBaseDatabase;
import api.petstore.interfaces.IDatabase;
import api.petstore.interfaces.IOracleDatabase;
import api.petstore.utilities.ConfigManager;
import api.petstore.utilities.LoggerUtils;
import oracle.jdbc.OracleDriver;

// O: Open/Closed Principle - Class is open for extension but closed for modification
public class OracleDatabase implements IOracleDatabase {


    private static Connection connection;

    // L: Liskov Substitution Principle - Ensures this class can be used wherever the Database interface is expected
    @Override
    public void connect() {
        try {
            String dbUrl = ConfigManager.getInstance().getProperty("oracleDbUrl");
            String username = ConfigManager.getInstance().getProperty("oracleDbUsername");
            String password = ConfigManager.getInstance().getProperty("oracleDbPassword");

            DriverManager.registerDriver(new OracleDriver());
            connection = DriverManager.getConnection(dbUrl, username, password);
            LoggerUtils.info("Oracle Database connected successfully.");
        } catch (SQLException e) {
            LoggerUtils.error("Failed to connect to the Oracle database.", e);
        }
    }

    @Override
    public void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                LoggerUtils.info("Oracle Database disconnected successfully.");
            }
        } catch (SQLException e) {
            LoggerUtils.error("Failed to disconnect from the Oracle database.", e);
        }
    }

    @Override
    public void executeUpdate(String query) {
        try {
            Statement statement = connection.createStatement();

            statement.executeUpdate(query);
        } catch (SQLException e) {
            LoggerUtils.error("Failed to execute query: " + query, e);
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
