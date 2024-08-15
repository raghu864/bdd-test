Feature: Phone API Tests

  Scenario: Filter phones by name prefix
    Given I have a list of phones
    When I filter phones with names starting with "Apple"
    Then I should get all phones with names starting with "Apple"

  Scenario: Find phone with the lowest cost
    When I get the phone with the lowest cost
    Then The phone with the lowest cost should be valid
    And The response ID should not be null

  Scenario: Validate phone ID
    Given I have a list of phones
    When I get the phone with the lowest cost
    Then The response ID should not be null