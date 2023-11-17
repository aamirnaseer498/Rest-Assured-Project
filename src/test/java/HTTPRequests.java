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
                .when().post("http://localhost:3000/students").jsonPath().getInt("id");
    }

    @Test(priority = 2)
   void getAllUser(){
        given()
                .when().get("http://localhost:3000/students")
                .then().statusCode(200).log().all();
   }

   @Test(priority = 3)
   void getSingleUser(){
       given()
               .when().get("http://localhost:3000/students/"+id)
               .then().statusCode(200).log().all();
   }

   @Test(priority = 4)
   void updateUser(){
       HashMap data= new HashMap<>();
       data.put("name","Test01");
       data.put("job","SQA Engineer");

       given().contentType("application/json").body(data)
               .when().put("http://localhost:3000/students/"+id)
               .then().statusCode(200).log().all();
   }

   @Test(priority = 5)
   void deleteUser(){
       given()
               .when().delete("http://localhost:3000/students/"+id)
               .then().statusCode(200).log().all();
   }

}
