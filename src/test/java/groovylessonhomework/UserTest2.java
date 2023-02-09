package groovylessonhomework;

import org.junit.jupiter.api.Test;

import static groovylesson.Specs.request;
import static groovylesson.Specs.resronseSpec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
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
}