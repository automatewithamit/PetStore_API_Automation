package api.petstore.payloads;

import api.petstore.interfaces.IMongoDatabase;
import api.petstore.utilities.LoggerUtils;
import org.bson.Document;

public class UserService {
    private IMongoDatabase mongodb;

    public UserService(IMongoDatabase mongodb) {
        this.mongodb = mongodb;
    }

    public void createUser(User user) {
        mongodb.insertDocument("users", user.toDocument());
        LoggerUtils.info("User created successfully.");
    }

    public User getUserById(String id) {
        Document document = mongodb.findDocumentById("users", id);
        if (document != null) {
            return User.fromDocument(document);
        }
        return null;
    }

    public void updateUser(String id, User updatedUser) {
        mongodb.updateDocument("users", id, updatedUser.toDocument());
        LoggerUtils.info("User updated successfully.");
    }

    public void deleteUserById(String id) {
        mongodb.deleteDocument("users", id);
        LoggerUtils.info("User deleted successfully.");
    }
}
