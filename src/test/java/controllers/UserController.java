package controllers;

import configurations.TestConfig;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.User;

public class UserController {

    private TestConfig testConfig;
    private RequestSpecification requestSpecification;

    public UserController() {
        RestAssured.defaultParser = Parser.JSON;
        testConfig = new TestConfig();
        requestSpecification = RestAssured.given().baseUri(testConfig.getBaseUrl());
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.accept(ContentType.JSON);
    }

    public Response createUser(User user) {
        return requestSpecification.body(user).post("user");
    }

    public Response getUserByUsername(String username) {
        return requestSpecification.get("user/" + username);
    }

    public Response updateUser(User user) {
        return requestSpecification.body(user).put("user/" + user.getUsername());
    }

    public Response deleteUser(String username) {
        return requestSpecification.delete("user/" + username);
    }
}