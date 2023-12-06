import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ParsingResponseBody {

    @Test
    void testJSONStaticResponse(){

        Response response= given()
                .when().get("http://localhost:3000/students");

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.header("Content-Type"),"application/json; charset=utf-8");
        Assert.assertEquals(response.jsonPath().get("[2].courses[0]").toString(),"javascript");

    }

    @Test
    void testJSONDynamicResponse(){

        Response response= given()
                .when().get("http://localhost:3000/students");

    }

}
