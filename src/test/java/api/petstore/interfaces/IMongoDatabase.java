package api.petstore.interfaces;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public interface IMongoDatabase extends IBaseDatabase{

    MongoClient getClient();
    MongoDatabase getDatabase(String dbName);
}
