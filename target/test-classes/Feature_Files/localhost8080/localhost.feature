Feature: Testing the messaging APP

  Scenario: Tests sending a messaging functionality
    Given I navigate to "http://localhost:8080/" page
    When I send a message
    Then The sent message should be visible to the end users
    And Message profile image is present
    And The message should have a time stamp
    Then Calender is shown during mouse over
#   Then chatbox color is #000000 // no css sheet present

  Scenario: Ability to catch wrong inputs
    Given I send a message with five spaces
    Then I should not be able to send a blank message

  Scenario: Ability to send versatile messages
    Given I send a message with multiple space key entry
    Then Message sent should be spaced accordingly

  Scenario: ability to handle long texts and XML code
    Given I send a long message
    When It should cut off at 140 characters
    Then I send input XML code
    Then It should not crash

  Scenario: ability to send multiple messages continuously
    Given I send a message 100 times
    When I refresh the page
    Then Chat history should remain



