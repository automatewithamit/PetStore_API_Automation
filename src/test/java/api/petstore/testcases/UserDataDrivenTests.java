package api.petstore.testcases;

import api.petstore.endpoints.UserEndPoints;
import api.petstore.payloads.User;
import api.petstore.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserDataDrivenTests {

    @Test(priority = 1, dataProvider = "getAllData",dataProviderClass = DataProviders.class)
    public void CreateUserTest(int userId,String userName,String firstName,String lastName,String email,String phone){
        User user = new User();
        user.setId(userId);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(userName);
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
        Response response = UserEndPoints.deleteUser(userName);
        //Log Response
        response.then().log().body();
        //Validation
        Assert.assertEquals(response.getStatusCode(),200);
    }


}
