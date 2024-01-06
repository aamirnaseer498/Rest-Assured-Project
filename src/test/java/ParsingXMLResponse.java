import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ParsingXMLResponse {

    @Test
    void testXMLStaticResponse(){

        given()
                .when()
                .get("http://restapi.adequateshop.com/api/Traveler?page=1")
                .then()
                .statusCode(200)
                .body("TravelerinformationResponse.page",equalTo("1"))
                .body("TravelerinformationResponse.travelers.Travelerinformation[0].id",equalTo("11133"));

    }

}
