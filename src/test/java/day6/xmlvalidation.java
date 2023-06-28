package day6;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


import org.testng.annotations.Test;

import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;

public class xmlvalidation {
	@Test
	void jsonschemavalidation()
	{
		given()
		
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler")
		.then()
			.assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("traveler.xsd"));
		
	}

}
