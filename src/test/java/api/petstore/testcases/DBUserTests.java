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
        ResultSet resultSet = DBUtils.executeQuery(query);

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
}
