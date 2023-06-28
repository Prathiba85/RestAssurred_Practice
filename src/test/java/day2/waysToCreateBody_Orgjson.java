package day2;

import org.json.JSONObject;
import org.testng.annotations.Test;
import groovy.util.logging.Log;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import java.util.HashMap;
//Hashmap
//Org.Json
//Pojoclass
//json files

public class waysToCreateBody_Orgjson {

	// 1) Post request body using Org.Json
	static int id;

	@Test(priority = 1)
	void testPostusingOrgjson() {

		JSONObject data = new JSONObject();
		data.put("name", "Scott");
		data.put("location", "France");
		data.put("phone", "0433008145");
		String courseArr[] = { "c", "c++" };
		data.put("courses", courseArr);

		id= given().contentType("application/json").body(data.toString())
		.when().post("http://localhost:3000/students").jsonPath().getInt("id");
		System.out.println("ID is "+id);
	}

	@Test(priority = 2)
	void validatePost() {
		given().when().then().statusCode(201).body("name", equalTo("Scott")).body("location", equalTo("France"))
				.body("phone", equalTo("0433008145")).body("courses[0]", equalTo("c"))
				.body("courses[1]", equalTo("c++")).header("Content-Type", "application/json; charset=utf-8").log()
				.all();

	}

	@Test(priority =3)
	void deleteRecord() {

		given()
		.when()
		   .delete("http://localhost:3000/students/"+id)
		.then()
			.statusCode(200)
			.log().all();
	}
}
