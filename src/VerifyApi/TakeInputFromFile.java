package VerifyApi;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


import Files.JsonReusableMethods;

public class TakeInputFromFile {

	public static void main(String[] args) throws IOException {


	//Validate if add place API is working as expected
		
		//given - all input fields
		//when - submit the API - resource, http method
		//Then - validate the response
		//content of the file to string -> content of file can convert into byte -> Byte data to string
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(new String(Files.readAllBytes(Paths.get("D:\\MyPythonProject\\TestFiles\\addPlace.json"))))
		.when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200)
		.body("scope", equalTo("APP")).header("Server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
		
		System.out.println(response);
		
		JsonPath js = JsonReusableMethods.rawToJson(response);
		
		String placeId = js.getString("place_id");
		
		System.out.println("place id fron json: " + placeId);
		
		// Delete place id
		
				given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
				.body("{\r\n"
						+ "\"place_id\":\""+placeId+"\"\r\n"
						+ "}")
				.when().delete("maps/api/place/delete/json")
				.then().log().all().assertThat().statusCode(200).body("status", equalTo("OK"));
	}

}
