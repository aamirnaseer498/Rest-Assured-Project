import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ParsingJSONResponse {

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

        Response response= given().contentType(ContentType.JSON)
                .when().get("http://localhost:3000/students");

        JSONArray jsonArray= new JSONArray(response.asString());
        int length= jsonArray.length();

        boolean status= false;

        for (int i=0; i<length; i++) {
            String name= jsonArray.getJSONObject(i).get("name").toString();
            if (name.equals("Usama")){
                status= true;
                break;
            }
        }

        Assert.assertTrue(status);

    }

}
