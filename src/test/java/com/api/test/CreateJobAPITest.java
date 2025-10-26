package com.api.test;

import com.api.constant.Role;
import com.api.pojo.*;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtil;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class CreateJobAPITest {

    @Test
    public void createJobAPiTest() {

        Customer customer = new Customer("Rushi", "P", "1234567890", "1234567890", "abc@yopmail.com", "abcd@yopamil.com");
        CustomerAddress customerAddress = new CustomerAddress("1", "abc", "redwood", "towerC", "landmark", "123456", "India", "Maharashtra");
        CustomerProduct customerProduct = new CustomerProduct("2025-08-31T18:30:00.000Z", "15093795311259", "15093795311259", "15093795311259", "2025-08-31T18:30:00.000Z", 1, 1);

        Problems[] problemsArray = new Problems[1];
        Problems problems = new Problems(1, "battery Issue");
        problemsArray[0] = problems;

        CreateJobPayload createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemsArray);

        RestAssured.given()
//                .baseUri(ConfigManager.getProperty("BASE_URI"))
//                .and()
//                .header("Authorization", AuthTokenProvider.getToken(Role.FD))
//                .contentType(ContentType.JSON)
//                /// ----- Java 15 feature -----
//                .body(createJobPayload)
                .spec(SpecUtil.requestSpecWithAuth(Role.FD, createJobPayload))
                .when()
                .post("/job/create")
                .then()
                .spec(SpecUtil.responseSpec_OK());

    }
}
