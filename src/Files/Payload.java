package Files;

public class Payload {

	public static String AddPlace() {
		return "{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \"Frontline kamal test house\",\r\n"
				+ "  \"phone_number\": \"(+91) 888 037 8402\",\r\n"
				+ "  \"address\": \"29, mico layout, cohen 09\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://rahulshettyacademy.com\",\r\n"
				+ "  \"language\": \"Demand-IN\"\r\n"
				+ "}";
	}
	
	public static String CoursePrice() {
		return "{\r\n"
				+ " \"dashboard\":{\r\n"
				+ "	\"purchaseAmount\":4650,\r\n"
				+ "	\"website\": \"kamaltest.com\"\r\n"
				+ " },\r\n"
				+ " \"courses\":[\r\n"
				+ " { \r\n"
				+ "	\"title\": \"Selenium Python\",\r\n"
				+ "	\"price\": 50,\r\n"
				+ "	\"copies\": 6\r\n"
				+ " },\r\n"
				+ "  { \r\n"
				+ "	\"title\": \"Cypress\",\r\n"
				+ "	\"price\": 40,\r\n"
				+ "	\"copies\": 12\r\n"
				+ " },\r\n"
				+ "  { \r\n"
				+ "	\"title\": \"RPA\",\r\n"
				+ "	\"price\": 45,\r\n"
				+ "	\"copies\": 10\r\n"
				+ "  },\r\n"
				+ "   { \r\n"
				+ "	\"title\": \"Appium\",\r\n"
				+ "	\"price\": 95,\r\n"
				+ "	\"copies\": 36\r\n"
				+ "  }\r\n"
				+ " ]\r\n"
				+ "}";	
	}
	
	public static String AddBook(String isbn, String aisle) {
		
		String addBookPayload = "{\r\n"
				+ "\"name\":\"Learn Appium Automation with Java test\",\r\n"
				+ "\"isbn\":\""+isbn+"\",\r\n"
				+ "\"aisle\":\""+aisle+"\",\r\n"
				+ "\"author\":\"John foe test686 test\"\r\n"
				+ "}";
		
		return addBookPayload;
	}
}
