#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Book Flight Ticket

  @BoockFlightTicket
  Scenario: Check User can able to book flight ticket with valid credentials
    Given I Open Browser with url "https://flights.qedgetech.com"
    Then I should see Login Page
    When I Enter UserName as "interiautest@gmail.com"
    When I Enter PassWord as "Dsop@123"
    And I click Sign In
    Then I should see User DashBoard
    When I Enter Flight Resrvation Date as "06/25/2025"
    When I select Flight from "Hyderabad"
    When I select Flight To "Kolkatha"
    And I Click Search Flights
    When I select airline as "King Fisher Airlines" from Flight search results table
    When I Enter Passeger Name as "Sridhar Parutabad"
    When I Select Flight class as "First Class"
    And I Enter Number of Tickets as "5"
    When I should take Screen Shot of order information
    When I Click Insert Order
    Then I should see Order Success Message
    When I click Logout
    Then I Should See Login Page after Logout
    And I Close Browser
