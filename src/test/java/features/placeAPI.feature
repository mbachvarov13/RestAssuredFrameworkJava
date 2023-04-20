Feature:Google maps API's

  Scenario Outline: Add place on google map
    Given I have add place payload <name>, <address>, <language>
    When I call addPlaceAPI with POST http request
    Then I got status code 200
    And returned status in the response is OK
    And verify <name> returned by getPlaceAPI is the same as in the POST request
    Examples:
      | name     | address     | language     |
      | TestName | TestAddress | TestLanguage |

  Scenario: Delete place on google map
  Given
    When I call deletePlaceAPI with POST http request


