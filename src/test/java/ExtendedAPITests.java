import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.path.json.JsonPath;
import models.Order;
import models.Pet;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.path.json.JsonPath.from;
import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings({"rawtypes", "unchecked"})
class ExtendedAPITests {

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    @AfterEach
    public void tearDown() {
        RestAssured.reset();
    }

    // Pet Endpoint Tests with JsonPath

    @Test
    @DisplayName("Add a new pet")
    @Tag("pet")
    public void testAddPet() {
        Pet pet = new Pet();
        pet.setId(100);
        pet.setName("Fido");
        pet.setStatus("available");

        String response = given()
                .header("Content-Type", "application/json")
                .body(pet)
                .post("/pet")
                .then()
                .statusCode(200)
                .extract().response().asString();

        JsonPath jsonPath = from(response);
        assertThat(jsonPath.getString("name")).isEqualTo("Fido");
        assertThat(jsonPath.getString("status")).isEqualTo("available");

        // Validate JSON schema
        given()
                .body(response)
                .then()
                .body(matchesJsonSchemaInClasspath("jsonSchema/petSchema.json"));
    }

    @Test
    @DisplayName("Get pet by ID")
    @Tag("pet")
    public void testGetPetById() {
        String response = given()
                .pathParam("petId", 100)
                .get("/pet/{petId}")
                .then()
                .statusCode(200)
                .extract().response().asString();

        JsonPath jsonPath = from(response);
        assertThat(jsonPath.getString("name")).isEqualTo("Fido");
        assertThat(jsonPath.getString("status")).isEqualTo("available");

        // Validate JSON schema
        given()
                .body(response)
                .then()
                .body(matchesJsonSchemaInClasspath("jsonSchema/petSchema.json"));
    }

    @Test
    @DisplayName("Update pet")
    @Tag("pet")
    public void testUpdatePet() {
        Pet pet = new Pet();
        pet.setId(100);
        pet.setName("Fido Updated");
        pet.setStatus("pending");

        String response = given()
                .header("Content-Type", "application/json")
                .body(pet)
                .put("/pet")
                .then()
                .statusCode(200)
                .extract().response().asString();

        JsonPath jsonPath = from(response);
        assertThat(jsonPath.getString("name")).isEqualTo("Fido Updated");
        assertThat(jsonPath.getString("status")).isEqualTo("pending");

        // Validate JSON schema
        given().
                header("accept", "application/json").
                queryParam("status", "available").
                when().
                get("/pet/findByStatus").
                then().
                assertThat().
                body(matchesJsonSchemaInClasspath("jsonSchema/petSchema.json"));

    }

    @Test
    @DisplayName("Find pets by status")
    @Tag("pet")
    void testFindPetsByStatus() {
        @SuppressWarnings("unchecked")
        List<Pet> pets = given().
                header("accept", "application/json").
                queryParam("status", "available").
                when().
                get("/pet/findByStatus").
                then().extract().as(new TypeRef<List<Pet>>() {
                });
        assertThat(pets).hasSizeGreaterThan(100);
        assertThat(pets).isNotEmpty();
        assertThat(pets.get(0).getName()).isEqualTo("fish");

        // Validate JSON schema
        given().
                header("accept", "application/json").
                queryParam("status", "available").
                when().
                get("/pet/findByStatus").
                then().
                assertThat().
                body(matchesJsonSchemaInClasspath("jsonSchema/petSchema.json"));
    }

    @Test
    @DisplayName("Delete pet")
    @Tag("pet")
    public void testDeletePet() {
        given()
                .pathParam("petId", 100)
                .delete("/pet/{petId}")
                .then()
                .statusCode(200);
    }


    // Store Endpoint Tests with JsonPath

    @Test
    @DisplayName("Get store inventory")
    @Tag("store")
    public void testGetInventory() {
        String response = given()
                .get("/store/inventory")
                .then()
                .statusCode(200)
                .extract().response().asString();

        JsonPath jsonPath = from(response);
        Map<String, Integer> inventory = jsonPath.getObject("$", Map.class);
        MatcherAssert.assertThat(inventory.size(), Matchers.greaterThanOrEqualTo(60));
    }


    @Test
    @DisplayName("Place an order")
    @Tag("store")
    public void testPlaceOrder() {
        Order order = new Order(100, 100, 100, "2022-01-01T12:00:00.000Z", "placed", false);

        String response = given()
                .header("Content-Type", "application/json")
                .body(order)
                .post("/store/order")
                .then().log().all()
                .statusCode(200)
                .extract().response().asString();

        JsonPath jsonPath = from(response);
        Order placedOrder = jsonPath.getObject("$", Order.class);
        MatcherAssert.assertThat(placedOrder.getId(), Matchers.equalTo(100L));
        MatcherAssert.assertThat(placedOrder.getPetId(), Matchers.equalTo(100L));
        MatcherAssert.assertThat(placedOrder.getQuantity(), Matchers.equalTo(100L));
        MatcherAssert.assertThat(placedOrder.getStatus(), Matchers.equalTo("placed"));

        // Validate JSON schema
        given()
                .pathParam("orderId", 100)
                .get("/store/order/{orderId}")
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("jsonSchema/orderSchema.json"));
    }

    @Test
    @DisplayName("Get order by ID")
    @Tag("store")
    public void testGetOrderById() {
        String response = given()
                .pathParam("orderId", 100)
                .get("/store/order/{orderId}")
                .then()
                .statusCode(200)
                .extract().response().asString();

        JsonPath jsonPath = from(response);
        Order order = jsonPath.getObject("$", Order.class);
        MatcherAssert.assertThat(order.getId(), Matchers.equalTo(100L));
        MatcherAssert.assertThat(order.getPetId(), Matchers.equalTo(100L));
        MatcherAssert.assertThat(order.getQuantity(), Matchers.equalTo(100L));
        MatcherAssert.assertThat(order.getStatus(), Matchers.equalTo("placed"));

        // Validate JSON schema
        given()
                .pathParam("orderId", 100)
                .get("/store/order/{orderId}")
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("jsonSchema/orderSchema.json"));
    }

    @Test
    @DisplayName("Delete order by ID")
    @Tag("store")
    public void testDeleteOrder() {
        given()
                .pathParam("orderId", 300)
                .delete("/store/order/{orderId}")
                .then()
                .statusCode(200);
    }
}
