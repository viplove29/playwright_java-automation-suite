# titan-integration-tests

## Where to write tests

This project uses JUnit to compose tests.
Unlike other projects that use cucumber/gherkin the integration-tests repo will have tests composed in JUnit.

To write a test go to `src/test/java/com/verafore/test/services/`

find what service you want to create a folder for like: `document`

Here you will find a file `DocumentServiceIntegration`

This file will have all the tests for Document-Service that have an integration test.
Each test is just a JUnit test and does not have a corresponding feature file or english sentence to coordinate.


## How to generate service-wrappers 

This project has a tool to build service-wrappers into Java Classes for you by using a gradle task.

The hope is that as API's change the tool will be responsible for updating our 'service-wrapper' to 
interact with each backend service. 

to use it:

``gradle run --args='{SERVICE-NAME}'``

so go get a whole Java class to interact with Auth-Service run:

``gradle run --args='auth'``

This command will reach out to swagger in dev-master then parse the whole API and output your class named
`UseAuthServiceTo`

## Framework

This project uses Serenity Screenplay for managing tests.