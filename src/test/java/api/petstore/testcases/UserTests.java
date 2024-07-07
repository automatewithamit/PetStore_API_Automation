package api.petstore.testcases;

import api.petstore.endpoints.UserEndPoints;
import api.petstore.payloads.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTests {
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


    @Test(priority = 1)
    public void createUserTest(){
    Response response = UserEndPoints.createUser(user);
    response.then().log().all();
    Assert.assertEquals(response.getStatusCode(),200);

    }

    @Test(priority = 2)
    public void getUserTest(){
        String userNameToFind = this.user.getUsername();
        System.out.println("Find User : "+ userNameToFind);
        Response response = UserEndPoints.getUser(userNameToFind);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);

    }
    @Test(priority = 3)
    public void updateUserTest(){
        user.setFirstName(faker.name().firstName());
        Response response = UserEndPoints.updateUser(this.user,this.user.getFirstName());
        response.then().log().body();
        Assert.assertEquals(response.getStatusCode(),200);
        System.out.println("Update User");
        response.then().log().body();
    }
    @Test(priority = 4)
    public void deleteUserTest(){
        Response response = UserEndPoints.deleteUser(this.user.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);

    }
}
