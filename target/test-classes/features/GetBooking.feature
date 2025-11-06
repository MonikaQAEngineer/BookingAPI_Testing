Feature:

  Scenario: Attempt to retrieve a booking with an valid ID
    When I send a GET request to the booking endpoint with booking id 9
    Then response status code should be 200
    And the response body should contain the all booking details


  Scenario: Attempt to retrieve a booking with an invalid ID
    When I send a GET request to the booking endpoint with booking id 999999990
    Then response status code should be 404
    And  response body should be "Not Found"

