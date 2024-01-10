import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class FileUploadDownload {

    @Test
    void singleFileUpload(){

        File file= new File("C:\\Users\\9D\\Downloads\\Test-Data\\Documents\\DWSample1-TXT.txt");

        given()
                .multiPart("file",file)
                .contentType("multipart/form-data")

                .when()
                .post("http://localhost:8080/uploadFile")

                .then()
                .log()
                .all();

    }

}
