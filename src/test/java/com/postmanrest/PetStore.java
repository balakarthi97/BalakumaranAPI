package com.postmanrest;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

public class PetStore {
	
	public static void main(String[] args) {
		
		RestAssured.baseURI = "https://petstore.swagger.io/v2";

			String response = given().log().all().header("Content-Type", "application/json").body(

			"{\r\n"
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
			+ "}"
			)
	       .when().post("/pet").then().log().all().assertThat()
	       .statusCode(200).body( "id", equalTo(1506)).extract().response().asString();

			System.out.println("end") ;
			System.out.println(response) ;

			JsonPath js = new JsonPath(response) ;
			int id = js.get("id");
			System. out.println(id) ;


			int id1 = js.get("category.id");
			System.out.println(id1);

			int id2 = js.get("tags[0].id");
			System.out.println(id2) ;

			String getResponse = given().log().all().pathParam("id", id).header("Content-Type", "application/json")
			.when().get("/pet/{id}")
			.then().assertThat().statusCode(200).extract().response().asString();

			 

			JsonPath js1 = new JsonPath(getResponse);
			int newid = js1.get("id");
			System. out. println(newid) ;

			Assert.assertEquals(id, newid);
		
	}

}
