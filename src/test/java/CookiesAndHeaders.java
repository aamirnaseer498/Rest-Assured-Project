import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class CookiesAndHeaders {

    @Test
    void testCookies(){

        given()
                .when().get("https://www.google.com/")
                .then().cookie("AEC","Ackid1TDQUREU4W_eyoDMOuL2Wrc_OPBW6VfpgHXOr6AusW_C2EM6fWbJg").log().cookies();

    }

    @Test
    void getSingleCookieInfo(){

        Response response= given()
                .when().get("https://www.google.com/");

        String cookie= response.getCookie("AEC");
        System.out.println("The value of cookie AEC: " + cookie);

    }

    @Test
    void getMultipleCookies(){

        Response response= given()
                .when().get("https://www.google.com/");

        Map<String,String> cookies= response.getCookies();

        for (String cookie: cookies.keySet()){
            System.out.println("The value of cookie " + cookie + ": " + response.getCookie(cookie));
        }

    }

    @Test
    void testHeaders(){

        given()
                .when().get("https://www.google.com/")
                .then().header("Content-Type","text/html; charset=ISO-8859-1").and().header("Content-Encoding","gzip").and().header("Server","gws")
                .log().headers();

    }

    @Test
    void getSingleHeaderInfo(){

        Response response= given()
                .when().get("https://www.google.com/");

        String headerValue= response.getHeader("Content-Type");
        System.out.println("The value of Content-Type header: " + headerValue);

    }

    @Test
    void getMultipleHeadersInfo(){

        Response response= given()
                .when().get("https://www.google.com/");

        Headers headers= response.getHeaders();
        for (Header header: headers){
            System.out.println("The value of header " + header.getName() + ": " + headers.getValue(header.getName()));
        }

    }

}
