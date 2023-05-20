@regression
Feature: Jumia task Scenarios

  Scenario: Create a new account
    Given I am on Jumia website
    And I click on the Account -> Sign in link
    When I fill in all the registration data
    Then I should be able to login using the newly created account

  Scenario: Add items to cart and verify subtotal
    Given I am on Jumia website
    Given I click on the Account -> Sign in link
    When I fill in all the registration data
    Then I should be able to login using the newly created account
    When I hover on Fashion -> Shirts
    And I add two items to my cart
    When I click on the Cart button
    Then I should see the two items in my cart
