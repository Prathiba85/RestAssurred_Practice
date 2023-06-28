package day3;
import org.testng.annotations.Test;
import groovy.util.logging.Log;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

public class Headersdemo {
	@Test
	void testCookies()
	{
		//https://www.facebook.com/	
		given()
		.when()
			.get("https://www.google.com")
		.then()
			.header("Content-Type","text/html; charset=ISO-8859-1")
			.and()//It is not mandatory. you can leave 
			.header("Server","gws")
			.log().all();
	}
	
	
	@Test(priority =2)
	void getHeader()
	{
		
		Response res = given()
		.when()
			.get("https://www.google.com");
		//get single cookie info
		String Header_Content_Type= res.getHeader("Content-Type");
		System.out.println("The value of Content Type header is "+Header_Content_Type);
		
		//get all cookie
		
		Headers myheaders = res.getHeaders();
		
		for(Header hd:myheaders)
		{
			
			System.out.println(hd.getName()+":  "+hd.getValue());
		}
		
			
	}


}
