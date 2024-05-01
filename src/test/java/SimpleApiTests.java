import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.matchesPattern;
import static org.hamcrest.Matchers.startsWith;

class SimpleApiTests {

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    @AfterEach
    public void tearDown() {
        RestAssured.reset();
    }

    @Test
    void testPostCreateUser() {
        User user = new User(0, "FPMI_test_user5", "firstName5", "lastName5", "email3@gmail.com", "qwe123",
                "123123123",
                0);
        var response = given().
                header("accept", "application/json").
                header("Content-Type", "application/json").
                body(user).
                when().
                post("/user").
                then();
        response.log().body();
        response.statusCode(200);

        String username = "FPMI_test_user5";
        String endpoint2 = "https://petstore.swagger.io/v2/user/" + username;
        given().
                when().
                get(endpoint2).
                then().
                log().
                all().
                assertThat().
                statusCode(200).
                body("username", equalTo("FPMI_test_user5")).
                body("firstName", startsWith("firstName5")).
                body("lastName", equalToIgnoringCase("LASTNAME5")).
                body("email", matchesPattern("^[a-zA-Z0-9._%+-]+@gmail\\.com$")).
                body("password", equalTo("qwe123")).
                body("phone", equalTo("123123123"));
    }

    @Test
    public void testGetUserByUsername() {
        String username = "FPMI_test_user777";

        Response response = given()
                .pathParam("username", username)
                .when()
                .get("/user/{username}");

        response.then().log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("username", equalTo(username));
    }

    @Test
    public void testGetUserByUsernameNotFound() {
        String username = "User404";

        Response response = given()
                .pathParam("username", username)
                .when()
                .get("/user/{username}");

        response.then().log().all()
                .statusCode(404)
                .contentType(ContentType.JSON)
                .body("message", equalTo("User not found"));
    }

    @Test
    void testPutUserByUsername() {
        String username = "FPMI_test_user5";
        String body = """
        {
          "id": 0,
          "username": "FPMI_user_333",
          "firstName": "firstName3",
          "lastName": "lastName3",
          "email": "email222@gmail.com",
          "password": "psw",
          "phone": "758389475",
          "userStatus": 0
        }
        """;

        var response = given()
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(body)
                .pathParam("username", username)
                .when()
                .put("/user/{username}")
                .then()
                .assertThat()
                .statusCode(200);

        response.log().body();
    }

    @Test
    public void testDeleteUser() {
        String username = "FPMI_user_333";

        Response response = given()
                .pathParam("username", username)
                .when()
                .delete("/user/{username}");

        response.then().log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("message", equalTo("FPMI_user_333"));
    }

    @Test
    public void testDeleteUserByUsernameNotFound() {
        String username = "FPMI_test_404";

        Response response = given()
                .pathParam("username", username)
                .when()
                .delete("/user/{username}");

        response.then()
                .statusCode(404);
    }
}