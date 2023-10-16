Feature: Validate Place API'S

	@AddPlace
  Scenario Outline: Verify if place is being Successfully added using AddPlaceAPI
    Given Add Place Playload with "<name>" "<language>" "<address>"
    When user calls "AddPlaceAPI" with "post" http request
    Then the API call GOT success with status code 200
    And "status" in response body is "OK"
    And verify place_id created maps to "<name>" using "GetPlaceAPI"

    Examples: 
      | name    | language | address |
      | maharaj | marathi  | swrajya |

  #    | maharani| marathi| swrajya|
  
  @DeletePlace
  Scenario: verify if delete place functionality is working
    Given Delete place payload
    When user calls "DeletePlaceAPI" with "post" http request
    Then the API call GOT success with status code 200
    And "status" in response body is "OK"
