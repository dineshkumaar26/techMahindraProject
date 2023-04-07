
Feature: Title of your feature

Scenario Outline: Validate_RequestType_and_EndPoint_for_GETSINGLEUSERNOTFOUND_API
    Given Login to reqres Home Page "<url>" "<expectedResult>"
    And Validate if different request types are displayed "<expectedRequest>"
    And Navigate to Single user not found request
    When validate the request and response requestURL "<requestURL>" "<responsecode>"
    
    Examples:
    |url               | expectedResult                                                        |expectedRequest                                                                                                                                                                                                                                             |  requestURL       | responsecode  |
    |https://reqres.in/| Reqres - A hosted REST-API ready to respond to your AJAX requests     |LIST USERS, SINGLE USER, SINGLE USER NOT FOUND, LIST <RESOURCE>, SINGLE <RESOURCE>, SINGLE <RESOURCE> NOT FOUND, CREATE, UPDATE, UPDATE, DELETE, REGISTER - SUCCESSFUL, REGISTER - UNSUCCESSFUL, LOGIN - SUCCESSFUL, LOGIN - UNSUCCESSFUL, DELAYED RESPONSE |/api/users/23      | 404           |

Scenario: SupportPage_Validation
    Given Login to reqres Home Page "<url>" "<expectedResult>"
    And Click on support reqRes link
    When verify one time Support Option "<oneTimeSupport>"
    And verify Monthly Support Option  "<MonthlySupport>"
    And validate the Upgrade button 
    
     Examples:
    | oneTimeSupport              | MonthlySupport               |  url                | expectedResult                                                   |
    | One-time payment ($)        |Monthly support ($5/month)    |  https://reqres.in/ |Reqres - A hosted REST-API ready to respond to your AJAX requests |