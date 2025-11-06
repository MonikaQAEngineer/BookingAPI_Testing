Feature: Update Booking
As an API user, I want to modify an existing booking,
So that I can keep reservation details up to date as per the API documentation.

This file is dedicated to PUT bookingID. The scenarios specifically address the documented requirement for Token-based authentication via a Cookie header.

Background:
  Given a booking exists with bookingID 9

  Scenario: Successfully update an existing booking with a valid token
    Given I am an authenticated user with a valid token
    And I have to update the lastname to "Authorized"
    When I send a PUT request to the booking endpoint with background booking ID
    Then the response code should be 200
    And the response body should show the lastname as "Authorized"

  @negative
  Scenario: Attempt to update a booking record without an authentication token
    Given I am not an authenticated user
    And I have to update the lastname to "NotAuthorized"
    When I send a PUT request to the booking endpoint with background booking ID
    Then the response code should be 403
    And the response body should be "Forbidden"

  @negative
  Scenario: Attempt to update a booking with an invalid authentication token
    Given I have an invalid authentication token "invalidTokenForTesting"
    And I have to update the lastname to "InvalidToken"
    When I send a PUT request to the booking endpoint with background booking ID
    Then the response code should be 403
    And the response body should be "Forbidden"

  @negative
  Scenario: Attempt to update a booking that does not exist
    Given I am an authenticated user with a valid token
    And I have to update the lastname to "NonExistent"
    When I send a PUT request to the booking endpoint with ID 9999
    Then the response code should be 405
    And the response body should be "Method Not Allowed"
