package VerifyApiThroughDynamicJson;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Files.JsonReusableMethods;
import Files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class BookDetailsThroughDynamicJson {
	
	public String id;
	
	@Test(dataProvider="BooksDetails")
	public void addBook(String isbn, String aisle) {
		
		RestAssured.baseURI = "http://216.10.245.166";
		
		String response = given().log().all().header("Content-Type", "application/json")
		.body(Payload.AddBook(isbn,aisle))
		.when().post("Library/Addbook.php")
		.then().log().all()
		.assertThat().statusCode(200)
		.extract().response().asString();
		
		JsonPath js = JsonReusableMethods.rawToJson(response);
		
		id =js.get("ID");
		
		String msg =js.get("Msg");
		
		System.out.println("Book ID: " + id);
		System.out.println("Book added message: " + msg);
		
		getBookDetails(id);
		
		deleteBook(id);
		
	}
	
//  @Test	
//	public void addBook() {
//		
//		RestAssured.baseURI = "http://216.10.245.166";
//		
//		String response = given().log().all().header("Content-Type", "application/json")
//		.body(Payload.AddBook("adsefc","65874"))
//		.when().post("Library/Addbook.php")
//		.then().log().all()
//		.assertThat().statusCode(200)
//		.extract().response().asString();
//		
//		JsonPath js = JsonReusableMethods.rawToJson(response);
//		String id =js.get("ID");
//		String msg =js.get("Msg");
//		
//		System.out.println("Book ID: " + id);
//		System.out.println("Book added message: " + msg);
//		
//	}
	
	@DataProvider(name="BooksDetails")
	public Object[][] getData() {
		
		return new Object[][] {{"adsefav","9527"}, {"adsefad","9258"}, {"adsefaa","9429"}};
	}
	
	//Get the book details
	public void getBookDetails(String id) {
	
		String resp = given().log().all().queryParam("ID", id)
			.when().get("Library/GetBook.php")
			.then().log().all().assertThat().statusCode(200)
			.extract().response().asString();
			
	System.out.println("Print the book details: " + resp);
	
	
	
	}
	
	//Delete the book
	public void deleteBook(String id) {
		
		given().log().all().header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"ID\" : \""+id+"\"\r\n"
				+ "}")
		.when().delete("Library/DeleteBook.php")
		.then().log().all().assertThat().statusCode(200).body("msg", equalTo("book is successfully deleted"));
		
	}

}
