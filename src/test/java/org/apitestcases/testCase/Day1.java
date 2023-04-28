package org.apitestcases.testCase;


//GET
//POST
//PUT
//DELETE

//given()- content-Type, authorization, add query param, set header

//when() - method type - put, post, get, delete

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

//then() - validate - assert on the response which will get generated
public class Day1 {


    @Test (priority = 1)
    void getUser()
    {
        given()

                .when()
                .get("https://reqres.in/api/users?page=2")

                .then()
                .statusCode(200)
                .body("page",equalTo(2))
                .body("total_pages",equalTo(2))
                .log().all();

    }

    int id;
    @Test (priority = 2)
    void createUser()
    {

        //body - {
        //    "name": "morpheus",
        //    "job": "leader"
        //}
        HashMap data = new HashMap();
        data.put("name","Harafit");
        data.put("job","QA");

        id  = given()
                .contentType("application/json")
                .body(data)

                .when()
                .post("https://reqres.in/api/users")
                .jsonPath().getInt("id");



               // .then()
               // .statusCode(201)
               // .log().all();

    }

    @Test (dependsOnMethods = {"createUser"})
    void updateUser()
    {
        HashMap data = new HashMap();
        data.put("name","Harafit");
        data.put("job","QA Engineer");

        given()
                .contentType("application/json")
                .body(data)
                .when()
                .put("https://reqres.in/api/users/"+id)

                .then()
                .statusCode(200)
                .log().all();

    }

    @Test (dependsOnMethods = {"createUser","updateUser"})
    void deleteUser()

    {
        given()

                .when()
                .delete("https://reqres.in/api/users/"+id)
                .then()
                .statusCode(204)
                .log().all();
    }

}
