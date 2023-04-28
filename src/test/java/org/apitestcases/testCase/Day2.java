package org.apitestcases.testCase;

import PojoClass.PojoData;
import net.minidev.json.parser.JSONParser;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Day2 {


    //1. Data - Payload - Post - Put -
    // - 1. HashMap - To Create The Payload
    // - 2. Using org.Json
    // - 3. Using POJO - Plain old java object
    // - 4. Using json

    PojoData dataPojo;
    // - 1. Creating payload with hashmap
    int id;
    @Test(priority = 2)
    void createUserUsingHashMap()
    {
        HashMap data = new HashMap();
        data.put("name","Harafit");
        data.put("job","QA");

        given()
                .contentType("application/json")
                .body(data)

                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("name",equalTo("Harafit"))
                .body("job",equalTo("QA"))
                .log().all();

    }

    @Test
    public void createUserUsingORGJSON()
    {
        JSONObject data = new JSONObject();
        data.put("name","Harafit Using ORG JSON");
        data.put("job","QA using ORG JSON");

        given()
                .contentType("application/json")
                .body(data)

                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .header("Content-Type","application/json; charset=utf-8")
                .body("map.name",equalTo("Harafit Using ORG JSON"))
                .body("map.job",equalTo("QA using ORG JSON"))
                .log().all();
    }

    @Test
    public void createUserUsingPOJO()
    {
        dataPojo = new PojoData();
        dataPojo.setName("Harafit Using POJO");
        dataPojo.setJob("QA Using POJO");

        given()
                .contentType("application/json")
                .body(dataPojo)

                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .header("Content-Type","application/json; charset=utf-8")
                .body("name",equalTo("Harafit Using POJO"))
                .body("job",equalTo("QA Using POJO"))
                .log().all();

        System.out.println(dataPojo.getJob());


    }

    @Test()
    public void createUsingJSONFile() {
       // System.out.println(dataPojo.getName());

        File file = new File("");
        FileReader fileReader;
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        JSONTokener jsonTokener = new JSONTokener(fileReader);

        JSONObject data = new JSONObject(jsonTokener);

        given()
                .contentType("application/json")
                .body(data)

                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .header("Content-Type","application/json; charset=utf-8")
                .body("map.name",equalTo("Harafit Using JSON External File"))
                .body("map.job",equalTo("QA Using JSON External File"))
                .log().all();
    }


}
