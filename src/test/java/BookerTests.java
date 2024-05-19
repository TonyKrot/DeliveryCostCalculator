import configurations.TestConfig;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import models.Booker;
import models.BookingDates;
import models.BookingResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import testdata.ApiTestData;

import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;
import static org.assertj.core.api.Assertions.assertThat;

import static org.hamcrest.Matchers.equalTo;

class BookerTests {
    TestConfig testConfig = new TestConfig();

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    }

    @Test
    @DisplayName("Create book")
    @Tag("book")
    public void testCreateBooking() {
        Booker booker = ApiTestData.DEFAULT_BOOKER;

        String response = given()
                .header("Authorization", testConfig.getBookerAuth())
                .contentType(ContentType.JSON)
                .body(booker)
                .post("/booking")
                .then()
                .statusCode(200)
                .extract().response().asString();

        JsonPath jsonPath = from(response);
        BookingResponse bookingResponse = jsonPath.getObject("$", BookingResponse.class);
        Booker bookerResponse = bookingResponse.getBooking();

        System.out.println("bookingid: " + bookingResponse.getBookingid());

        assertThat(bookingResponse.getBookingid()).isGreaterThan(0);
        assertThat(bookerResponse.getFirstname()).isEqualTo("John");
        assertThat(bookerResponse.getLastname()).isEqualTo("Doe");
        assertThat(bookerResponse.getTotalprice()).isEqualTo(333);
        assertThat(bookerResponse.getAdditionalneeds()).isEqualTo("Dinner");
    }

    @Test
    @DisplayName("UpdatePartial book")
    @Tag("book")
    public void testUpdateBookingWithPartialPayload() {
        BookingDates bookingdates = new BookingDates("2022-01-03", "2022-01-04");
        Booker booker = new Booker(4429, "Julia", "Doe", 222, true, bookingdates, "Lunch");

        Booker partialBooker = new Booker(booker.getId(), "Klara", booker.getLastname(), booker.getTotalprice(),
                booker.isDepositpaid(), booker.getBookingdates(), booker.getAdditionalneeds());

        Response response = given()
                .header("Authorization", testConfig.getBookerAuth())
                .contentType(ContentType.JSON)
                .accept("application/json")
                .body(partialBooker)
                .patch("/booking/" + booker.getId());

        response.then()
                .statusCode(200)
                .body("firstname", equalTo("Klara")) // firstname was updated
                .body("lastname", equalTo("Doe"))
                .body("totalprice", equalTo(222))
                .body("depositpaid", equalTo(true))
                .body("bookingdates.checkin", equalTo("2022-01-03"))
                .body("bookingdates.checkout", equalTo("2022-01-04"))
                .body("additionalneeds", equalTo("Lunch"));
        System.out.println("firstname was updated");
    }

    @Test
    @DisplayName("Update book")
    @Tag("book")
    public void testUpdateBooking() {
        BookingDates bookingdates = new BookingDates("2022-01-03", "2022-01-04");
        Booker booker = new Booker(4429, "Jane", "Doe", 222, true, bookingdates, "Lunch");

        Response response = given()
                .header("Authorization", testConfig.getBookerAuth())
                .contentType(ContentType.JSON)
                .accept("application/json")
                .body(booker)
                .put("/booking/" + booker.getId());

        response.then()
                .statusCode(200)
                .body("firstname", equalTo("Jane"))
                .body("lastname", equalTo("Doe"))
                .body("totalprice", equalTo(222))
                .body("depositpaid", equalTo(true))
                .body("bookingdates.checkin", equalTo("2022-01-03"))
                .body("bookingdates.checkout", equalTo("2022-01-04"))
                .body("additionalneeds", equalTo("Lunch"));
        System.out.println("Booker was updated");
    }
}