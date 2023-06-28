package day5;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
public class ParsingXMLResonseData {
	
	@Test
	void testxmlResponse()
	{
		//Approach 1
		given()
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler?page=1")
		.then()
			.statusCode(200)
			.header("Content-Type","application/xml; charset=utf-8")
			.body("TravelerinformationResponse.page",equalTo("1"));
			//.body("TravelerinformationResponse.travelers.Travelerinformation[0].name", equalTo("Vijay Bharath Reddy"));	
	}
	
	@Test
	void testxmlResponse2()
	{
		//Approach 2
		Response res ;
		res = given()
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler?page=1");
		
		Assert.assertEquals(res.getStatusCode(), 200);
		String actual_content = res.header("Content-Type");
		Assert.assertEquals(actual_content, "application/xml; charset=utf-8");
		String actual_pagenumber=res.xmlPath().get("TravelerinformationResponse.page").toString();
		Assert.assertEquals(actual_pagenumber,"1");
		//String actual_name = res.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[0].name").toString();
		
	}
	//Approach -3
	@Test
	void testxmlResponse3()
	{
		//Approach 1
		Response res ;
		res = given()
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler?page=1");
		
	XmlPath xmlobj = new XmlPath(res.asString());
	List <String> travellers = xmlobj.getList("TravelerinformationResponse.travelers.Travelerinformation");
	
	// 1. Validate the size
	
	Assert.assertEquals(travellers.size(), 10);
	
	//Validate the name
	List <String> travellers_name = xmlobj.getList("TravelerinformationResponse.travelers.Travelerinformation.name");
	
	Boolean nameflag=false;
	
	for(String name:travellers_name)
	{
		//System.out.println(name);
		if(name.equals("vano"))
		{
			nameflag = true;
			break;
		}
		
	}
	
	Assert.assertEquals(nameflag, true);
		
	}

}
