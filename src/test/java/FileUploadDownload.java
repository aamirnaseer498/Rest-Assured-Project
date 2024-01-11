import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

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

    @Test
    void multipleFileUpload(){

        File file1= new File("C:\\Users\\9D\\Downloads\\Test-Data\\Documents\\DWSample1-TXT.txt");
        File file2= new File("C:\\Users\\9D\\Downloads\\Test-Data\\Documents\\DWSample2-TXT.txt");

        given()
                .multiPart("files",file1)
                .multiPart("files",file2)
                .contentType("multipart/form-data")

                .when()
                .post("http://localhost:8080/uploadMultipleFiles")

                .then()
                .statusCode(200)
                .body("[0].fileName",equalTo("DWSample1-TXT.txt"))
                .body("[1].fileName",equalTo("DWSample2-TXT.txt"))
                .log()
                .all();

    }

}
