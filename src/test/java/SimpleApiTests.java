import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.Order;
import models.Pet;
import models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

class SimpleApiTests {

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    @AfterEach
    public void tearDown() {
        RestAssured.reset();
    }
// Pet Endpoint Tests
    @Test
    public void testPostCreatePet() {
        Pet pet = new Pet();
        pet.setId(123L);
        pet.setName("doggie");
        pet.setStatus("available");

        given()
                .contentType(ContentType.JSON)
                .body(pet)
                .when()
                .post("/pet")
                .then()
                .statusCode(200)
                .body("name", equalTo("doggie"));
    }

    @Test
    public void testGetPetById() {
        given()
                .pathParam("petId", 123)
                .when()
                .get("/pet/{petId}")
                .then().log().all()
                .statusCode(200)
                .body("id", equalTo(123));
    }

    @Test
    public void testGetPetsByStatus() {
        given()
                .param("status", "available")
                .when()
                .get("/pet/findByStatus")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    public void testUpdatePet() {
        Pet pet = new Pet();
        pet.setId(123);
        pet.setName("new doggie");
        pet.setStatus("available");

        given()
                .contentType(ContentType.JSON)
                .body(pet)
                .when()
                .put("/pet")
                .then().log().all()
                .statusCode(200)
                .body("name", equalTo("new doggie"));
    }

    @Test
    public void testDeletePet() {
        given()
                .pathParam("petId", 123)
                .when()
                .delete("/pet/{petId}")
                .then()
                .statusCode(200);
    }
// Store Endpoint Tests
    @Test
    public void testGetInventory() {
        when()
                .get("/store/inventory")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    public void testPlaceOrder() {
        Order order = new Order();
        order.setId(100);
        order.setPetId(123);
        order.setStatus("placed");

        given()
                .contentType(ContentType.JSON)
                .body(order)
                .when()
                .post("/store/order")
                .then().log().all()
                .statusCode(200)
                .body("petId", equalTo(123));
    }

    @Test
    public void testGetOrderById() {
        given()
                .pathParam("orderId", 100)
                .when()
                .get("/store/order/{orderId}")
                .then()
                .statusCode(200)
                .body("id", equalTo(100));
    }

    @Test
    public void testDeleteOrder() {
        given()
                .pathParam("orderId", 100)
                .when()
                .delete("/store/order/{orderId}")
                .then()
                .statusCode(200);
    }

// User Endpoint Tests
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
        String username = "FPMI_test_user5";

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