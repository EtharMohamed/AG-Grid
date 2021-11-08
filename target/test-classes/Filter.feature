Feature: Grid WebSite First Feature
  			
  			In order to do Filtration 
  			As Having Valid Data 
  			I want to Filter on Specific Data


  Scenario Outline: Task Scienario Example 
  
    Given Making Language Filter on “French”
    And  Making Country Filter on “Belgium”
    When I verify Selected data on “French” and “Belgium”
    Then Jan row is appeare with right value 
   