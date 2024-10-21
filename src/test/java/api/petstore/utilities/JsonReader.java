package api.petstore.utilities;
import api.petstore.payloads.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonReader {

    // Method to get users from the JSON file
    public static List<User> getUsersFromJson(String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        List<User> users = new ArrayList<>();

        try {
            File file = new File(filePath);

            // Read the JSON tree
            JsonNode jsonNode = mapper.readTree(file);

            if (jsonNode.isArray()) {
                // If it's an array, map it directly
                users = mapper.readValue(file, new TypeReference<List<User>>() {});
            } else if (jsonNode.isObject()) {
                // If it's a single object, wrap it in a list
                User user = mapper.readValue(file, User.class);
                users.add(user);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    // DataProvider for TestNG
    @DataProvider(name = "userDataFromJson")
    public static Object[][] userDataProvider() {
        List<User> users = getUsersFromJson(System.getProperty("user.dir") + "/TestData/Users.json");

        // Convert the List<User> into a 2D array
        Object[][] userData = new Object[users.size()][1];
        for (int i = 0; i < users.size(); i++) {
            userData[i][0] = users.get(i);
        }
        return userData;
    }
}
