import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

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

    @Test
    void testXMLDynamicResponse(){

        Response response= given()
                .when().get("http://restapi.adequateshop.com/api/Traveler?page=1");

        XmlPath xmlObject= new XmlPath(response.asString());

        List<String> travelers= xmlObject.getList("TravelerinformationResponse.travelers.Travelerinformation.name");

        boolean status= false;

        for (String traveler: travelers){

            System.out.println("Traveler Name: " + traveler);

            if (traveler.equals("Aamir")){
                status= true;
                break;
            }
        }

        Assert.assertTrue(status,"Traveler not found");

    }

}
