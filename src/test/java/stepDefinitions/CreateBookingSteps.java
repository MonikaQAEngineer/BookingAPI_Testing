package stepDefinitions;

import java.util.HashMap;
import java.util.Map;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;

public class CreateBookingSteps {
    Map<String, Object> requestBody;
    Response response;

    @Given("I have booking data with {string} {string} {int} {string}")
    public void i_have_booking_data_with(String firstname, String lastname, Integer totalprice, String depositpaid) {
        requestBody = new HashMap<>();
        requestBody.put("firstname", firstname);
        requestBody.put("lastname", lastname);
        requestBody.put("totalprice", totalprice);
        requestBody.put("depositpaid", true);
        Map<String, String> bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2025-11-01");
        bookingDates.put("checkout", "2025-11-05");
        requestBody.put("bookingdates", bookingDates);
    }

    @When("I send a POST request to create a booking")
    public void i_send_a_post_request_to_create_a_booking() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post("/booking");
    }

    @Then("the response status code should be 200")
    public void the_response_status_code_should_be_200() {
        Assert.assertEquals("Status code should be 200", 200, response.getStatusCode());
    }

    @Then("the response body should contain {string} {string}")
    public void the_response_body_should_contain(String firstname, String lastname) {
        String resp = response.asString();
        Assert.assertTrue(resp.contains(firstname));
        Assert.assertTrue(resp.contains(lastname));
        Integer bookingid = response.jsonPath().getInt("bookingid");
        Assert.assertTrue("Booking ID should be greater than 0", bookingid > 0);
    }

    @Given("I have a booking data with only {string} {string}")
    public void I_have_a_booking_data_with_only(String lastname, String depositpaid) {
        requestBody = new HashMap<>();
        requestBody.put("lastname", lastname);
        requestBody.put("depositpaid", true);
    }

    @Then("the response status code should be 500")
    public void the_response_status_code_should_be_500() {
        Assert.assertEquals("Status code should be 500", 500, response.getStatusCode());
    }

    @Then("the response body should contain {string}")
    public void the_response_body_should_contain(String Internal_Server_Error) {
        String actualBody = response.getBody().asString().trim();
        actualBody.contains(Internal_Server_Error);
    }

}
