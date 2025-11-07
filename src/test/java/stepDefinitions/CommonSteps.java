package stepDefinitions;

import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;

public class CommonSteps {
    private static Response response;

    public static void setResponse(Response apiResponse) {
        response = apiResponse;
    }

    @Then("the response status code should be {int}")
    public void verifyResponseStatusCode(int expectedStatusCode) {
        Assert.assertNotNull("Response should not be null", response);
        Assert.assertEquals("Status code should match",
                expectedStatusCode, response.getStatusCode());
    }

    @Then("the response body should contain error message {string}")
    public void verifyErrorMessage(String expectedError) {
        Assert.assertNotNull("Response should not be null", response);
        String body = response.getBody().asString();
        Assert.assertTrue("Expected error text not found. Body: " + body,
                body != null && body.contains(expectedError));
    }
}