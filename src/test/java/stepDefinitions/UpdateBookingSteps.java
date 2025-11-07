package stepDefinitions;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import org.junit.Assert;
import config.TestConfig;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import java.util.HashMap;
import java.util.Map;

public class UpdateBookingSteps {

    private Response response;
    private String token;
    private String updatedLastname;
    private int bookingId;
    private Map<String, Object> bookingData;

    @Given("I create a test booking for update")
    public void createTestBooking() {
        bookingData = createDefaultBookingPayload();
        response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(bookingData)
                .post("/booking");

        Assert.assertEquals(200, response.getStatusCode());
        bookingId = response.jsonPath().getInt("bookingid");
        System.out.println("Created booking with ID: " + bookingId);
    }

    private Map<String, Object> createDefaultBookingPayload() {
        Map<String, Object> booking = new HashMap<>();
        booking.put("firstname", "John");
        booking.put("lastname", "Doe");
        booking.put("totalprice", 100);
        booking.put("depositpaid", true);

        Map<String, String> bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2024-01-01");
        bookingDates.put("checkout", "2024-01-02");
        booking.put("bookingdates", bookingDates);

        booking.put("additionalneeds", "Breakfast");
        return booking;
    }

    @Given("I am an authenticated user with a valid token")
    public void i_am_an_authenticated_user_with_a_valid_token() {
        token = TestConfig.getBasicAuth();
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
        CommonSteps.setResponse(response);
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