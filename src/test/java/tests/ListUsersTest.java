package tests;

import models.ListUsersResponseModel;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.LoginSpecs.requestSpec;
import static specs.LoginSpecs.responseSpec;

public class ListUsersTest {

    @Test
    void getListUsersTest() {
        ListUsersResponseModel response = step("Отправка запроса", () ->
                given(requestSpec)
                        .when()
                        .get("/users?page=2")
                        .then()
                        .spec(responseSpec)
                        .statusCode(200)
                        .extract().as(ListUsersResponseModel.class));

        step("Проверка ответа", () -> {
            assertEquals(12, response.getTotal());
        });
    }
}