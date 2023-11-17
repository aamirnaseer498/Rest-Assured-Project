import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class POSTRequestPayloads {

    // 1. HashMap
    @Test(priority = 1)
    void postPayloadHashMap(){
        HashMap data= new HashMap<>();

        data.put("name","Scott");
        data.put("location","Canada");
        data.put("phone","123456789");

        String[] courses= {"C", "C++"};
        data.put("courses",courses);

        given()
                .contentType("application/json")
                .body(data)

                .when()
                .post("http://localhost:3000/students")

                .then()
                .statusCode(201)
                .body("name", equalTo("Scott"))
                .body("location", equalTo("Canada"))
                .body("phone", equalTo("123456789"))
                .body("courses[0]", equalTo("C"))
                .body("courses[1]", equalTo("C++"))
                .log().all();
    }
    @Test(priority = 2)
    void deletePayloadHashMap(){
        given()
                .when().delete("http://localhost:3000/students/4")
                .then().statusCode(200).log().status();
    }

    // 2. org.json
    @Test(priority = 3)
    void postPayloadOrgJson(){
        JSONObject data= new JSONObject();

        data.put("name","Scott");
        data.put("location","Canada");
        data.put("phone","123456789");

        String[] courses= {"C", "C++"};
        data.put("courses",courses);

        given()
                .contentType("application/json")
                .body(data.toString())

                .when()
                .post("http://localhost:3000/students")

                .then()
                .statusCode(201)
                .body("name", equalTo("Scott"))
                .body("location", equalTo("Canada"))
                .body("phone", equalTo("123456789"))
                .body("courses[0]", equalTo("C"))
                .body("courses[1]", equalTo("C++"))
                .log().all();
    }
    @Test(priority = 4)
    void deletePayloadOrgJson(){
        given()
                .when().delete("http://localhost:3000/students/4")
                .then().statusCode(200).log().status();
    }

    // 3. POJO (Plain Old Java Object)
    @Test(priority = 5)
    void postPayloadPojo(){
        PojoHelper data= new PojoHelper();

        data.setName("Scott");
        data.setLocation("Canada");
        data.setPhone("123456789");

        String[] courses= {"C", "C++"};
        data.setCourses(courses);

        given()
                .contentType("application/json")
                .body(data)

                .when()
                .post("http://localhost:3000/students")

                .then()
                .statusCode(201)
                .body("name", equalTo("Scott"))
                .body("location", equalTo("Canada"))
                .body("phone", equalTo("123456789"))
                .body("courses[0]", equalTo("C"))
                .body("courses[1]", equalTo("C++"))
                .log().all();
    }
    @Test(priority = 6)
    void deletePayloadPojo(){
        given()
                .when().delete("http://localhost:3000/students/4")
                .then().statusCode(200).log().status();
    }

    // 4. External JSON File
    @Test(priority = 7)
    void postPayloadExternalJSON(){
        File file;
        FileReader fileReader;
        JSONTokener jsonTokener;
        JSONObject data;

        try {
            file= new File(".\\body.json");
            fileReader= new FileReader(file);
            jsonTokener= new JSONTokener(fileReader);
            data= new JSONObject(jsonTokener);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        given()
                .contentType("application/json")
                .body(data.toString())

                .when()
                .post("http://localhost:3000/students")

                .then()
                .statusCode(201)
                .body("name", equalTo("Scott"))
                .body("location", equalTo("Canada"))
                .body("phone", equalTo("123456789"))
                .body("courses[0]", equalTo("C"))
                .body("courses[1]", equalTo("C++"))
                .log().all();
    }
    @Test(priority = 8)
    void deletePayloadExternalJSON(){
        given()
                .when().delete("http://localhost:3000/students/4")
                .then().statusCode(200).log().status();
    }

}
