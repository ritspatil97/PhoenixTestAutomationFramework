package com.api.test;

import com.api.requestModel.UserCredentials;

import static com.api.utils.SpecUtil.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;

public class LogInAPITest {

    private UserCredentials userCredentials;

    @BeforeMethod(description = "Create the Payload for the login API")
    public void setup() {
        userCredentials = new UserCredentials("iamfd", "password");
    }

    @Test(description = "Verify if the login API is working", groups = {"api", "regression", "smoke"})
    public void loginAPITest() {


        given()
//                .baseUri(getProperty("BASE_URI"))
//                .and()
//                .contentType(JSON)
//                .accept(ANY)
                .spec(requestSpec(userCredentials))
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
                .spec(responseSpec_OK())
                .body("message", equalTo("Success"))
                .and()
                .body("data.token", notNullValue())
                .log().body()
                .and()
                .body(matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
    }
}
