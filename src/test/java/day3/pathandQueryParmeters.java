package day3;
import org.testng.annotations.Test;
import groovy.util.logging.Log;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;
import org.testng.annotations.Test;


public class pathandQueryParmeters {
	@Test
	void testQueryAndPathParameters()
	{
		given()
			.pathParam("mypath","users")    // path parameters
			.queryParam("page",2)  // query parameter
			.queryParam("id",5)  // query parameters
		
		.when()
			.get("https://reqres.in/api/{mypath}")
		
		.then()
			.statusCode(200)
			.log().all();
		
		
	}

	

}
