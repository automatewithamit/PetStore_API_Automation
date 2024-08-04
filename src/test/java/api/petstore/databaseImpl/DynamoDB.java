package api.petstore.databaseImpl;

import api.petstore.interfaces.IDatabase;

import java.sql.ResultSet;

public class DynamoDB implements IDatabase {
    @Override
    public void connect() {

    }

    @Override
    public void disconnect() {

    }

    @Override
    public ResultSet executeQuery(String query) {
        return null;
    }
}
