package com.postmanrest;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.reporters.jq.Main;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class TestngDemo {
	
	@DataProvider(name ="status")
	public Object[][]getStatus(){
		return new Object[][] {{"sold"},{"available"},{"pending"}};
	}
	
	@BeforeClass
	public void Post(){
		RestAssured.baseURI ="https://petstore.swagger.io/v2";
		String response =given().log().all().header("Content-Type","application/json")
		.body("{\r\n"
				+ "  \"id\": 1506,\r\n"
				+ "  \"category\": {\r\n"
				+ "    \"id\": 65,\r\n"
				+ "    \"name\": \"Tiger\"\r\n"
				+ "  },\r\n"
				+ "  \"name\": \"Tiger New\",\r\n"
				+ "  \"photoUrls\": [\r\n"
				+ "    \"string\"\r\n"
				+ "  ],\r\n"
				+ "  \"tags\": [\r\n"
				+ "    {\r\n"
				+ "      \"id\": 66,\r\n"
				+ "      \"name\": \"Lion\"\r\n"
				+ "    }\r\n"
				+ "  ],\r\n"
				+ "  \"status\": \"available\"\r\n"
				+ "}").when().post("/pet").then().log().all().assertThat().statusCode(200).body("id",equalTo(155)).extract().response().asString();
	 System.out.println(response);
	}
	
@Test(priority = 1,dataProvider="status")
public void findpetstatus(String status){
	RestAssured.baseURI ="https://petstore.swagger.io/v2";
	String response = given().log().all().queryParam("status", status).header("Content-Type","application/json")
	.when().get("/pet/findByStatus")
	.then().assertThat().statusCode(200).extract().response().asString();
JsonPath js = new JsonPath(response);
String s = js.get("status[0]");
System.out.println(response);
System.out.println(s);}


@Test(priority = 2)
public void put() {
	RestAssured.baseURI ="https://petstore.swagger.io/v2";
	String response =given().log().all().header("Content-Type","application/json")
	.body("{\\r\\n\"\r\n"
			+ "			+ \"  \\\"id\\\": 1506,\\r\\n\"\r\n"
			+ "			+ \"  \\\"category\\\": {\\r\\n\"\r\n"
			+ "			+ \"    \\\"id\\\": 65,\\r\\n\"\r\n"
			+ "			+ \"    \\\"name\\\": \\\"Tiger\\\"\\r\\n\"\r\n"
			+ "			+ \"  },\\r\\n\"\r\n"
			+ "			+ \"  \\\"name\\\": \\\"Tiger New\\\",\\r\\n\"\r\n"
			+ "			+ \"  \\\"photoUrls\\\": [\\r\\n\"\r\n"
			+ "			+ \"    \\\"string\\\"\\r\\n\"\r\n"
			+ "			+ \"  ],\\r\\n\"\r\n"
			+ "			+ \"  \\\"tags\\\": [\\r\\n\"\r\n"
			+ "			+ \"    {\\r\\n\"\r\n"
			+ "			+ \"      \\\"id\\\": 66,\\r\\n\"\r\n"
			+ "			+ \"      \\\"name\\\": \\\"Tiger\\\"\\r\\n\"\r\n"
			+ "			+ \"    }\\r\\n\"\r\n"
			+ "			+ \"  ],\\r\\n\"\r\n"
			+ "			+ \"  \\\"status\\\": \\\"available\\\"\\r\\n\"\r\n"
			+ "			+ \"}").when().post("/pet").then().log().all().assertThat().statusCode(200).body("id",equalTo(155)).extract().response().asString();
 System.out.println(response);

}

@AfterClass
public void get(){
	RestAssured.baseURI ="https://petstore.swagger.io/v2";
	String response=given().header("Content-Type","application/json")
	.when().get("/pet/155")
	.then().assertThat().statusCode(200).extract().response().asString();
	JsonPath js =new JsonPath(response);
	int id = js.get("id");
	System.out.println(id);

System.out.println();

}
}
