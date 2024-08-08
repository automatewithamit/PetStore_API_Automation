package api.petstore.testcases;

import api.petstore.databaseImpl.SQLDatabase;
import api.petstore.endpoints.UserEndPoints;
import api.petstore.interfaces.ISQLDatabase;
import api.petstore.payloads.User;
import api.petstore.utilities.LoggerUtils;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.sql.ResultSet;
import java.sql.SQLException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SQLDatabaseTests extends BaseTest_DB<ISQLDatabase> {


    @Override
    protected ISQLDatabase createDatabase() {
        return new SQLDatabase();
    }

    @Override
    protected String getDatabaseType() {
        return "sql";
    }

    Faker faker;
    User user;
    @BeforeClass
    public void generateUserData(){
        faker = new Faker();
        user = new User();
        user.setId(faker.idNumber().hashCode());
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setUsername(faker.name().username());
        user.setPhone(faker.phoneNumber().cellPhone());
        user.setEmail(faker.internet().emailAddress());
        user.setUserStatus(0);

    }
    @Test
    public void testVerifyUserNameFromDatabase() {
        String userId = "23423";
        String expectedUsername = "donald.trump";

        String query = "SELECT Username FROM users WHERE UserId = " + userId;
        ResultSet resultSet = database.executeQuery(query);

        try {
            if (resultSet.next()) {
                String actualUsername = resultSet.getString("Username");
                assertThat("Username should match", actualUsername, equalTo(expectedUsername));
                LoggerUtils.info("Username verified successfully for userId: " + userId);
            } else {
                LoggerUtils.error("No user found in database with UserId: " + userId, new SQLException());
            }
        } catch (SQLException e) {
            LoggerUtils.error("Error while validating database results.", e);
        }
    }

    @Test
    public void api_DBTest() throws SQLException {
        //Create User using api
        //ExtentReportManager.getTest().log(Status.INFO, "Starting Test to Create User with username : '"+ user.getUsername() + "'");
        Logger.info("Starting Test to Create User with username : '"+ user.getUsername() + "'");
        Response response = UserEndPoints.createUser(user);
        Assert.assertEquals(response.getStatusCode(),200);
        //ExtentReportManager.getTest().log(Status.INFO, "User with username : '"+ user.getUsername() + "' CREATED  using API");
        Logger.info("User with username : '"+ user.getUsername() + "' CREATED Successfully using API ");
        //create user in SQL Database
        //ExtentReportManager.getTest().log(Status.INFO, "Creating user in DB with username : '"+ user.getUsername() + "'");
        Logger.info("Creating user in DB with username : '"+ user.getUsername() + "'");

        String query = "INSERT INTO users (UserId, Username, FirstName, LastName, Email, Password, Phone) VALUES (" +
                user.getId() + ", '" + user.getUsername() + "', '" + user.getFirstName() + "', '" +
                user.getLastName() + "', '" + user.getEmail() + "', '" + user.getPassword() + "', '" +
                user.getPhone() + "')";
        //ExtentReportManager.getTest().log(Status.INFO, "Query used : "+query);
        Logger.info("Query used : {}", query);
        database.executeUpdate(query);
        //ExtentReportManager.getTest().log(Status.INFO, "User with username : '"+ user.getUsername() + "' CREATED  using DB");
        Logger.info("User with username : '{}' CREATED Successfully using DB ", user.getUsername());


        //Get user using id from api endpoint
        //ExtentReportManager.getTest().log(Status.INFO, "User with userID : '"+ user.getId() + "' Getting fetched");

        String userNameToFind = this.user.getUsername();

        //ExtentReportManager.getTest().log(Status.INFO, "Starting Test to Fetch User with username : '"+ user.getUsername() + "'");
        Logger.info("Starting Test to Fetch User with username : '"+ user.getUsername() + "'");
        response = UserEndPoints.getUser(userNameToFind);
        response.then().log().body();
        System.out.println(response.body().toString());
        Assert.assertEquals(response.getStatusCode(),200);
        //ExtentReportManager.getTest().log(Status.INFO, "User with username : '"+ user.getUsername() + "' FOUND");
        Logger.info("User with username : '"+ user.getUsername() + "' FOUND");
        //get user using id from database


        Logger.info("Fetching user in DB with username : '"+ user.getUsername() + "'");

        query = "SELECT * from users where Username = '" + user.getUsername() + "'";

        //ExtentReportManager.getTest().log(Status.INFO, "Query used : "+query);
        Logger.info("Query used : {}", query);
        ResultSet result = database.executeQuery(query);
        int userId = 0;
        if (result != null && result.next()) {

             userId = result.getInt("UserId");
        }
        System.out.println("UserId from database is : " + userId);
        Assert.assertTrue(response.getBody().asString().contains(String.valueOf(userId)), "userID from DB '"+userId +"' is not Matching with API Response");
        //ExtentReportManager.getTest().log(Status.INFO, user.getId() +" is not Matching with DB and API Response");
        Logger.info(user.getId() +" is Matching with DB and API Response");
        //assert both users are same

    }

    @Test
    public void testCreateUserAndVerifyInOracleDatabase() {
        String requestBody = "{ \"userId\": 12345, \"username\": \"new.user\", \"firstName\": \"New\", \"lastName\": \"User\", \"email\": \"new.user@example.com\", \"password\": \"test@123\", \"phone\": \"9876543210\" }";

        String userId = "23423";
        String expectedUsername = "donald.trump";
        // Verify User in Database
        String query = "SELECT Username FROM users WHERE UserId = " + userId;
        ResultSet resultSet = database.executeQuery(query);
        try {
            if (resultSet.next()) {
                assertThat("Username should match", resultSet.getString("Username"), equalTo(expectedUsername));

                LoggerUtils.info("User verified successfully in the database.");
            } else {
                LoggerUtils.error("No user found in database with UserId: "+userId);
            }
        } catch (SQLException e) {
            LoggerUtils.error("Error while validating database results.", e);
        }
    }


}
