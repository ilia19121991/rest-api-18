package lesson20.tests;

import lesson20.models.lombok.LoginBodyLombokModel;
import lesson20.models.lombok.LoginResponseLombokModel;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static lesson20.specs.LoginSpecs.*;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("reqres")
public class ReqresInHomeworkTests {


    @Test
    void loginTest() {
        LoginBodyLombokModel data = new LoginBodyLombokModel();
        data.setEmail("eve.holt@reqres.in");
        data.setPassword("cityslicka");

        LoginResponseLombokModel response = given(loginRequestSpec)
                .body(data)
                .when()
                .post("/login")
                .then()
                .spec(loginResponseSpec)
                .extract().as(LoginResponseLombokModel.class);

       assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");

    }


    @Test
    void unSupportedMediaTypeTest() {

        given(unSupportedMediaTypeRequestSpec)
                .when()
                .post("/login")
                .then()
                .spec(unSupportedMediaTypeResponseSpec);
    }


    @Test
    void missingEmailOrUsernameTest() {
        LoginBodyLombokModel data = new LoginBodyLombokModel();
        data.setEmail("1234567@gmail.com");

        LoginResponseLombokModel response = given(missingEmailOrUsernameRequestSpec)
                .body(data)
                .when()
                .post("/login")
                .then()
                .spec(missingEmailOrUsernameResponseSpec)
                .extract().as(LoginResponseLombokModel.class);

        assertThat(response.getError()).isEqualTo("Missing email or username");
    }


    @Test
    void missingPasswordTest() {
        LoginBodyLombokModel data = new LoginBodyLombokModel();
        data.setEmail("eve.holt@reqres.in");

        LoginResponseLombokModel response = given(loginRequestSpec)
                .body(data)
                .when()
                .post("/login")
                .then()
                .spec(missingEmailOrUsernameResponseSpec)
                .extract().as(LoginResponseLombokModel.class);

        assertThat(response.getError()).isEqualTo("Missing password");

    }

}
