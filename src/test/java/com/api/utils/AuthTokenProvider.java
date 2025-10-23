package com.api.utils;

import static com.api.constant.Role.*;

import com.api.constant.Role;
import com.api.pojo.UserCredentials;

import static org.hamcrest.Matchers.*;

import static com.api.utils.ConfigManager.*;

import static io.restassured.http.ContentType.*;

import static io.restassured.RestAssured.*;

public class AuthTokenProvider {
    private AuthTokenProvider() {
    }

    public static String getToken(Role role) {

        UserCredentials userCredentials = null;

        if (role == FD) {
            userCredentials = new UserCredentials("iamfd", "password");
        } else if (role == SUP) {
            userCredentials = new UserCredentials("iamsup", "password");
        } else if (role == ENG) {
            userCredentials = new UserCredentials("iameng", "password");
        } else if (role == QC) {
            userCredentials = new UserCredentials("iamqc", "password");
        }

        String token =
                given().baseUri(getProperty("BASE_URI")).and().contentType(JSON).and().accept(ANY).and().body(userCredentials)
                        .and().log().uri().log().body().log().method().log().headers().when().post("login").then().log().ifValidationFails().statusCode(200).body("data.token", notNullValue())
                        .time(lessThan(3000l)).extract().body().jsonPath().getString("data.token");

        return token;
    }
}
