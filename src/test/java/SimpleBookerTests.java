import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class SimpleBookerTests {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    }

    @Test
    public void testSuccessfulAuth() {
        String requestBody = "{\"username\":\"admin\",\"password\":\"password123\"}";
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post("/auth");
        response.then().log().all()
                .statusCode(200)
                .body("token", notNullValue());
    }

    @Test
    public void testCreateBooking() {
        String requestBody = "{\"firstname\":\"John\",\"lastname\":\"Doe\",\"totalprice\":111,\"depositpaid\":true,\"bookingdates\":{\"checkin\":\"2022-01-01\",\"checkout\":\"2022-01-02\"},\"additionalneeds\":\"Breakfast\"}";
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post("/booking");
        response.then().log().all()
                .statusCode(200)
                .body("bookingid", notNullValue())
                .body("booking", notNullValue());
    }

    @Test
    public void testUpdateBookingWithPartialPayload() {
        int bookingId = 6889; // booking ID
        String requestBody = "{\"firstname\":\"KURT\"}"; // Only updating the firstname
        Response response = given()
                .header("Cookie", "faba52c0754b4b3")
                .contentType(ContentType.JSON)
                .body(requestBody)
                .patch("/booking/" + bookingId);
        response.then().log().all()
                .statusCode(200)
                .body("firstname", equalTo("KURT"))
                .body("lastname", equalTo("Doe"))
                .body("totalprice", equalTo(111))
                .body("depositpaid", equalTo(true))
                .body("bookingdates.checkin", equalTo("2022-01-01"))
                .body("bookingdates.checkout", equalTo("2022-01-02"))
                .body("additionalneeds", equalTo("Breakfast"));
    }

    @Test
    public void testUpdateBooking() {
        int bookingId = 221; // booking ID
        String requestBody = "{\"firstname\":\"Jane\",\"lastname\":\"Doe\",\"totalprice\":222,\"depositpaid\":true,\"bookingdates\":{\"checkin\":\"2022-01-03\",\"checkout\":\"2022-01-04\"},\"additionalneeds\":\"Lunch\"}";
        Response response = given()
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .contentType(ContentType.JSON)
                .body(requestBody)
                .put("/booking/" + bookingId);
        response.then().log().all()
                .statusCode(200)
                .body("firstname", equalTo("Jane"))
                .body("lastname", equalTo("Doe"))
                .body("totalprice", equalTo(222))
                .body("depositpaid", equalTo(true))
                .body("bookingdates.checkin", equalTo("2022-01-03"))
                .body("bookingdates.checkout", equalTo("2022-01-04"))
                .body("additionalneeds", equalTo("Lunch"));
    }
}