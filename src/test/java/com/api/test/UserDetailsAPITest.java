package com.api.test;

import static com.api.constant.Role.*;

import static com.api.utils.SpecUtil.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.*;

public class UserDetailsAPITest {

    @Test(description = "verify if the userDetails API response is shown correctly", groups = {"api", "regression", "smoke"})
    public void userDetailsAPITest() throws IOException {

        ///Header authHeader = new Header("Authorization", getToken(FD));
        given()
//       .baseUri(getProperty("BASE_URI")).and().header(authHeader).and().accept(JSON).log().uri().log().method().log().headers().log().body()
                .spec(requestSpecWithAuth(FD))
                .when()
                .get("userdetails")
                .then()
//                .log().all().statusCode(200).and().time(lessThan(1000l)).and()
                .spec(responseSpec_OK())
                .body(matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));
    }
}

