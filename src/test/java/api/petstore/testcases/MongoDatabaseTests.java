package api.petstore.testcases;

import api.petstore.databaseImpl.MongoDBDatabase;
import api.petstore.interfaces.IMongoDatabase;
import org.bson.Document;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MongoDatabaseTests extends BaseTest_DB<IMongoDatabase>{
    @Override
    protected IMongoDatabase createDatabase() {
        return new MongoDBDatabase();
    }

    @Override
    protected String getDatabaseType() {
        return "mongodb";
    }

    @Test
    public void testFindUserByUsername() {
        String username = "john.doe";
        MongoDBDatabase mongoDBDatabase = (MongoDBDatabase) database; // Cast to MongoDBDatabase
        Document user = mongoDBDatabase.findUserByUsername(username);
        Assert.assertNotNull(user, "User should exist in the database");
        Assert.assertEquals(user.getString("username"), username);
    }

    }


