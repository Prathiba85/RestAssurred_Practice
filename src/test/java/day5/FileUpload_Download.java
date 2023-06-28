package day5;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class FileUpload_Download {
	@Test(priority =1)
	void singleFileUpload()
	{
		File myfile = new File("C:\\Users\\sanpr\\Documents\\Automation notes\\API-Testing\\Day-15-Parsing xml\\Test1.txt");
		
		given()
		.multiPart("file",myfile)
		.contentType("multipart/form-data")
	
	.when()
		.post("http://localhost:8080/uploadFile")
	
	.then()
		.statusCode(200)
		.body("fileName", equalTo("Test1.txt"))
		.log().all();

	}
@Test(priority =2)
	void multiFileUpload()
	{
		File myfile1 = new File("C:\\Users\\sanpr\\Documents\\Automation notes\\API-Testing\\Day-15-Parsing xml\\Test1.txt");
		File myfile2 = new File("C:\\Users\\sanpr\\Documents\\Automation notes\\API-Testing\\Day-15-Parsing xml\\Test2.txt");
		
		given()
		.multiPart("files",myfile1)
		.multiPart("files",myfile2)
		.contentType("multipart/form-data")
	
	.when()
		.post("http://localhost:8080/uploadMultipleFiles")
	
	.then()
		.statusCode(200)
		.body("[0].fileName", equalTo("Test1.txt"))
		.body("[1].fileName", equalTo("Test2.txt"))
		
		.log().all();

		

	}


@Test(priority =3)
void multipleFilesUpload2()   
{
	File myfile1=new File("C:\\AutomationPractice\\Test1.txt");
	File myfile2=new File("C:\\AutomationPractice\\Test2.txt");
	
	File filearr[]= {myfile1,myfile2};  // wont work for all kinds API
	
	given()
		.multiPart("files",filearr)
		.contentType("multipart/form-data")
	
	.when()
		.post("http://localhost:8080/uploadMultipleFiles")
	
	.then()
		.statusCode(200)
		.body("[0].fileName", equalTo("Test1.txt"))
		.body("[1].fileName", equalTo("Test2.txt"))
		
		.log().all();
				
	
}
@Test(priority=4)
void fileDownload()
{
	given()
	
	.when()
		.get("http://localhost:8080/downloadFile/Test1.txt")
	.then()
		.statusCode(200)
		.log().body();	
	}



}
