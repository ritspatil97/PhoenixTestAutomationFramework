package com.api.test;

import com.api.constant.Role;
import com.api.pojo.*;
import com.api.utils.SpecUtil;
import io.restassured.RestAssured;

import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class CreateJobAPITest {

    @Test
    public void createJobAPiTest() {

        Customer customer = new Customer("Rushi", "P", "1234567890", "1234567890", "abc@yopmail.com", "abcd@yopamil.com");
        CustomerAddress customerAddress = new CustomerAddress("1", "abc", "redwood", "towerC", "landmark", "123456", "India", "Maharashtra");
        CustomerProduct customerProduct = new CustomerProduct("2025-08-31T18:30:00.000Z", "15093795311179", "15093795311179", "15093795311179", "2025-08-31T18:30:00.000Z", 1, 1);
        Problems problems = new Problems(1, "battery Issue");
        List<Problems> problemList = new ArrayList<Problems>();
        problemList.add(problems);

        CreateJobPayload createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemList);

        RestAssured.given()
                .spec(SpecUtil.requestSpecWithAuth(Role.FD, createJobPayload))
                .when()
                .post("/job/create")
                .then()
                .spec(SpecUtil.responseSpec_OK())
                .body(matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
                .body("message", equalTo("Job created successfully. "))
                .body("data.mst_service_location_id",equalTo(1))
                .body("data.job_number",startsWith("JOB_"));
    }
}
