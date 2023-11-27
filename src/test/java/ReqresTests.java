import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class ReqresTests {
    String url = new String("https://reqres.in/api");

    @Test
    void getListUsersTest() {
        given()
                .log().uri()
                .log().method()
                .log().body()
                .when()
                .get(url + "/users?page=2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("total", is(12));

    }

    @Test
    void getSingleUserTest() {
        given()
                .log().uri()
                .log().method()
                .log().body()
                .when()
                .get(url + "/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.id", is(2));
    }

    @Test
    void getSingleUserNotFoundTest() {
        given()
                .log().uri()
                .log().method()
                .log().body()
                .when()
                .get(url + "/unknown/23")
                .then()
                .log().status()
                .log().body()
                .statusCode(404);
    }

    @Test
    void createTest() {
        String create = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";

        given()
                .log().uri()
                .log().method()
                .log().body()
                .body(create)
                .contentType(JSON)
                .when()
                .post(url + "/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus"));
    }

    @Test
    void deleteTest() {
        given()
                .log().uri()
                .log().method()
                .when()
                .delete(url + "/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(204);
    }
}