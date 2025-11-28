package VerifyApi;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import Files.JsonReusableMethods;
import Files.Payload;

public class AddPlaceInMap {

	public static void main(String[] args) {
		
		//Validate if add place API is working as expected
		
		//given - all input fields
		//when - submit the API - resource, http method
		//Then - validate the response
		//Add place -> update place with new Address -> get place to validate if new address is present in the response
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(Payload.AddPlace())
		.when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200)
		.body("scope", equalTo("APP")).header("Server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
		
		System.out.println(response);
		
		//Take the json and parse the json to get the value
		
		//JsonPath js = new JsonPath(response); // for parsing json
		
		JsonPath js = JsonReusableMethods.rawToJson(response);
		
		String placeId = js.getString("place_id");
		
		System.out.println("place id fron json: " + placeId);
		
		
		//Update place
		
		String newAddress = "570 main cross road BTM, Bangalore, INDIA";
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeId+"\",\r\n"
				+ "\"address\":\""+newAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}")
		.when().put("maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		//Get Place
		
		String getPlaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
		.when().get("maps/api/place/get/json")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		
		//JsonPath js1 = new JsonPath(getPlaceResponse);
		JsonPath js1= JsonReusableMethods.rawToJson(getPlaceResponse);
		
		String actualAddress = js1.getString("address");
		
		System.out.println("Actual address : " + actualAddress);
		
		Assert.assertEquals(newAddress, actualAddress);
		
		// Delete place id
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeId+"\"\r\n"
				+ "}")
		.when().delete("maps/api/place/delete/json")
		.then().log().all().assertThat().statusCode(200).body("status", equalTo("OK"));
	}

}
