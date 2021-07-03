package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;


public class PUT_Or_Update_A_Product {

	@Test
	public void update_A_Product() {
		//https://techfios.com/api-prod/api/product/create.php	
		
//		String payloadPath = ".\\src\\main\\java\\data\\updatePayload.json";
		String payloadPath = "src/main/java/data/updatePayload.json";
		
		Response response = 
				given()
					.log().all()
					.baseUri("https://techfios.com/api-prod/api/product")
					.header("Content-Type","application/json;")
					.body(new File(payloadPath))
				.when()
					.put("/update.php")
				.then()
					.log().all()
					.extract().response();
		
		int statusCode = response.getStatusCode();
		System.out.println("Status code: "+ statusCode);
		Assert.assertEquals(statusCode, 200);
		
		long responseTime = response.getTimeIn(TimeUnit.MILLISECONDS);
		System.out.println("Response time: "+ responseTime);
		if(responseTime<=3600) {
			System.out.println("Response time is within range.");
		}else {
			System.out.println("Response time is out of range.");
		}
		
		String responseBody = response.getBody().asString();
		System.out.println("Response body: " + responseBody);
			
		JsonPath jp = new JsonPath(responseBody);
		
		String successMessage = jp.getString("message");
		System.out.println("successMessage: "+ successMessage);
		Assert.assertEquals(successMessage, "Product was updated.");
	}
	
	
	
}
