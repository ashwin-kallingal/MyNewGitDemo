
@tag
Feature: Purchase the Order from the ECommerce site.
  I want to use this template for my feature file
  
  Background:
  Given User lands on Ecommerce page
  

  @Regression
  Scenario Outline: Purchase Zara Coat 3 Jacket from Ecommerce Site
    Given Logged in to Ecommerce site with username <username> and password <password>
    When Add product <productName> to cart
    And Checkout the <productName> and submit the order  
    Then The confirmation message "THANKYOU FOR THE ORDER." is displayed on Confirmation page

    Examples: 
      | username                   | password  | productName |
      | ashwin.kallingal@gmail.com | Manasvi@08| ZARA COAT 3 |
    