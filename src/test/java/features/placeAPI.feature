Feature: Place API's

  Scenario: Add place on google map
    Given I have add place payload
    When I call addPlace
    Then I got status code 200
    And returned status in response is OK
