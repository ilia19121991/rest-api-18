package tests;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SelenoidTests {
    /*
    1. Make request to https://selenoid.autotests.cloud/status
    2. Get response {"total": 20, "used": 1, "queued": 0, "pending": 0, "browsers": { "android": { "8.1": { } },
    "chrome": { "": {  "user1": { "count": 1, "sessions": [ { "id": "9509bb4168e23858531cb996252fa099",  "container": "4b9910ba0d0f1a81a08ed93de1b0e10ec8579aa33e92be211fc377a4c02be3f7", "containerInfo": { "id": "4b9910ba0d0f1a81a08ed93de1b0e10ec8579aa33e92be211fc377a4c02be3f7", "ip": "172.18.0.4" }, "vnc": true, "screen": "1920x1080x24", "caps": { "browserName": "chrome", "screenResolution": "1920x1080x24", "enableVNC": true, "enableVideo": true, "videoName": "selenoid68e68ab375cef6337a18eba38e75b43e.mp4", "videoScreenSize": "1920x1080" }, "started": "2023-01-29T17:23:17.422825769Z" } ] }  },  "100.0": { }, "99.0": { } }, "chrome-mobile": { "86.0": { } }, "firefox": { "97.0": { }, "98.0": { }  },
    "opera": { "84.0": { }, "85.0": { } }  } }
    3. Check total is 20
     */

    @Test
    void checkTotal() {
        get("https://selenoid.autotests.cloud/status")
                .then()
                .body("total", is(20));
    }

    @Test
    void checkTotalWithStatus() {
        get("https://selenoid.autotests.cloud/status")
                .then()
                .statusCode(200)
                .body("total", is(20));
    }

    @Test
    void checkTotalWithGiven() {
        given()
                .when()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .statusCode(200)
                .body("total", is(20));
    }

    @Test
    void checkTotalWithLogs() {
        given()
                .log().all()
                .when()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .log().all()
                .statusCode(200)
                .body("total", is(20));
    }

    @Test
    void checkTotalWithSomeLogs() {
        given()
                .log().uri()
                .when()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("total", is(20));
    }

    @Test
    void checkChromeVersion() {
        given()
                .log().uri()
                .when()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("browsers.chrome", hasKey("100.0"));
    }

    /**
     * Этот вариант не лучше, чем то, что выше. Себе зафиксировал, чтобы знать, что можно присвоить переменной результат запроса
     */
    @Test
    void checkResponseGoodPractice() {
        Integer expectedTotal = 20;

        Integer actualTotal = given()
                .log().uri()
                .when()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().path("total");

        assertEquals(expectedTotal, actualTotal);
    }


    /*
    1. Make request to https://selenoid.autotests.cloud/wd/hub/status
    2. Get response { "value": { "message": "Selenoid 1.10.7 built at 2021-11-21_05:46:32AM", "ready": true } }
    3. Check total "value.ready" is true
     */
    @Test
    void checkVdHubStatus401() {
        given()
                .log().uri()
                .when()
                .get("https://selenoid.autotests.cloud/wd/hub/status")
                .then()
                .log().status()
                .log().body()
                .statusCode(401);
    }

    @Test
    void checkVdHubStatus() {
        given()
                .log().uri()
                .when()
                .get("https://user1:1234@selenoid.autotests.cloud/wd/hub/status")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("value.ready", is(true));
    }

    @Test
    void checkVdHubWithAuthStatus() {
        given()
                .log().uri()
                .auth().basic("user1", "1234")
                .when()
                .get("https://selenoid.autotests.cloud/wd/hub/status")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("value.ready", is(true));
    }
}