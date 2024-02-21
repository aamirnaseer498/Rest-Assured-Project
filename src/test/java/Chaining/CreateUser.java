package Chaining;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreateUser {

    @Test
    void createUser(ITestContext context){

        Faker faker= new Faker();

        String token= "26fa336766a4b5389f2f2a1985b6645c513f0b29f9270884f076d8f250d19a2d";
        String url= "https://gorest.co.in/public/v2/users";

        context.setAttribute("token",token);
        context.setAttribute("url",url);

        JSONObject jsonObject= new JSONObject();
        jsonObject.put("name",faker.name().fullName());
        jsonObject.put("email",faker.internet().emailAddress());
        jsonObject.put("gender","Male");
        jsonObject.put("status","Active");

        Response response = given()
                .headers("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(jsonObject.toString())

                .when()
                .post(url);

        int id= response.jsonPath().getInt("id");

        System.out.println();
        System.out.println("New user is created by POST request with the ID: " + id);
        System.out.println();

        context.setAttribute("id",id);

    }

}
