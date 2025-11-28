package VerifyApi;

import Files.Payload;
import io.restassured.path.json.JsonPath;

public class MockComplexJsonParse {

	public static void main(String[] args) {

		JsonPath js = new JsonPath(Payload.CoursePrice());
		
		//1. print no of courses return by API
		
		int count = js.getInt("courses.size()");
		System.out.println("count the number of courses: " + count);
		
		//2. Print purches amount
		
		int totalAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println("print the total amount of purches: " + totalAmount);
		
		//3. Print title of the first courses
		String titleFirstCourse = js.get("courses[2].title");
		System.out.println("count the number of coursesTitle of first course: " + titleFirstCourse);
		
		//4. print all courses titles and their respective prices
		
		for(int i=0; i<count; i++) {
			String coursesTitle = js.get("courses["+i+"].title");
			int coursePrice = js.get("courses["+i+"].price");
			System.out.println("print the course title:- " + coursesTitle +" => with respective price:- " + coursePrice);
		}
		
		//5. print no of copies sold by RPA courses
		for(int i=0; i<count; i++) {
			String coursesTitle = js.get("courses["+i+"].title");
			if(coursesTitle.equalsIgnoreCase("RPA")) {
				//copies sold
				System.out.println("Print the count of sell copies: " + js.get("courses["+i+"].copies").toString());
				break;
			}
			
		}
		
	}

}
