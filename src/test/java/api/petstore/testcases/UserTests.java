package api.petstore.testcases;

import api.petstore.endpoints.UserEndPoints;
import api.petstore.payloads.User;
import api.petstore.reporting.ExtentReportManager;
import com.aventstack.extentreports.Status;
import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.extern.java.Log;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTests extends  BaseTest{
    Faker faker;
    User user;

    @BeforeClass
    @Step("User Data Getting Generated..")
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

    @Description("This test verifies that User is getting created into the system")
    @Test(priority = 1)
    public void createUserTest(){

    ExtentReportManager.getTest().log(Status.INFO, "Starting Test to Create User with username : '"+ user.getUsername() + "'");
    Logger.info("Starting Test to Create User with username : '"+ user.getUsername() + "'");
    Response response = UserEndPoints.createUser(user);
    response.then().log().body();
    Assert.assertEquals(response.getStatusCode(),200);
    ExtentReportManager.getTest().log(Status.INFO, "User with username : '"+ user.getUsername() + "' CREATED Successfully");
    Logger.info("User with username : '"+ user.getUsername() + "' CREATED Successfully");
    }
    @Description("This test verifies that User is Fetched using API Get Call")
    @Test(priority = 2)
    public void getUserTest(){
        String userNameToFind = this.user.getUsername();
        ExtentReportManager.getTest().log(Status.INFO, "Starting Test to Fetch User with username : '"+ user.getUsername() + "'");
        Logger.info("Starting Test to Fetch User with username : '"+ user.getUsername() + "'");
        System.out.println("Find User : "+ userNameToFind);
        Response response = UserEndPoints.getUser(userNameToFind);
        response.then().log().body();
        Assert.assertEquals(response.getStatusCode(),200);
        ExtentReportManager.getTest().log(Status.INFO, "User with username : '"+ user.getUsername() + "' FOUND");
        Logger.info("User with username : '"+ user.getUsername() + "' FOUND");
    }
    @Test(priority = 3)
    @Description("This test verifies that User is being able to be updated using PATCH api call")
    public void updateUserTest(){
        String newFirstName = faker.name().firstName();
        ExtentReportManager.getTest().log(Status.INFO, "Starting Test to Update User's FirstName '"+user.getFirstName()+"' with new First Name : '"+ newFirstName + "'");
        Logger.info("Starting Test to Update User's FirstName '"+user.getFirstName()+"' with new First Name : '"+ newFirstName + "'");
        user.setFirstName(newFirstName);
        Response response = UserEndPoints.updateUser(this.user,this.user.getFirstName());
        response.then().log().body();
        Assert.assertEquals(response.getStatusCode(),200);
        System.out.println("Update User");
        response.then().log().body();
        ExtentReportManager.getTest().log(Status.INFO, "User Updated with new firstname : '"+ newFirstName + "' Successfully");
        Logger.info("User Updated with new firstname : '"+ newFirstName + "' Successfully");
    }
    @Test(priority = 4)
    @Description("This test verifies that User is being able to be Deleted by Delete API Call")
    public void deleteUserTest(){
        ExtentReportManager.getTest().log(Status.INFO, "Starting Test to DELETE User with username : '"+ user.getUsername() + "'");
        Logger.info("Starting Test to DELETE User with username : '"+ user.getUsername() + "'");
        Response response = UserEndPoints.deleteUser(this.user.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
        ExtentReportManager.getTest().log(Status.INFO, "User with username : '"+ user.getUsername() + "' DELETED Successfully");
        Logger.info("User with username : '{}' DELETED Successfully", user.getUsername());
    }

}
