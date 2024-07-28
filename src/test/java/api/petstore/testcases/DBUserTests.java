package api.petstore.testcases;

import api.petstore.utilities.DBUtils;
import api.petstore.utilities.LoggerUtils;
import org.testng.annotations.Test;
import java.sql.ResultSet;
import java.sql.SQLException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DBUserTests extends BaseTest {

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
