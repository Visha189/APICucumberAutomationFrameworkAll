
Feature: Insert products using Post api

  Scenario Outline: Validate Post Product api status code works correctly
    Given I hit the url of post product api endpoint
    When I pass the url of products in the request
    And I pass the request body of product title <ProductTitle>
    Then I receive the response code as 200
    Examples:
      |ProductTitle|
      |Shoes       |

  Scenario Outline: Validate Post Product api response body works correctly
    Given I hit the url of post product api endpoint
    When I pass the url of products in the request
    And I pass the request body of product title <ProductTitle>
    Then I receive the response body with id as <id>
    Examples:
      |ProductTitle|id|
      |Shoes       |21|