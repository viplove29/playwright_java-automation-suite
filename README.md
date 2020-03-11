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

The hope is that as api's change the tool will be responsible for updating our 'service-wrapper' to 
interact with each backend service. 

to use it:

``gradle run --args='{SERVICE-NAME}'``

so go get a whole Java class to interact with Auth-Service run:

``gradle run --args='auth'``

This command will reach out to swagger in dev-master then parse the whole API and output your class named
`UseAuthServiceTo`

## General Test Setup

This project uses JUnit and Serenity Screenplay to run and write integration tests. Some tools for setup and talking to Titan api's 
have already been configured.

To write a new test here are some basic setup information
Each class for that holds a service integration test should have the @RunWith(SerenityRunner.class) annotation.

We need to build our `cast` of actors for the test so we use a @Before hook at the top of the class and we give it:
- username
- tenant name
- entity name
for the actor(s) that we want to use for the test.
Then we `setTheStage` with the actors we just built.
This before hook will run before each JUnit test making sure our actors are fresh.

`````
@RunWith(SerenityRunner.class)
public class DocumentServiceIntegration {

  private List<TitanUser> users = new ArrayList<>();

  @Before
  public void setupActors() {
    users.add(new TitanUser("donald@lizzy123.com", "LIZZY123", "LIZZY123"));
    OnStage.setTheStage(BuildCastOfUsers.buildCastOfAuthenticatedUsers(users));
  }

  @Test
  public void documentServiceBrandingSetsConfigCorrectly() throws IOException {
    Actor currentActor = theActorCalled("donald@lizzy123.com");



`````

Next we just need to write our tests.
Each test should have the JUnit `@Test` annotation.


To get access to the actor you need use the `theActorCalled`
 and give it the username of the actor that you want to use.

From there just build your setup test data and send your requests off. 
The general format for sending requests off in this project look like:

```
currentActor.attemptsTo(UseDocumentServiceTo.getBrandingsUsingGetOnTheBrandingController());
```

You use the the variable that holds your current actor and access the `.attemptsTo` api which takes in a `Performable`.
The performable we pass will be the service-wrapper Class and Method that are auto-generated in the gradle task you ran.
 This example doesn't take any parameters, but a different endpoint that requires an ID or a body for example 
 would be supplied right here.

Serenity gives access to an easy way to see our last response using:
`SerenityRest.lastResponse()`

Which gives you access to things like '.getStatusCode()'

