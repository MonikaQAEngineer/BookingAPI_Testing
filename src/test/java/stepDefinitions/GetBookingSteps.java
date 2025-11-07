package stepDefinitions;

import java.util.Map;
import org.junit.Assert;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetBookingSteps {

    Map<String, Object> requestBody;
    Response response;

    @When("I send a GET request to the booking endpoint with booking id {int}")
    public void I_send_a_GET_request_to_the_booking_endpoint_with_booking_id(int bookingId) {
        response = RestAssured.get("/booking/" + bookingId);
        CommonSteps.setResponse(response);
    }

    @Then("the response body should contain a valid booking details")
    public void the_response_body_should_contain_the_all_booking_details() {
        String resp = response.asString();
        Assert.assertTrue(resp.contains("firstname"));
        Assert.assertTrue(resp.contains("lastname"));
        Assert.assertTrue(resp.contains("totalprice"));
        Assert.assertTrue(resp.contains("depositpaid"));
        Assert.assertTrue(resp.contains("bookingdates"));
        Assert.assertTrue(resp.contains("checkin"));
        Assert.assertTrue(resp.contains("checkout"));
    }

}