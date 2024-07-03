
@tag
Feature: Verify the Error Message Validation for ECommerce Login Page.
  I want to use this template for my feature file
  
  @ErrorValidation
  Scenario Outline: Purchase Zara Coat 3 Jacket from Ecommerce Site
    Given User lands on Ecommerce page
    Given Logged in to Ecommerce site with username <username> and password <password>
    Then Verify the Error Validation Message "Incorrect email or password." on Login page

    Examples: 
      | username                   | password  | 
      | ashwin.kallingal@gmail.com | Manasvi@09|
    