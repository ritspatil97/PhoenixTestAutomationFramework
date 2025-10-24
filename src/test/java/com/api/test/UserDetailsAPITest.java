package com.api.test;

import static com.api.constant.Role.*;

import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.*;

public class UserDetailsAPITest {

    @Test
    public void userDetailsAPITest() throws IOException {

        ///Header authHeader = new Header("Authorization", getToken(FD));
        given()
//       .baseUri(getProperty("BASE_URI")).and().header(authHeader).and().accept(JSON).log().uri().log().method().log().headers().log().body()
                .spec(SpecUtil.requestSpecWithAuth(FD))
        .when()
                .get("userdetails")
        .then()
//                .log().all().statusCode(200).and().time(lessThan(1000l)).and()
                .spec(SpecUtil.responseSpec_OK())
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));
    }
}

