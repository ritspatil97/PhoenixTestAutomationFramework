package com.api.test;

import static com.api.constant.Role.*;
import static io.restassured.RestAssured.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static org.hamcrest.Matchers.*;

import com.api.utils.SpecUtil;
import org.testng.annotations.Test;

public class CountAPITest {
    @Test(description = "Verify if the Count API is working", groups = {"api", "regression", "smoke"})
    public void verifyCountAPIResponse() {

        given()
//                .baseUri(getProperty("BASE_URI"))
//                .and()
//                .header("Authorization", getToken(FD))
//                .and()
//                .contentType(JSON)
//                .log().uri()
//                .log().method()
//                .log().headers()
                .spec(SpecUtil.requestSpecWithAuth(FD))
                .when()
                .get("dashboard/count")
                .then()
//                .statusCode(200)
//                .time(lessThan(3000l))
//                .log().body()
                .spec(SpecUtil.responseSpec_OK())
                .body("message", equalTo("Success"))
                .body("data", notNullValue())
                .body("data.size()", equalTo(3))
                .body("data.count", everyItem(greaterThanOrEqualTo(0)))
///                .body("data.label",Matchers.everyItem(Matchers.not(Matchers.blankOrNullString())))
                .body("data.label", everyItem(not(blankOrNullString())))
                .body("data.key", everyItem(not(blankOrNullString())))
                .body("data.key", containsInAnyOrder("pending_for_delivery", "created_today", "pending_fst_assignment"))
                .body(matchesJsonSchemaInClasspath("response-schema/CountAPIResponseSchema-FD.json"))
        ///.log().all()
        ///  .log().ifValidationFails()

        ;
    }

    @Test(description = "Verify if the Count API is giving 401 status code when no token passed", groups = {"api", "negative", "regression", "smoke"})
    public void countAPiTest_MissingAuthToken() {
        given()
//                .baseUri(getProperty("BASE_URI"))
//                .and()
//                .contentType(JSON)
//                .log().uri()
//                .log().method()
//                .log().headers()
                .spec(SpecUtil.requestSpec())
                .when()
                .get("dashboard/count")
                .then()
//                .log().all()
//                .statusCode(401);
                .spec(SpecUtil.responseSpec_TEXT(401));
    }

}
