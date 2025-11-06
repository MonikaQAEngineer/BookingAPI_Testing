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
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        response = RestAssured.get("/booking" + "/" + bookingId);
    }

    @Then("response status code should be 200")
    public void response_status_code_should_be_200() {
        Assert.assertEquals("Status code should match", response.getStatusCode(), 200);

    }

    @Then("response status code should be 404")
    public void response_status_code_should_be_404() {
        Assert.assertEquals("Status code should match", response.getStatusCode(), 404);

    }

    @Then("the response body should contain the all booking details")
    public void the_response_body_should_contain_the_all_booking_details() {
        String resp = response.asString();
        Assert.assertTrue(resp.contains("firstname"));
        Assert.assertTrue(resp.contains("lastname"));
        Assert.assertTrue(resp.contains("totalprice"));
        Assert.assertTrue(resp.contains("depositpaid"));
        Assert.assertTrue(resp.contains("bookingdates"));
        Assert.assertTrue(resp.contains("checkin"));
        Assert.assertTrue(resp.contains("checkout"));
        // {"firstname":"Susan","lastname":"Ericsson","totalprice":126,"depositpaid":true,
        // "bookingdates":{"checkin":"2017-09-04","checkout":"2021-08-26"}}
    }

    @Then("response body should be {string}")
    public void response_body_should_be(String Not_Found) {
        String actualBody = response.getBody().asString().trim();
        actualBody.contains(Not_Found);
    }

}
