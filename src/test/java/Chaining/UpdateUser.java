package Chaining;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UpdateUser {

    @Test
    void updateUser(ITestContext context){

        String token= context.getAttribute("token").toString();
        String url= context.getAttribute("url").toString();
        int id= (int) context.getAttribute("id");

        Faker faker= new Faker();

        JSONObject jsonObject= new JSONObject();
        jsonObject.put("name",faker.name().fullName());
        jsonObject.put("email",faker.internet().emailAddress());
        jsonObject.put("gender","Male");
        jsonObject.put("status","Active");

        System.out.println();
        System.out.println("Details of updated user fetched by PUT request");
        System.out.println();

        given()
                .headers("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(jsonObject.toString())

                .when()
                .put(url + "/" + id)

                .then()
                .statusCode(200)
                .log().body();

    }

}
