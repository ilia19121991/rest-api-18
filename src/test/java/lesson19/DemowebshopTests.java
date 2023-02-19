package lesson19;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class DemowebshopTests {
    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://demowebshop.tricentis.com";
    }

    @Test
    void addToCartTest() {
        String cookieValue = "5D35131B13512C8B6A582B05221B384B58DCF4A106357248C1CBDC157A4D2066EF71700D3B25641AB52F329B81BCABFB" +
                "6425ADF32CB59BA426525D833430396ED755C72DF1693AD0831EFBA1A4B405E99E613A16C60710126782BB5125FD9BD2806C34C6CD68A" +
                "FE48E6378258FC710BDC834FF9B4D251FC65F26A2BB6C061521AF5A2359275CA380D2DD236660809B8DFA9A34A73383D79AEA70530824D99C84",
                body = "product_attribute_72_5_18=65" +
                        "&product_attribute_72_6_19=54" +
                        "&product_attribute_72_3_20=57" +
                        "&product_attribute_72_8_30=94" +
                        "&addtocart_72.EnteredQuantity=3";

        given()
//                .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .cookie("NOPCOMMERCE.AUTH", cookieValue) // лучше убрать в файлик
                .body(body)
                .when()
                .post("/addproducttocart/details/72/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"));

    }

    @Test
    void addToCartAnonymTest() {
        String body = "product_attribute_72_5_18=65" +
                        "&product_attribute_72_6_19=54" +
                        "&product_attribute_72_3_20=57" +
                        "&product_attribute_72_8_30=94" +
                        "&addtocart_72.EnteredQuantity=3";

        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .body(body)
                .when()
                .post("/addproducttocart/details/72/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"))
                .body("updatetopcartsectionhtml", is("(3)"));

    }

    /*@Test
    void changeLastNameTest() {
        given()
                .contentType("application/x-www-form-urlencoded")
                .body("__RequestVerificationToken:SlcUixp8r6rBL4emLQI3fUIzSmp4aE62u5fA96l2pNh89Ehs7AEGEiNCWlHQgBwh648CVUC4uqg4PXz5XAlLiUvNCukcCMPGYXIv5fhjSO3CURQPOANeN1Ny1sCSkM7J0\n" +
                        "Gender:M\n" +
                        "FirstName:ilia\n" +
                        "LastName:Sergeev\n" +
                        "Email:ilia_19011991@mail.ru\n" +
                        "save-info-button:Save")
                .when()
                .post("/customer/info")
                .then()
                .log().all()
                .statusCode(200)
                .body()
    }*/

}
