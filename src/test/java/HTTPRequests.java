import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class HTTPRequests {

    int id;

    @Test(priority = 1)
    void createUser(){
        HashMap data= new HashMap<>();
        data.put("name","Test1");
        data.put("job","QA Engineer");

        id=given().contentType("application/json").body(data)
                .when().post("https://reqres.in/api/users").jsonPath().getInt("id");
    }

    @Test(priority = 2)
   void getAllUser(){
        given()
                .when().get("https://reqres.in/api/users?page=1")
                .then().statusCode(200).log().all();
   }

   @Test(priority = 3)
   void getSingleUser(){
       given()
               .when().get("https://reqres.in/api/users/"+id)
               .then().statusCode(200).log().all();
   }

   @Test(priority = 4)
   void updateUser(){
       HashMap data= new HashMap<>();
       data.put("name","Test01");
       data.put("job","SQA Engineer");

       given().contentType("application/json").body(data)
               .when().put("https://reqres.in/api/users/"+id)
               .then().statusCode(200).log().all();
   }

   @Test(priority = 5)
   void deleteUser(){
       given()
               .when().delete("https://reqres.in/api/users/"+id)
               .then().statusCode(204).log().all();
   }

}
