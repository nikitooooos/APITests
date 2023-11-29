package tests;

import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.LoginSpecs.requestSpec;
import static specs.LoginSpecs.responseSpec;

public class SingleUserNotFoundTest {

    @Test
    void getSingleUserNotFoundTest() {
        step("Ответ 404", () ->
        given(requestSpec)
                .when()
                .get("/unknown/23")
                .then()
                .spec(responseSpec)
                .statusCode(404));
    }
}