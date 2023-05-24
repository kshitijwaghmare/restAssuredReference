package restAssuredReference;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.given;
import java.time.LocalDateTime;

import org.testng.Assert;

public class postReference {

	public static void main(String[] args) {

		String baseURI= "https://reqres.in";
		
		String requestBody = "{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"leader\"\r\n"
				+ "}";
		
		JsonPath jsprequestBody = new JsonPath(requestBody);
		String req_name = jsprequestBody.getString("name");
		String req_job = jsprequestBody.getString("job");
		LocalDateTime Date = LocalDateTime.now();
		String exp_date = Date.toString().substring(0,10);
		System.out.println(exp_date);

		RestAssured.baseURI = baseURI;
		
		int statuscode = given().header("Content-Type","application/json").body(requestBody).when().post("/api/users").then().extract().statusCode();
		System.out.println(statuscode);
		String responseBody = given().header("Content-Type","application/json").body(requestBody).when().post("/api/users").then().extract().response().asString();

		JsonPath jspresponse = new JsonPath(responseBody);
		String res_name = jspresponse.getString("name");
		String res_job = jspresponse.getString("job");
		String res_id = jspresponse.getString("id");
		String res_createdAt = jspresponse.getString("createdAt");
		
		res_createdAt = res_createdAt.substring(0,10);
	
		
		Assert.assertEquals(req_name,res_name);
		Assert.assertEquals(req_job,res_job);
		Assert.assertNotNull(res_id);
		Assert.assertEquals(exp_date,res_createdAt);
	
	}
	
}


