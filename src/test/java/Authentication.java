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

    @Test
    void preemptiveAuthentication(){

        given()
                .auth().preemptive().basic("postman","password")

                .when()
                .get("https://postman-echo.com/basic-auth")

                .then()
                .statusCode(200)
                .body("authenticated",equalTo(true))
                .log().all();

    }

    @Test
    void bearerTokenAuthentication(){

        String token= "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";

        given()
                .headers("Authorization","Bearer " + token)

                .when()
                .get("https://api.github.com/user/repos")

                .then()
                .statusCode(200)
                .log().all();

    }

    @Test
    void oAuth2Authentication(){

        String token= "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";

        given()
                .auth().oauth2(token)

                .when()
                .get("https://api.github.com/user/repos")

                .then()
                .statusCode(200)
                .log().all();

    }

    @Test
    void apiKeyAuthentication(){

        String key= "f2f6c2f4bb5989e855fd75d691d2a737";

        given()
                .pathParams("path","data/2.5/weather")
                .queryParam("q","Rawalpindi")
                .queryParam("units","metric")
                .queryParam("cnt","7")
                .queryParam("appid",key)

                .when()
                .get("https://api.openweathermap.org/{path}")

                .then()
                .statusCode(200)
                .log().all();

    }

}
