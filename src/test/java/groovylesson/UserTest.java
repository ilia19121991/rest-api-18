package groovylesson;

import groovylesson.lombok.LombokUserData;
import groovylesson.models.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static groovylesson.Specs.request;
import static groovylesson.Specs.resronseSpec;

public class UserTest {
    @Test
    void singleUser() {
       // @formatter:off
        given()
                .spec(request)
                .when()
                .get("/users/2")
                .then()
                .spec(resronseSpec)
                .log().body();
        // @formatter:on
    }

    @Test
    void singleUserWithModel() {
        // @formatter:off
       UserData data = given()
                .spec(request)
                .when()
                .get("/users/2")
                .then()
                .spec(resronseSpec)
                .log().body()
                .extract().as(UserData.class);
        // @formatter:on
        assertEquals(2, data.getData().getId());
    }

    @Test
    void singleUserWithLombokModel() {

        LombokUserData data = given()
                .spec(request)
                .when()
                .get("/users/2")
                .then()
                .spec(resronseSpec)
                .log().body()
                .extract().as(LombokUserData.class);
        // @formatter:on
        Assertions.assertEquals(2, data.getUser().getId());
        // @formatter:off
    }


}
