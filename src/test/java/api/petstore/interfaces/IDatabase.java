package api.petstore.interfaces;

import java.sql.ResultSet;

// S: Single Responsibility Principle - Defines common database operations
// D: Dependency Inversion Principle - High-level module depends on an abstraction (interface) rather than a concrete implementation
public interface IDatabase {
    void connect();
    void disconnect();
    ResultSet executeQuery(String query);
}