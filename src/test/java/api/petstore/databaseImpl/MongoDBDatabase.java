package api.petstore.databaseImpl;

import api.petstore.interfaces.IMongoDatabase;
import api.petstore.utilities.ConfigManager;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDBDatabase implements IMongoDatabase {

    private MongoClient mongoClient;
    private MongoDatabase database;

    @Override
    public void connect() {
        // Extracting the connection string and database name from the configuration file
        String connectionString = ConfigManager.getInstance().getProperty("mongoDbUrl");
        String dbName = ConfigManager.getInstance().getProperty("mongoDbName");

        // Creating the MongoDB client and connecting to the specified database
        this.mongoClient = MongoClients.create(connectionString);
        this.database = mongoClient.getDatabase(dbName);
    }


    @Override
    public void disconnect() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }

    @Override
    public MongoClient getClient() {
        return this.mongoClient;
    }

    @Override
    public MongoDatabase getDatabase(String dbName) {
        return this.database;
    }

    public Document findUserByUsername(String username) {
        return database.getCollection("users").find(new Document("username", username)).first();
    }


}
