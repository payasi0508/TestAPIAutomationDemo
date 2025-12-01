package VerifyApi;

import org.testng.Assert;
import org.testng.annotations.Test;

import Files.Payload;
import io.restassured.path.json.JsonPath;

public class MockSumValidation {
	
	//6. Verify if sum of all courses prices matches with purchase amount
	
	@Test
	public void sumOfCoursesPrice() {
		
		int sum = 0;
		JsonPath js = new JsonPath(Payload.CoursePrice());
		int count = js.getInt("courses.size()");
		for(int i=0; i < count; i++) {
			int price = js.get("courses["+i+"].price");
			
			int copies = js.get("courses["+i+"].copies");
			
			System.out.println("print the course price:- " + price +" => with respective copies:- " + copies);	
			
			int amount = price*copies;
			
			System.out.println("Total amount for copies of courses: " + amount);
			
			sum = sum + amount;
		}	
		
		System.out.println("total purches of sum : " + sum);
		
		int purchesAmount = js.getInt("dashboard.purchaseAmount");
		
		System.out.println("total purches of amount : " + purchesAmount);
		
		Assert.assertEquals(sum, purchesAmount);
	}
}
