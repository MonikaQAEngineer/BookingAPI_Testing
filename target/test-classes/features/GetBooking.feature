Feature: Get Booking by ID
Verify that user is able to retrieve booking details by providing valid and invalid booking IDs.

  Scenario: Attempt to retrieve a booking with an valid ID
    When I send a GET request to the booking endpoint with booking id 9
    Then the response status code should be 200
    And the response body should contain a valid booking details

  Scenario: Attempt to retrieve a booking with an invalid ID
    When I send a GET request to the booking endpoint with booking id 999999990
    Then the response status code should be 404
    And  the response body should contain error message "Not Found"