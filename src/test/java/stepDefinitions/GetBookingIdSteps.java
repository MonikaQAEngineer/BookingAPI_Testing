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
        response = RestAssured
                .get("/booking");
                CommonSteps.setResponse(response);
    }

    @Then("the response body should be a list containing at least one booking ID")
    public void the_response_body_should_be_a_list_containing_at_least_one_booking_id() {
        String body = response.getBody().asString();
        Assert.assertNotNull("Response body should should contain at least one booking ID", body);
    }

    @When("I send a GET request to the booking endpoint with query parameter {string} set to {string}")
    public void I_send_a_GET_request_to_the_booking_endpoint_with_query_parameter_set_to(String key, String value) {
        response = RestAssured.get("/booking" + "?" + key + "=" + value);
        CommonSteps.setResponse(response);
    }

    @Then("the response body should be empty")
    public void the_response_body_should_be_empty() {
        String body = response.getBody().asString().trim();
        Assert.assertTrue("Response body should be empty", body.equals("[]"));
    }
}