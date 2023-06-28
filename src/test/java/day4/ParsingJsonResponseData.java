package day4;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ParsingJsonResponseData {
	@Test(priority = 1)
	void testJSonresponse() {
		/*
		 * Approach -1 - This is recommended for small json 
		 * given()
		 * .contentType(ContentType.JSON) .when() .get("http://localhost:3000/store")
		 * .then() .statusCode(200)
		 * .header("Content-Type","application/json; charset=utf-8")
		 * .body("book[3].title",equalTo("The Lord of the Rings")) .log().all();
		 */

		/*
		 * Approach -2 - this approach is useful for complex json. Response res;
		 * res=given() .contentType(ContentType.JSON) .when()
		 * .get("http://localhost:3000/store"); //1. Assert Status Code int
		 * actual_statuscode = res.getStatusCode();
		 * Assert.assertEquals(actual_statuscode,200);//Validation 1
		 * 
		 * //2. Content Type Verificaiton String actual_contentType =
		 * res.getContentType();
		 * //Assert.assertEquals(actual_statuscode,"application/json; charset=utf-8");
		 * 
		 * //3.Json Path - This will be used to get any value from the response. String
		 * actual_Titile = (res.jsonPath().get("book[3].titile")).toString();
		 * 
		 * Assert.assertEquals(actual_Titile,"The Lord of the Rings");
		 */

	}

	@Test(priority = 3)

	void testJSonresponseBodyData() {
		// Approach -3
		// Requirement - We want to capture all the title and check if the particular
		// element is available or not
		// This method is very useful since the data is not always in the same order. It
		// gives more stability
		// 1. In this case we will read all the title and then search

		Response res;
		res = given().contentType(ContentType.JSON).when().get("http://localhost:3000/store");

		// 1. You have to convert the response to String format and then pause the
		// string in JSON object
		String response = res.asString();
		JSONObject jo = new JSONObject(response);

		// You have to find the length of array
		int n = jo.getJSONArray("book").length();
		boolean status = false;

		for (int i = 0; i < n; i++) {
			String bookTitle = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
			System.out.println("Book Titile " + bookTitle);

			if (bookTitle.equals("The Lord of the Rings")) {
				status = true;
				break;
			}

		}
		Assert.assertEquals(status, true);
		
		// Get Total price 
		double totalprice = 0;
		for (int i = 0; i < n; i++) {
			String bookPrice = jo.getJSONArray("book").getJSONObject(i).get("price").toString();
			double current_bookPrice = Double.parseDouble(bookPrice);
			 totalprice = totalprice+current_bookPrice;

			

		}
	}
}
