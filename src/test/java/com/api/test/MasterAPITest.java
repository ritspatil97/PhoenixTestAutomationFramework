package com.api.test;

import static io.restassured.RestAssured.*;

import static com.api.utils.ConfigManager.*;

import static com.api.constant.Role.*;
import static com.api.utils.AuthTokenProvider.*;
import static io.restassured.http.ContentType.*;

import static org.hamcrest.Matchers.*;

import com.api.constant.Role;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

public class MasterAPITest {
    @Test
    public void masterAPITest(){

        /// Issue in the API - this is a post request but still don't have a body
         given()
                .baseUri(getProperty("BASE_URI"))
                .and()
                .header("Authorization", getToken(FD))
                .and()
                .contentType("") /// empty content type
                 .log().uri()
                 .log().headers()
                 .log().method()
         .when()
                .post("master")
         .then()
                 .log().all()
                 .statusCode(200)
                 .time(lessThan(3000l))
                 .body("message", equalTo("Success"))
                 .body("$",hasKey("message"))
                 .body("$",hasKey("data"))
                 .body("data", notNullValue())
                 .body("data",hasKey("mst_oem"))
                 .body("data",hasKey("mst_model"))
                 .body("data.mst_oem.size()",greaterThan(1))   ///  size of JSON array with matchers
                 .body("data.mst_model.size()",greaterThan(0))
                 .body("data.mst_oem.id",everyItem(notNullValue()))
                 .body("data.mst_oem.name",everyItem(notNullValue()))
                 .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/MasterAPIResponseSchema.json"));

    }

    @Test
    public void invalidTokenMasterAPITest(){
        given().baseUri(getProperty("BASE_URI"))
                .header("Authorization","")
                .and()
                .contentType("")
                .and()
                .when()
                .post("master")
                .then()
                .log().all()
                .statusCode(401)
                .time(lessThan(1000l));
    }
}
