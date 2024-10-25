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
Feature: Login Feature

  @tag1
  Scenario: Check User can able to Login with Valid Credentials
    Given I Open Browser with url "https://flights.qedgetech.com"
    Then I should see Login Page
    When I Enter UserName as "interiautest@gmail.com"
    When I Enter PassWord as "Dsop@123"
    And I click Sign In
    Then I should see User DashBoard
    When I click Logout
    Then I Should See Login Page after Logout
    And I Close Browser
    
  @tag2
  Scenario Outline: Check User can able to login with invalid Credentials
  	Given I Open Browser with url "https://flights.qedgetech.com"
    Then I should see Login Page
    When I Enter UserName as "<UserName>"
    When I Enter PassWord as "<Password>"
    And I click Sign In
    Then I should see Error Message
    And I Close Browser
    
  Examples:
						|UserName|Password|
						|interiautest@gmail.com|Dsop@1234|
						|Dsop@gmail.com|Dsop@123|
						|Dspop@gmail.com|Dsop@Dsop|
						

    