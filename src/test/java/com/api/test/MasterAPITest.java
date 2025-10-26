package com.api.test;

import static io.restassured.RestAssured.*;

import static com.api.utils.ConfigManager.*;

import static com.api.constant.Role.*;
import static com.api.utils.AuthTokenProvider.*;
import static io.restassured.http.ContentType.*;

import static org.hamcrest.Matchers.*;

import com.api.constant.Role;

import static com.api.utils.SpecUtil.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

import org.testng.annotations.Test;

public class MasterAPITest {
    @Test(description = "Verify the master API is correct response", groups = {"api", "regression", "smoke"})
    public void masterAPITest() {

        /// Issue in the API - this is a post request but still don't have a body
        given()
//                .baseUri(getProperty("BASE_URI"))
//                .and()
//                .header("Authorization", getToken(FD))
//                .and()
//                .contentType("") /// empty content type
//                 .log().uri()
//                 .log().headers()
//                 .log().method()
                //                .spec(SpecUtil.requestSpecWithAuth(FD))
                .spec(requestSpecWithAuth(FD))

                .when()
                .post("master")
                .then()
//                 .log().all()
//                 .statusCode(200)
//                 .time(lessThan(3000l))
                .spec(responseSpec_OK())
                .body("message", equalTo("Success"))
                .body("$", hasKey("message"))
                .body("$", hasKey("data"))
                .body("data", notNullValue())
                .body("data", hasKey("mst_oem"))
                .body("data", hasKey("mst_model"))
                .body("data.mst_oem.size()", greaterThan(1))   ///  size of JSON array with matchers
                .body("data.mst_model.size()", greaterThan(0))
                .body("data.mst_oem.id", everyItem(notNullValue()))
                .body("data.mst_oem.name", everyItem(notNullValue()))
                .body(matchesJsonSchemaInClasspath("response-schema/MasterAPIResponseSchema.json"));

    }

    @Test(description = "Verify the master API is correct response for Invalid token", groups = {"api", "negative", "regression", "smoke"})
    public void invalidTokenMasterAPITest() {
        given()
//                .baseUri(getProperty("BASE_URI"))
//                .header("Authorization","")
//                .and()
//                .contentType("")
//                .and()
                .spec(requestSpec())
                .when()
                .post("master")
                .then()
//                .log().all()
//                .statusCode(401)
//                .time(lessThan(1000l));
                .spec(responseSpec_TEXT(401));
    }
}
