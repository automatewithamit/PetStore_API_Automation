package api.petstore.testcases;
import api.petstore.payloads.User;
import api.petstore.utilities.JsonReader;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class UserFromJSONTests {
    String projectPath = System.getProperty("user.dir");
    @Test
    public void testUsersFromJson() {

        // Get the list of users from the JSON file
        List<User> users = JsonReader.getUsersFromJson(projectPath + "/TestData/Users.json");

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

        // Assert that all important fields are not null
        Assert.assertNotNull(user.getId(), "UserId should not be null");
        Assert.assertNotNull(user.getUsername(), "Username should not be null");
        Assert.assertNotNull(user.getEmail(), "Email should not be null");
    }
}
