import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class PathAndQueryParameters {

        @Test
        void pathAndQueryTest(){

            given().pathParam("path1","users").queryParam("page","2").queryParam("id","4")
                    .when().get("https://reqres.in/api/{path1}")
                    .then().statusCode(200).log().all();

        }

}