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
        String connectionString = ConfigManager.getInstance().getProperty("mongo.connectionString");
        mongoClient = MongoClients.create(connectionString);
        String dbName = ConfigManager.getInstance().getProperty("mongo.dbname");
        database = (MongoDatabase) mongoClient.getDatabase(dbName);
    }

    @Override
    public void insertDocument(String collectionName, Document document) {
        database.getCollection(collectionName).insertOne(document);
    }

    @Override
    public Document findDocumentById(String collectionName, String id) {
        return database.getCollection(collectionName).find(new Document("_id", id)).first();
    }

    @Override
    public void updateDocument(String collectionName, String id, Document updatedDocument) {
        database.getCollection(collectionName).updateOne(new Document("_id", id), new Document("$set", updatedDocument));
    }

    @Override
    public void deleteDocument(String collectionName, String id) {
        database.getCollection(collectionName).deleteOne(new Document("_id", id));
    }

    @Override
    public void disconnect() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}
