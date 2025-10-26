package com.api.test;

import com.api.requestModel.UserCredentials;

import com.api.utils.SpecUtil;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;

public class LogInAPITest {

    @Test
    public void loginAPITest() {

        UserCredentials userCredentials = new UserCredentials("iamfd","password");

        given()
//                .baseUri(getProperty("BASE_URI"))
//                .and()
//                .contentType(JSON)
//                .accept(ANY)
                .spec(SpecUtil.requestSpec(userCredentials))
                .and()
       //         .body(userCredentials)
//                .log().uri()
//                .log().method()
//                .log().headers()
//                .log().body()
        .when()
                .post("login")
        .then()
//                .log().all()
//                .statusCode(200)
//                .and()
//                .time(lessThan(4000l))
//                .and()
                .spec(SpecUtil.responseSpec_OK())
                .body("message", equalTo("Success"))
                .and()
                .body("data.token",notNullValue())
                .log().body()
                .and()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
    }
}
