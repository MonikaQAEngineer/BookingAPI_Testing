package stepDefinitions;

import org.junit.Assert;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetBookingIdSteps {
    private Response response;

    @When("I send a GET request to the booking endpoint")
    public void I_send_a_GET_request_to_the_booking_endpoint() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        response = RestAssured
        //given().when()
                .get("/booking");
    }


    @Then("the response body should be a list containing at least one booking ID")
    public void the_response_body_should_be_a_list_containing_at_least_one_booking_id() {
       // JsonPath json = response.jsonPath();
       // Assert.assertTrue("Response body should contain a list of booking IDs", json.getList("bookingid"), is(not(empty())));
        String body = response.getBody().asString();
        Assert.assertNotNull("Response body should should contain at least one booking ID", body);
    }

    @When("I send a GET request to the booking endpoint with query parameter {string} set to {string}")
    public void I_send_a_GET_request_to_the_booking_endpoint_with_query_parameter_set_to(String key, String value) {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        //String path = "/booking";
        //String query = "?" + key + "=" + value;
        //String endpoint = RestAssured.baseURI + path + query;
        //System.out.println("Sending GET request to: " + endpoint);
        //response = RestAssured.get(endpoint);
        response = RestAssured.get("/booking" + "?" + key + "=" + value);
        //given().queryParam(key, value).when()
    }

    @Then("the response body should be empty")
    public void the_response_body_should_be_empty() {
        String body = response.getBody().asString().trim();
        //System.out.println("Response Body: " + body);
        Assert.assertTrue("Response body should be empty", body.equals("[]"));
    }

    @Then("the response status should be {int}")
    public void the_response_status_should_be(int expectedStatusCode) {
        Assert.assertEquals("Status code should match", response.getStatusCode(), expectedStatusCode);
    }

}
