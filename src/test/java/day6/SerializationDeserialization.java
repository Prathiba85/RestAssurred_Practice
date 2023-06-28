package day6;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import day2.pojoclass;

public class SerializationDeserialization {
	//Pojo class ---> Json Object --- de-Serialization ----->pojo
	//converting java object to JSON object is serialization
	
	@Test
	void convertoPojo2Json() throws JsonProcessingException
	{
		//Created java object using pojo class
		Student stupojo=new Student();    //pojo
		
		stupojo.setName("Scott");
		stupojo.setLocation("France");
		stupojo.setPhone("123456");
		String coursesArr[]= {"C","C++"};
		stupojo.setCourses(coursesArr);
		
		//Convert java object ---> json object (serialization) by using object mapper class. 
		//you have to import the ObjectMapper from fasterxml.jackson.databind.ObjectMapper;
		
		ObjectMapper objMapper=new ObjectMapper();
		String jsondata=objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(stupojo);
		System.out.println(jsondata);

	}
	
	// Json -----> Pojo    ( De-Serilization)
			@Test
			void convertJson2Pojo() throws JsonProcessingException
			{
				// You copy paste the output of previous class here then java will automatically format it as below
				
				String jsondata="{\r\n"
						+ "  \"name\" : \"Scott\",\r\n"
						+ "  \"location\" : \"France\",\r\n"
						+ "  \"phone\" : \"123456\",\r\n"
						+ "  \"courses\" : [ \"C\", \"C++\" ]\r\n"
						+ "}";
				
				
				//convert json data---> Pojo object
				
				ObjectMapper objMapper=new ObjectMapper();
				
				
				Student stupojo=objMapper.readValue(jsondata,Student.class); //convert json to pojo
				
				System.out.println("Name:"+stupojo.getName());
				System.out.println("Location:"+stupojo.getLocation());
				System.out.println("Phone:"+stupojo.getPhone());
				System.out.println("Course 1:"+stupojo.getCourses()[0]);
				System.out.println("Course2:"+stupojo.getCourses()[1]);	
				
				
			}
			


}
