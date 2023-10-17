Feature: Youtube Search Functionality
  Background: Background for Youtube Search Functionality
    Given Navigate to the page

    Scenario: Scenario for Youtube Search Functionality
      And Search for "Selenium" keyword
      And Click on Search Button
      Then Verify that at least "80" videos are displayed
      Then Excel out the title of the last video