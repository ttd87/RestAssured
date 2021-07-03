package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;


public class GET_Or_Read_A_Product {

	@Test
	public void read_A_Product() {
		Response response = 
				given()
					.baseUri("https://techfios.com/api-prod/api/product")
					.header("Content-Type","application/json; charset=UTF-8")
					.queryParam("id", "1685")
				.when()
					.get("/read_one.php")
				.then().extract().response();
		
		int statusCode = response.getStatusCode();
		System.out.println("Status code: "+ statusCode);
		Assert.assertEquals(statusCode, 200);
		
		long responseTime = response.getTimeIn(TimeUnit.MILLISECONDS);
		System.out.println("Response time: "+ responseTime);
		if(responseTime<=2000) {
			System.out.println("Response time is within range.");
		}else {
			System.out.println("Response time is out of range.");
		}
		
		String responseBody = response.getBody().asString();
		
		System.out.println("Response body: " + responseBody);
			
		JsonPath jp = new JsonPath(responseBody);
		
		String productPrice = jp.getString("price");
		System.out.println("productPrice: "+ productPrice);
		Assert.assertEquals(productPrice, "199");
	}
	
	
	
}
