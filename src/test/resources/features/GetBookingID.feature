Feature: Get Booking IDs
Verify the retrieval of booking IDs from the booking API with and without filters.

  Scenario: Retrieve all booking IDs without any filters
    When I send a GET request to the booking endpoint
    Then the response status code should be 200
    And the response body should be a list containing at least one booking ID

  Scenario Outline: Filter booking IDs using lastname
    When I send a GET request to the booking endpoint with query parameter '<param_name>' set to '<param_value>'
    Then the response status code should be 200
    And the response body should be a list containing at least one booking ID

    Examples:
      | param_name | param_value |
      | firstname  | Josh        |
      | lastname   | Brown       |
      | checkin    | 2025-01-01  |

  Scenario Outline: Filter booking IDs using firstname that doesnt exist in booking records
    When I send a GET request to the booking endpoint with query parameter '<param_name>' set to '<param_value>'
    Then the response status code should be 200
    And the response body should be empty

    Examples:
      | param_name | param_value |
      | firstname  | None        |
      | lastname   | Existing    |