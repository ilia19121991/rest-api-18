package specs;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static specs.Specs.request;
import static specs.Specs.resronseSpec;

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
        // @formatter:ob
    }
}
