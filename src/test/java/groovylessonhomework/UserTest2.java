package groovylessonhomework;

import groovylessonhomework.createuser.LambokCreateUserData;
import org.junit.jupiter.api.Test;

import static groovylesson.Specs.request;
import static groovylesson.Specs.resronseSpec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest2 {
    @Test
    void checkUserExistsTestWithGroovy() {
        given()
                .spec(request)
                .when()
                .get("/users?page=2")
                .then()
                .spec(resronseSpec)
                .log().body()
                .body("data.findAll{it.email =~/.*?@reqres.in/}.email.flatten()", hasItem("rachel.howell@reqres.in"));
    }

    /**
    только Specs добавил
     */
    @Test
    void deleteUsersWithSpecsTest() {
        given()
                .spec(request)
                .when()
                .delete("/users/2")
                .then()
                .log().status()
                .statusCode(204);
    }

    @Test
    void getSingleUserWithLambokTest() {
        LombokUserData2 data2 = given()
                .spec(request)
                .when()
                .get("/users/2")
                .then()
                .spec(resronseSpec)
                .log().status()
                .log().body()
                .extract().as(LombokUserData2.class);

        assertEquals(data2.getLambokUser().getId(),2);
        assertEquals(data2.getLambokUser().getEmail(), "janet.weaver@reqres.in");

    }

    /**
     * пока не понял, вернуться
     */
    @Test
    void createUserTest() {
        String createUser = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";

        given()
                .spec(request)
                .body(createUser)
                .when()
                .post("/users")
                .then()
                .log().body()
                .log().status()
                .statusCode(201)
                .body("findAll{it.name =~/.*?morpheus}.name.flatten()", hasItem("morpheus"));


       // assertEquals(data.getLambokCreateUser().getName(), "morpheus");
       // assertEquals(data.getLambokCreateUser().getJob(), "leader");

    }


}