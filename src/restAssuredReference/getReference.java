package restAssuredReference;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

public class getReference {

    public static void main(String[] args) {

        // Step 1 :Declare BaseURL
        RestAssured.baseURI = "https://reqres.in/";

        // Step 2:Configure Response Body
        String responseBody = given().header("Content-Type","application/json").when().get("/api/users?page=2").then().extract().response().asString();
        int statusCode = given().header("Content-Type","application/json").when().get("/api/users?page=2").then().extract().statusCode();
      
        JsonPath jsonPath = new JsonPath(responseBody);
      
        int dataSize = jsonPath.getList("data").size();

        // Assert the total count of objects inside the data array
        Assert.assertEquals(dataSize, 6);

        // Validate each object in the data array
        for (int i = 0; i < dataSize; i++) {
            String id = jsonPath.getString("data[" + i + "].id");
            //System.out.println(id);
            String email = jsonPath.getString("data[" + i + "].email");
            //System.out.println(email);
            String firstName = jsonPath.getString("data[" + i + "].first_name");
            //System.out.println(firstName);
            String lastName = jsonPath.getString("data[" + i + "].last_name");
            //System.out.println(lastName);
            String avatar = jsonPath.getString("data[" + i + "].avatar");
            //System.out.println(avatar);

            Assert.assertNotNull(id);
            Assert.assertNotNull(email);
            Assert.assertNotNull(firstName);
            Assert.assertNotNull(lastName);
            Assert.assertNotNull(avatar);

            Assert.assertTrue(Integer.parseInt(id) >= 7 && Integer.parseInt(id) <= 12);
            Assert.assertTrue(email.contains("@reqres.in"));
        }
    }
}