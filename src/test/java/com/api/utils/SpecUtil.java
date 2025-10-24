package com.api.utils;

import com.api.constant.Role;
import com.api.pojo.UserCredentials;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;

import static com.api.utils.ConfigManager.*;

public class SpecUtil {

    /// ---------     for Get--Delete request
    public static RequestSpecification requestSpec(){
        // To take care of the common request sections (methods)
        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setBaseUri(getProperty("BASE_URI"))
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .log(LogDetail.URI)
                .log(LogDetail.METHOD)
                .log(LogDetail.HEADERS)
                .log(LogDetail.BODY)
                .build();
        return requestSpecification;
    }

    /// ---------  for POST - PUT - PATCH {Body} request
    ///public static RequestSpecification requestSpec(UserCredentials userCredentials){
     public static RequestSpecification requestSpec(Object payload){
        // To take care of the common request sections (methods)
        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setBaseUri(getProperty("BASE_URI"))
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .setBody(payload)
                .log(LogDetail.URI)
                .log(LogDetail.METHOD)
                .log(LogDetail.HEADERS)
                .log(LogDetail.BODY)
                .build();
        return requestSpecification;
    }

    public static RequestSpecification requestSpecWithAuth(Role role){
        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setBaseUri(getProperty("BASE_URI"))
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .addHeader("Authorization",AuthTokenProvider.getToken(role))
                .log(LogDetail.URI)
                .log(LogDetail.METHOD)
                .log(LogDetail.HEADERS)
                .log(LogDetail.BODY)
                .build();
        return requestSpecification;
    }

    public static ResponseSpecification responseSpec_OK(){
        ResponseSpecification responseSpecification = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusCode(200)
                .expectResponseTime(Matchers.lessThan(3000l))
                .log(LogDetail.ALL)
                .build();
        return responseSpecification;
    }

    public static ResponseSpecification responseSpec_JSON(int statusCode){
        ResponseSpecification responseSpecification = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusCode(statusCode)
                .expectResponseTime(Matchers.lessThan(3000l))
                .log(LogDetail.ALL)
                .build();
        return responseSpecification;
    }
    public static ResponseSpecification responseSpec_TEXT(int statusCode){
        ResponseSpecification responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .expectResponseTime(Matchers.lessThan(3000l))
                .log(LogDetail.ALL)
                .build();
        return responseSpecification;
    }
}
