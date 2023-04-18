Feature: Place API's

  Scenario: Add place on google map
    Given I have add place payload
    When I POST a new place
    Then I got status code 200
    And returned status in the response is OK
