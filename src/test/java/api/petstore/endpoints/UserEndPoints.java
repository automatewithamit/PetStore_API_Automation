package api.petstore.endpoints;

import api.petstore.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static api.petstore.endpoints.Routes.*;
import static io.restassured.RestAssured.*;

public class UserEndPoints {

    public static Response createUser(User payload){
        Response response = given().accept(ContentType.JSON).contentType(ContentType.JSON)
                .body(payload).when().post(post_url);

        return response;
    }

}
