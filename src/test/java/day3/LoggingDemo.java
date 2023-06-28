package day3;
import org.testng.annotations.Test;
import groovy.util.logging.Log;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class LoggingDemo {
	
	@Test
	void log()
	{
		given()
		.when()
			.get("https://reqres.in/api/users?page=2")
		.then()
		//.log().body();
		//.log().cookies();
		//.log().headers();
		.log().all();
	}

}
