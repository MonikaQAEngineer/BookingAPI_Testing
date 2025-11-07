Feature: Create new Booking
Verify that new bookings can be created successfully.

  Scenario Outline: Successfully create new booking with valid data
    Given I have booking data with "<firstname>" "<lastname>" <totalprice> "<depositpaid>"
    When I send a POST request to create a booking
    Then the response status code should be 200
    And the response body should contain "<firstname>" "<lastname>"

    Examples:
      | firstname | lastname | totalprice | depositpaid |
      | Oreo      | Deo      |       1200 | true        |
      | Mice      | Smith    |       1500 | false       |
      | Bobby     | Johnson  |        800 | true        |

  Scenario: Attempt to create a booking with incomplete information (missing required fields)
    Given I have a booking data with only "<lastname>" "<depositpaid>"
    When I send a POST request to create a booking
    Then the response status code should be 500
    And the response body should contain error message "Internal Server Error"