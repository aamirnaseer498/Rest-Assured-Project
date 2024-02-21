package Chaining;

import com.github.javafaker.Faker;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteUser {

    @Test
    void deleteUser(ITestContext context){

        String token= context.getAttribute("token").toString();
        String url= context.getAttribute("url").toString();
        int id= (int) context.getAttribute("id");

        System.out.println();
        System.out.println("User with ID: " + id + " is deleted by DELETE request");
        System.out.println();

        given()
                .headers("Authorization", "Bearer " + token)

                .when()
                .delete(url + "/" + id)

                .then()
                .statusCode(204)
                .log().body();

    }

}
