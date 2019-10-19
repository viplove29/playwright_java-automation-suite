Feature: Form Service Integration Suite

  Tests follow this pattern

  [SETUP]
  Form Service Integration tests run the whole workflow of creating a form in form service
  that is expecting to get values from other services and map the values onto the form.

  [CREATE TITAN OBJECTS]
  Next it calls out to other services and actually create the objects (policies, exposures, etc..) we are mapping.

  [RENDER FORM]
  Next it renders the form/pdf and hits an endpoint that gives us key value pairs of what will be final PDF.

  [VALIDATE]
  We then validate that what is on the form are what was created in corresponding titan service.

  Background:
    Given I act as 'Form Admin' using the 'form' service on the server located at 'https://api.dev.titan.v4af.com'
    And I set the context to be for the entity 'VERTAFORE'
@FormTest
  Scenario: Map a form to a canonical object type of POLICY
    Given That a form template is mapped to a canonical 'POLICY'
    And I create a titan 'POLICY'
    When I create and render a form instance associated to that form with 'POLICY' data
    Then 'POLICY' data is in the form

#  Scenario: Map a form to a canonical object type of PERSONAL AUTO POLICY
#    Given That a form template is mapped to a canonical 'PERSONAL AUTO POLICY'
#    And I create a titan 'EXPOSURE GROUP'
#    And I create a titan 'POLICY'
#    When I create and render a form instance under that form with 'PERSONAL AUTO POLICY' data
#    Then titan 'POLICY' data is in the form
@FormTest
  Scenario: Map a form to a canonical object type of AGENCY
    Given That a form template is mapped to a canonical 'AGENCY'
    When I create and render a form instance associated to that form with 'AGENCY' data
    Then 'AGENCY' data is in the form

#  Scenario: Map a form to a canonical object type of CUSTOMER
#    Given That a form template is mapped to a canonical 'CUSTOMER'
#    When I create and render a form instance under that form with 'CUSTOMER' data
#    Then 'CUSTOMER' data is in the form



