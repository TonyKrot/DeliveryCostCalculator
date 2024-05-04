import controllers.UserController;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.User;
import org.junit.jupiter.api.*;
import testdata.ApiTestData;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static testdata.ApiTestData.DEFAULT_USER;


public class ControllerApiTests {

    UserController userController = new UserController();

    @BeforeEach
    @AfterEach
    public void tearDown() {
        RestAssured.reset();
    }

    @Test
    @DisplayName("Create and check a new user")
    @Tag("user post")
    public void testCreateUser() {
        User user = ApiTestData.DEFAULT_USER;
        Response createUserResponse = userController.createUser(user);
        createUserResponse.then().statusCode(200);

        Response getUserResponse = userController.getUserByUsername(user.getUsername());
        getUserResponse.then().statusCode(200);

        User responseBody = getUserResponse.getBody().as(User.class);
        assertThat(responseBody).usingRecursiveComparison().ignoringFields("id").isEqualTo(user);
    }

    @Test
    @DisplayName("Get user by username")
    @Tag("user get")
    public void testGetUserByUsername() {
        String username = DEFAULT_USER.getUsername();
        Response response = userController.getUserByUsername(username);
        response.then().statusCode(200);
        response.then().body("username", equalTo(username));
    }

    @Test
    @DisplayName("Update an existing user")
    @Tag("user put")
    public void testUpdateUser() {
        User user = ApiTestData.DEFAULT_USER;
        user.setFirstName("username404");
        user.setEmail("123@gmail.com");
        user.setPassword("pswtest");
        Response response = userController.updateUser(user);
        response.then().statusCode(200);

        String updateUser = user.getUsername();
        Response getUserResponse = userController.getUserByUsername(updateUser);
        getUserResponse.then().statusCode(200);

        User responseBody = getUserResponse.getBody().as(User.class);
        assertThat(responseBody).usingRecursiveComparison().ignoringFields("id").isEqualTo(user);
    }

    @Test
    @DisplayName("Delete an existing user")
    @Tag("user delete")
    public void testDeleteUser() {
        String username = DEFAULT_USER.getUsername();
        Response response = userController.deleteUser(username);
        response.then().statusCode(200);
        response.then().body("message", equalTo("FPMI_user_1"));
    }

    @Test
    @DisplayName("Create user with invalid request")
    @Tag("user post invalid")
    public void testCreateUserInvalidRequest() {
        User user = new User();
        Response response = userController.createUser(user);
        response.then().log().all().statusCode(200);
    }

    @Test
    @DisplayName("Get user by username not found")
    @Tag("user get 404")
    public void testGetUserByUsernameNotFound() {
        String username = "userName404";
        Response response = userController.getUserByUsername(username);
        response.then().statusCode(404);
    }

    @Test
    @DisplayName("Update user not found")
    @Tag("user put 404")
    public void testUpdateUserNotFound() {
        User user = new User();
        Response response = userController.updateUser(user);
        response.then().statusCode(200);
    }

    @Test
    @DisplayName("Delete user not found")
    @Tag("user delete 404")
    public void testDeleteUserNotFound() {
        String username = "userName404";
        Response response = userController.deleteUser(username);
        response.then().statusCode(404);
    }
}