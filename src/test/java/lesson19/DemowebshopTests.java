package lesson19;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

    /** почему-то не делает logout на самом сайте */
    @Test
    void logOutTest() {
        String cookie = "E9B0B6B52B3A36800B67E65FB7A52C5DF5BE3A0EB23FE3D0400D29F649D3B7B2C70D8788E5DC819A276D78DB032D79E" +
                "A6F5527260BA0E3EBACF4E4572F993290FF4E0A16E67DB077542FF4728059E6071B14B4C69ACE68C70EA9A8A9D3B059F12F1CFC" +
                "BEC6032B8EFC86DB9A2F558B58E0C75827AC641AFD895B7C56B700DACE8DE41358B3CF54B64A1C3956C37BFCE3C4D94A9F21FFC587735E99B36DFAFB3C";


        given()
                .cookie("NOPCOMMERCE.AUTH", cookie)
                .when()
                .post("/logout")
                .then()
                .log().all()
                .statusCode(302);

    }

    @Test
    void changeAddressTest() {
        String cookies = "0049B1ACE10714805A5F84DA83767BD3F99473F5EB84C5F9666A4F43258B7F7D06937B12A7EB5689983619333" +
                "3D755CCC1AD25BD20E6E2F4B613BC15F0CF97099B9C617C6E14C17AB8654EBEAD875C0C2DD8318DA292CCFBF1621F12571" +
                "F7A8866F6D3CD301B4FB9EB08DF6FBD4DD36C2ABF9F5709638803F26E8B25E09CC5CB6640B1ABEB31E0CBEC5DAE4E15DEB6" +
                "5D7F8EFF4CDD7EBFA9FE77FDA1D86A58F4";


        Map<String, String> keys = new HashMap<>();
        keys.put("Address.Id", "2948265");
        keys.put("Address.FirstName", "ilia");
        keys.put("Address.LastName", "Andreev");
        keys.put("Address.Email", "ilia_19011991@mail.ru");
        keys.put("Address.Company:", "qaguru");
        keys.put("Address.CountryId:", "1");
        keys.put("Address.StateProvinceId:", "1");
        keys.put("Address.City:", "New Jersey");
        keys.put("Address.Address1:", "400-500 Springfield Ave");
        keys.put("Address.Address2:", "");
        keys.put("Address.ZipPostalCode:", "07110");
        keys.put("Address.PhoneNumber:", "+19738480700");
        keys.put("Address.FaxNumber:", "");

        Response response = given()
                .contentType("application/x-www-form-urlencoded")
                .cookie("NOPCOMMERCE.AUTH", cookies)
                .formParams(keys)
                .when()
                .post("/customer/addressedit/2948265")
                .then()
                //.log().all()
                .statusCode(200)
                .extract()
                .response();


        String value = response.htmlPath().getString("**.findAll{it.@class == 'address1'}");
        System.out.println(value);

        //assertEquals(value, "400-500+Springfield+Ave");


    }

}
