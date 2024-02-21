package Chaining;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetUser {

    @Test
    void getUser(ITestContext context){

        String token= context.getAttribute("token").toString();
        String url= context.getAttribute("url").toString();
        int id= (int) context.getAttribute("id");

        System.out.println("Details of user fetched by GET request");
        System.out.println();

        given()
                .headers("Authorization", "Bearer " + token)
                .queryParam("id", id)

                .when()
                .get(url)

                .then()
                .statusCode(200)
                .log().body();
    }

}
