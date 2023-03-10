package groovylessonhomework;

import groovylessonhomework.models.DataModel.LombokDataModel;
import groovylessonhomework.models.DataModel.LombokPostUser;
import groovylessonhomework.models.UserModel.LombokUser;
import groovylessonhomework.models.UserModel.LombokUserModel;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static groovylesson.Specs.request;
import static groovylesson.Specs.resronseSpec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("groovy_homework")
public class UserModelTest {
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
    void getSingleUserWithLombokTest() {
        LombokUser data = given()
                .spec(request)
                .when()
                .get("/users/2")
                .then()
                .spec(resronseSpec)
                .log().status()
                .log().body()
                .extract().as(LombokUser.class);

        assertEquals(data.getId(),2);
        assertEquals(data.getEmail(), "janet.weaver@reqres.in");

    }

   @Test
    void createUserWithLombokTest() {

       LombokDataModel data = new LombokDataModel();
       data.setName("morpheus");
       data.setJob("leader");


        LombokPostUser response = given()
                .spec(request)
                .body(data)
                .when()
                .post("/users")
                .then()
                .log().status()
                .log().body()
                .extract().as(LombokPostUser.class);

        assertThat(response.getName()).isEqualTo("morpheus");
        assertThat(response.getJob()).isEqualTo("leader");


    }

    @Test
    void updateUserWithLombokTest() {

        LombokDataModel data = new LombokDataModel();
        data.setName("Sergey");
        data.setJob("QA TeamLead");

        LombokPostUser response = given()
                .spec(request)
                .body(data)
                .when()
                .put("/users/2")
                .then()
                .log().status()
                .log().body()
                .extract().as(LombokPostUser.class);

        assertEquals(data.getName(), "Sergey");
        assertEquals(data.getJob(), "QA TeamLead");


    }

}
