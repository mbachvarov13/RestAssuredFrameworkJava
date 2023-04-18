Feature: Place API's

  Scenario: Add place on google map
    Given I have add place payload
    When I add a new place
    Then I got status code 200
    And status in response is OK
