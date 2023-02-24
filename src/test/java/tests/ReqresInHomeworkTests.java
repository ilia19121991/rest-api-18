package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

public class ReqresInHomeworkTests {

    @Test
    void checkUserExistsTest() {
        given()
                .log().uri()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().status()
                .log().body()
                .body("data.email", hasItem("rachel.howell@reqres.in"));

    }

    @Test
    void deleteUsersTest() {
        given()
                .log().uri()
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .statusCode(204);
    }

    @Test
    void getSingleUserTest() {
        given()
                .log().uri()
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .body("data.id", is(2))
                .body("data.email", is("janet.weaver@reqres.in"));

    }

    @Test
    void createUserTest() {
        String user = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";

        given()
                .log().uri()
                .contentType(JSON)
                .body(user)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().body()
                .log().status()
                .statusCode(201)
                .body("name", is("morpheus"), "job", is("leader"));
    }

    @Test
    void updateUserTest() {
        String updateUser = "{ \"name\": \"Andrey\", \"job\": \"QA\" }";

        given()
                .log().uri()
                .contentType(JSON)
                .body(updateUser)
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("Andrey"), "job", is("QA"));
    }
}
