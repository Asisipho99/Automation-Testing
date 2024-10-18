package tests;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class PutPatchDeleteExamples {

    @Test
    public void testPut() {

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("name", "Raghav");
        jsonObject.put("job", "Teacher");

        System.out.println(jsonObject.toJSONString());

        baseURI = "https://reqres.in/api";

        given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(jsonObject.toJSONString())
                .when()
                .put("/users/2")
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    public void testPatch() {

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("name", "Raghav");
        jsonObject.put("job", "Teacher");

        System.out.println(jsonObject.toJSONString());

        baseURI = "https://reqres.in";

        given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(jsonObject.toJSONString())
                .when()
                .patch("/api/users/2")
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    public void testDelete() {



        baseURI = "https://reqres.in";

        given()
                .when()
                .delete("/api/users/2")
                .then()
                .statusCode(204)
                .log().all();
    }
}
