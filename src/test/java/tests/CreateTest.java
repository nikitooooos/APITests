package tests;

import models.CreateResponseTestModel;
import models.CreateTestModel;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.LoginSpecs.requestSpec;
import static specs.LoginSpecs.responseSpec;

public class CreateTest {

    @Test
    void createTest() {
        CreateTestModel authBody = new CreateTestModel();
        authBody.setName("morpheus");
        authBody.setJob("leader");

        CreateResponseTestModel response = step("Создание пользователя", () ->
            given(requestSpec)
                    .body(authBody)
                    .when()
                    .post("/users")
                    .then()
                    .spec(responseSpec)
                    .statusCode(201)
                    .extract().as(CreateResponseTestModel.class));

        step("Проверка ответа", () ->
                assertEquals("morpheus", response.getName()));
    }
}