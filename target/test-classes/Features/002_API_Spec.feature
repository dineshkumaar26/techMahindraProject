
Feature: API TestCases

  Scenario Outline: Get request to retrieve information
    Given I have the base URI "<baseURI>"
    When I send a GET request to "<endpointURL>"
    Then the response status code should be <statuscode>
    
     Examples:
       |baseURI           |endpointURL  |statuscode|
       |https://reqres.in/|api/users/2  |200       |
       
Scenario Outline: Create a new user
Given make the post request "<baseuri>""<endpoint>""<name>""<job>"<statuscode>

Examples:
| name       | job    | baseuri           | endpoint |statuscode |
| morpheus   | leader |https://reqres.in/ |api/users |201        |