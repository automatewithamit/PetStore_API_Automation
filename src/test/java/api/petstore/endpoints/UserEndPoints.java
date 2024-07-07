package api.petstore.endpoints;

import api.petstore.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static api.petstore.endpoints.Routes.*;
import static io.restassured.RestAssured.*;

public class UserEndPoints {

    public static Response createUser(User payload){
        Response response = given()
                                .accept(ContentType.JSON)
                                .contentType(ContentType.JSON)
                                .body(payload)
                                .when()
                                .post(post_url);

        return response;
    }
    public static Response getUser(String username){
        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .pathParam("username",username)
                .when()
                .get(get_url);

        return response;
    }

    public static Response updateUser(User payload,String username){
        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .pathParam("username",username)
                .body(payload)
                .when()
                .put(put_url);

        return response;
    }
    public static Response deleteUser(String username){
        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .pathParam("username",username)
                .when()
                .delete(del_url);

        return response;
    }

}
