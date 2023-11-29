package tests;

import models.SingleUserResponseModel;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.LoginSpecs.requestSpec;
import static specs.LoginSpecs.responseSpec;

public class SingleUserTest {

    @Test
    void getSingleUserTest() {
        SingleUserResponseModel response = step("Отправка запроса", () ->
        given(requestSpec)
                .when()
                .get("/users/2")
                .then()
                .spec(responseSpec)
                .statusCode(200)
                .extract().as(SingleUserResponseModel.class));

        step("Проверка ответа", () -> {
            assertEquals(2, response.getData().getId());
        });
    }
}
//