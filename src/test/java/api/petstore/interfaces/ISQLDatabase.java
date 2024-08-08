package api.petstore.interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ISQLDatabase extends IBaseDatabase {
    void executeUpdate(String query);
    ResultSet executeQuery(String query);
}