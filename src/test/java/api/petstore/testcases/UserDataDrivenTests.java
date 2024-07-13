package api.petstore.testcases;

import api.petstore.endpoints.UserEndPoints;
import api.petstore.payloads.User;
import api.petstore.reporting.ExtentReportManager;
import api.petstore.utilities.DataProviders;
import com.aventstack.extentreports.Status;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserDataDrivenTests extends BaseTest {

    @Test(priority = 1, dataProvider = "getAllData",dataProviderClass = DataProviders.class)
    public void CreateUserTest(String userId,String userName,String firstName,String lastName,String email,String password, String phone){
        ExtentReportManager.getTest().log(Status.INFO, "Starting Test to Create User with username : '"+ userName+ "'");

        Logger.info("Starting Test to Create User with username : '{}'", userName);

        System.out.println("userName "+userName);
        System.out.println("firstname "+firstName);
        User user = new User();
        user.setId(Integer.parseInt(userId));
        user.setFirstName(firstName.toString());
        user.setLastName(lastName.toString());
        user.setUsername(userName.toString());
        user.setPassword(password.toString());
        user.setPhone(phone);
        user.setEmail(email);
        user.setUserStatus(0);

        Response response = UserEndPoints.createUser(user);
        //Log Response
        response.then().log().body();
        //Validation
        Assert.assertEquals(response.getStatusCode(),200);
    }
    @Test(priority = 2, dataProvider = "getUserNameColumnValues",dataProviderClass = DataProviders.class)
    public void DeleteUserTest(String userName){
        ExtentReportManager.getTest().log(Status.INFO, "Starting Test to Delete User with username : '"+ userName+ "'");
        System.out.println("userName "+userName);
        Response response = UserEndPoints.deleteUser(userName);
        //Log Response
        response.then().log().body();
        //Validation
        Assert.assertEquals(response.getStatusCode(),200);
    }


}
