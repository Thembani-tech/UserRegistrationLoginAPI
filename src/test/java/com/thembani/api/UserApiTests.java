package com.thembani.api;

import io.javalin.Javalin;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

class UserApiTests {

    private static Javalin app;

    @BeforeAll
    static void startServer() {
        app = Main.createApp();
        app.start(0); // 0 = let the OS pick a free port
        RestAssured.baseURI = "http://localhost:" + app.port();
    }

    @AfterAll
    static void stopServer() {
        app.stop();
    }

    @BeforeEach
    void resetState() {
        Main.resetUsers();
    }

    private String loadJson(String filename) throws IOException {
        return Files.readString(Path.of(filename));
    }

    @Test
    @DisplayName("Registering a new user succeeds")
    void registerNewUserSucceeds() throws IOException {
        Response response = given()
                .contentType("application/json")
                .body(loadJson("register-request.json"))
                .when()
                .post("/register");

        assertThat(response.statusCode()).isEqualTo(201);
    }

    @Test
    @DisplayName("Registering a duplicate username fails")
    void registerDuplicateUsernameFails() throws IOException {
        given().contentType("application/json").body(loadJson("register-request.json")).post("/register");

        Response response = given()
                .contentType("application/json")
                .body(loadJson("register-duplicate.json"))
                .when()
                .post("/register");

        assertThat(response.statusCode()).isEqualTo(409);
    }

    @Test
    @DisplayName("Registering without a username fails")
    void registerMissingUsernameFails() throws IOException {
        Response response = given()
                .contentType("application/json")
                .body(loadJson("register-missing-username.json"))
                .when()
                .post("/register");

        assertThat(response.statusCode()).isEqualTo(400);
    }

    @Test
    @DisplayName("Registering without a password fails")
    void registerMissingPasswordFails() throws IOException {
        Response response = given()
                .contentType("application/json")
                .body(loadJson("register-missing-password.json"))
                .when()
                .post("/register");

        assertThat(response.statusCode()).isEqualTo(400);
    }

    @Test
    @DisplayName("Logging in with correct credentials succeeds")
    void loginSucceeds() throws IOException {
        given().contentType("application/json").body(loadJson("register-request.json")).post("/register");

        Response response = given()
                .contentType("application/json")
                .body(loadJson("register-request.json"))
                .when()
                .post("/login");

        assertThat(response.statusCode()).isEqualTo(200);
    }

    @Test
    @DisplayName("Logging in with the wrong password fails")
    void loginWrongPasswordFails() throws IOException {
        given().contentType("application/json").body(loadJson("register-request.json")).post("/register");

        Response response = given()
                .contentType("application/json")
                .body(loadJson("login-wrong-password.json"))
                .when()
                .post("/login");

        assertThat(response.statusCode()).isEqualTo(401);
    }

    @Test
    @DisplayName("Logging in as an unknown user fails")
    void loginUnknownUserFails() throws IOException {
        Response response = given()
                .contentType("application/json")
                .body(loadJson("login-unknown-user.json"))
                .when()
                .post("/login");

        assertThat(response.statusCode()).isEqualTo(401);
    }
}