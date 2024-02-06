import io.restassured.matcher.RestAssuredMatchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class XMLSchemaValidation{

    @Test
    void xmlSchemaValidation(){

        given()
                .when().get("http://restapi.adequateshop.com/api/Traveler")
                .then().assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("xml-schema.xsd"));

    }

}
