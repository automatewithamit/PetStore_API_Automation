package api.petstore.testcases;
import api.petstore.endpoints.UserEndPoints;
import api.petstore.payloads.User;
import api.petstore.reporting.ExtentReportManager;
import api.petstore.utilities.JsonReader;
import com.aventstack.extentreports.Status;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

import static api.petstore.testcases.BaseTest.Logger;

public class UserFromJSONTests {
    String projectPath = System.getProperty("user.dir");
    User user;
    List<User> users;
    @Test
    public void testUsersFromJson() {

        // Get the list of users from the JSON file
        users = JsonReader.getUsersFromJson(projectPath + "/TestData/Users.json");

        // Assert that users are successfully deserialized
        Assert.assertNotNull(users, "Users list should not be null");
        Assert.assertFalse(users.isEmpty(), "Users list should not be empty");

        // You can loop through the users and verify their data
        for (User user : users) {
            System.out.println("User: " + user.getUsername());
            Assert.assertNotNull(user.getUsername(), "Username should not be null");
            Assert.assertNotNull(user.getEmail(), "Email should not be null");
        }
    }
    @Test(dataProvider = "userDataFromJson", dataProviderClass = JsonReader.class)
    public void testUsersFromJsonDataProvider(User user) {
        // Verify user object from JSON data
        System.out.println("Testing user: " + user.getUsername());
        Response response = UserEndPoints.createUser(user);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
        // Assert that all important fields are not null
        Assert.assertNotNull(user.getId(), "UserId should not be null");
        Assert.assertNotNull(user.getUsername(), "Username should not be null");
        Assert.assertNotNull(user.getEmail(), "Email should not be null");
    }
    @Test(priority = 2,dataProvider = "userDataFromJson", dataProviderClass = JsonReader.class)
    public void getUserTest(User user){
        String userNameToFind = user.getUsername();
        //ExtentReportManager.getTest().log(Status.INFO, "Starting Test to Fetch User with username : '"+ userNameToFind + "'");
        //Logger.info("Starting Test to Fetch User with username : '"+ userNameToFind + "'");
        System.out.println("Find User : "+ userNameToFind);
        Response response = UserEndPoints.getUser(userNameToFind);
        response.then().log().body();
        Assert.assertEquals(response.getStatusCode(),200);
        //ExtentReportManager.getTest().log(Status.INFO, "User with username : '"+ userNameToFind + "' FOUND");
        //Logger.info("User with username : '"+ user.getUsername() + "' FOUND");
    }

}
