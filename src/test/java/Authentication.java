import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Authentication {

    @Test
    void basicAuthentication(){

        given()
                .auth().basic("postman","password")

                .when()
                .get("https://postman-echo.com/basic-auth")

                .then()
                .statusCode(200)
                .body("authenticated",equalTo(true))
                .log().all();

    }

    @Test
    void digestAuthentication(){

        given()
                .auth().digest("postman","password")

                .when()
                .get("https://postman-echo.com/basic-auth")

                .then()
                .statusCode(200)
                .body("authenticated",equalTo(true))
                .log().all();

    }

}
