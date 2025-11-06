package stepDefinitions;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import org.junit.Assert;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import java.util.HashMap;
import java.util.Map;

public class UpdateBookingSteps {

    private Response response;
    private String token;
    private String updatedLastname;
    private int bookingId;

    private static final String VALID_TOKEN = "YWRtaW46cGFzc3dvcmQxMjM=";

    @Given("a booking exists with bookingID {int}")
    public void a_booking_exists_with_bookingID(Integer id) {
        this.bookingId = id;
    }

    @Given("I am an authenticated user with a valid token")
    public void i_am_an_authenticated_user_with_a_valid_token() {
        token = VALID_TOKEN;
    }

    @Given("I am not an authenticated user")
    public void i_am_not_an_authenticated_user() {
        token = null;
    }

    @Given("I have an invalid authentication token {string}")
    public void i_have_an_invalid_authentication_token(String invalidToken) {
        token = invalidToken;
    }

    @And("I have to update the lastname to {string}")
    public void i_have_to_update_the_lastname_to(String lastname) {
        this.updatedLastname = lastname;
    }

    @When("I send a PUT request to the booking endpoint with background booking ID")
    public void i_send_a_PUT_request_to_the_booking_endpoint_with_background_booking_ID() {
        sendPutRequest(bookingId);
    }

    @When("I send a PUT request to the booking endpoint with ID {int}")
    public void i_send_a_PUT_request_to_the_booking_endpoint_with_ID(int id) {
        sendPutRequest(id);
    }

    private void sendPutRequest(int id) {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";

        Map<String, Object> bookingDetails = new HashMap<>();
        bookingDetails.put("firstname", "John");
        bookingDetails.put("lastname", updatedLastname);
        bookingDetails.put("totalprice", 111);
        bookingDetails.put("depositpaid", true);

        Map<String, String> bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2024-01-01");
        bookingDates.put("checkout", "2024-01-02");
        bookingDetails.put("bookingdates", bookingDates);

        bookingDetails.put("additionalneeds", "Breakfast");

        // Build and send PUT request with or without authorization header
        if (token != null) {
            response = RestAssured
                    .given()
                    .contentType(ContentType.JSON)
                    .header("Authorization", "Basic " + token)
                    .body(bookingDetails)
                    .when()
                    .put("/booking/" + id);
        } else {
            response = RestAssured
                    .given()
                    .contentType(ContentType.JSON)
                    .body(bookingDetails)
                    .when()
                    .put("/booking/" + id);
        }
    }

    @Then("the response code should be {int}")
    public void the_response_code_should_be(Integer expectedStatusCode) {
        Assert.assertEquals("Status code should match",
                expectedStatusCode.intValue(), response.getStatusCode());
    }

    @And("the response body should show the lastname as {string}")
    public void the_response_body_should_show_the_lastname_as(String expectedLastname) {
        String actualLastname = response.jsonPath().getString("lastname");
        assertThat("Lastname should be updated correctly",
                actualLastname, equalTo(expectedLastname));
    }

    @And("the response body should be {string}")
    public void the_response_body_should_be(String expectedBody) {
        String body = response.getBody().asString().trim();
        Assert.assertEquals("Response body text should match", expectedBody, body);
    }
}
