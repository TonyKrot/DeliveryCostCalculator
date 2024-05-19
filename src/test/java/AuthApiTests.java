import configurations.TestConfig;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@Story("Auth API")
public class AuthApiTests {

    TestConfig config = new TestConfig();

    @Test
    void testUserToken() {

        Response response = given()
                .header("Authorization",config.getBasicAuth())
                .header("Accept-Language", "ru,en;q=0.9,es;q=0.8")
                .header("Aelang", "en_US")
                .header("Aesite", "AEO_US")
                .formParam("grant_type", "client_credentials")
                .contentType("application/x-www-form-urlencoded")
                .post(config.getAuthUrl());

        response.then()
                .log()
                .all()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("scope", equalTo("guest"))
                .body("token_type", equalTo("client_credentials"))
                .body("expires_in", equalTo(1800))
                .extract().response();
        String token = response.jsonPath().get("access_token");
        System.out.println("Token: " + token);
    }
}