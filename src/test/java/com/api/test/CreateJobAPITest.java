package com.api.test;

import com.api.constant.*;
import com.api.requestModel.*;
import static com.api.utils.DateTimeUtility.*;
import static com.api.utils.SpecUtil.*;
import static io.restassured.RestAssured.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class CreateJobAPITest {

    @Test
    public void createJobAPiTest() {

        Customer customer = new Customer("Rushi", "Patil", "1234567890", "1234567890", "abc@yopmail.com", "abcd@yopamil.com");
        CustomerAddress customerAddress = new CustomerAddress("1", "abc", "redwood", "towerC", "landmark", "123456", "India", "Maharashtra");
        CustomerProduct customerProduct = new CustomerProduct(getTimeWithDaysAge(10), "15093795311109", "15093795311109", "15093795311109", getTimeWithDaysAge(10),
                Product.NEXUS_2.getCode(), Model.NEXUS_2_BLUE.getCode());
        Problems problems = new Problems(Problem.SMARTPHONE_IS_RUNNING_SLOW.getCode(), "battery Issue");
        List<Problems> problemList = new ArrayList<Problems>();
        problemList.add(problems);

        CreateJobPayload createJobPayload = new CreateJobPayload(ServiceLocation.SERVICE_LOCATION_A.getCode(), PlatformID.FRONT_DESK.getCode(), WarrentyStatus.IN_WAARENTY.getCode(),
                OEM.GOOGLE.getCode(), customer, customerAddress, customerProduct, problemList);

        given()
                .spec(requestSpecWithAuth(Role.FD, createJobPayload))
                .when()
                .post("/job/create")
                .then()
                .spec(responseSpec_OK())
                .body(matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
                .body("message", equalTo("Job created successfully. "))
                .body("data.mst_service_location_id",equalTo(1))
                .body("data.job_number",startsWith("JOB_"));
    }
}
