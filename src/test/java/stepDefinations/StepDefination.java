package stepDefinations;

import static org.testng.AssertJUnit.*;
import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils {
	ResponseSpecification resspec;
	RequestSpecification res;
	Response response;
	TestDataBuild data = new TestDataBuild();
	static String place_id;
	
	
	
	@Given("Add Place Playload with {string} {string} {string}")
	public void add_place_playload_with(String name, String language,String address) throws IOException {
		
		res=given().spec(requestSpecification()).log().all()
		.body(data.addPlacePayload(name,language,address));
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_post_http_request(String resource,String method) {
		APIResources apiResource = APIResources.valueOf(resource);
		System.out.println(apiResource.getResource());
		resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if(method.equalsIgnoreCase("POST"))
			response =res.when().post(apiResource.getResource());
		else if(method.equalsIgnoreCase("GET"))
			response =res.when().get(apiResource.getResource());
	}

	@Then("the API call GOT success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
	    
	    assertEquals(response.getStatusCode(), 200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyvalue, String expectedValue) {
	
		assertEquals(getJsonPath(response, keyvalue), expectedValue);
		
	}
	
	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
	   
		place_id = getJsonPath(response, "place_id");
		res=given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_post_http_request(resource,"GET");
		String actualName = getJsonPath(response, "name");
		assertEquals(actualName, expectedName);
		System.out.println(actualName);
		
		//from demo 2
		System.out.println(actualName);
		System.out.println(actualName);
		System.out.println(actualName);
		
		//in develop branch
		System.out.println(actualName);
		
		// in develop demo2 branch
		
		
		
	}
	
	@Given("Delete place payload")
	public void delete_place_payload() throws IOException {
	    res = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
	}

}
