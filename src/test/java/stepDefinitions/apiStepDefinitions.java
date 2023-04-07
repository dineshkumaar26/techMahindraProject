package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;


public class apiStepDefinitions  {

	private Response response;

	@Given("I have the base URI {string}")
	public void i_have_the_base_uri(String baseURI) {
		RestAssured.baseURI = baseURI;
	}

	@When("I send a GET request to {string}")
	public void i_send_a_get_request_to(String endpoint) {
		response = RestAssured.given()
				.when()
				.get(endpoint);
	}
	@Then("the response status code should be {int}")
	public void the_response_status_code_should_be(Integer statusCode) {
		assertThat(response.getStatusCode(), equalTo(statusCode));
		System.out.println("API response is ...."+response.asPrettyString());
	}
	
	@Given("make the post request {string}{string}{string}{string}{int}")
	public void make_the_post_request(String baseuri, String endpoint, String name, String job, Integer StatusCode) {
		String id;
		RestAssured.baseURI =baseuri; 
		RequestSpecification request = RestAssured.given(); 
		JSONObject requestParams = new JSONObject();
		requestParams.put("userName", name);
		requestParams.put("password", job); 
		request.body(requestParams.toString());
		Response response = request.post(endpoint); 
		ResponseBody body = response.getBody();
		JsonPath responseBody = response.getBody().jsonPath();

		assertThat(response.getStatusCode(), equalTo(StatusCode));

		System.out.println("API response is ...."+body.asPrettyString());
		System.out.println(response.getStatusLine());
		
	}
}
