package api.petstore.testcases;

import api.petstore.databaseImpl.MongoDBDatabase;
import api.petstore.interfaces.IMongoDatabase;
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
    public void mongoDBTest(){


//        UserService userService = new UserService(database);

//        // Create a new user
//        User newUser = new User();//new User("1", "johndoe", "John", "Doe", "john.doe@example.com", "password123", "1234567890",0);
//        userService.createUser(newUser);
//
//        // Retrieve the user by ID
//        User retrievedUser = userService.getUserById("1");
//        if (retrievedUser != null) {
//            System.out.println("User found: " + retrievedUser.getUsername());
//        } else {
//            System.out.println("User not found.");
//        }
//
//        // Update the user
//        newUser.setEmail("new.email@example.com");
//        userService.updateUser("1", newUser);
//
//        // Delete the user
//        userService.deleteUserById("1");
//
//        database.disconnect();
    }
    }


