package com.api.test;

import com.api.pojo.UserCredentials;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

import static io.restassured.http.ContentType.*;

import static io.restassured.RestAssured.*;

public class LogInAPITest {

    @Test
    public void loginAPITest(){

        UserCredentials userCredentials = new UserCredentials("iamfd","password");

        given()
                .baseUri("http://64.227.160.186:9000/v1")
                .and()
                .contentType(JSON)
                .accept(ANY)
                .and()
                .body(userCredentials)
                .log().uri()
                .log().method()
                .log().headers()
                .log().body()
        .when()
                .post("login")
        .then()
                .log().all()
                .statusCode(200)
                .and()
                .time(lessThan(1000l))
                .and()
                .body("message", equalTo("Success"))
                .and()
                .body("data.token",notNullValue())
                .log().body()
                .and()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
    }
}
