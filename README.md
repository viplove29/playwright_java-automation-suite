# titan-integration-tests

## Where to write tests

This project uses JUnit to compose tests there is no cucumber/gherkin in this repo.

To write a test:
- go to `src/test/java/com/verafore/test/services/`
- find or make the folder for the service: `document`
- find or make the class for the test: `DocumentServiceIntegration`

`DocumentServiceIntegration` Class will have all the tests for Document-Service.

Each integration test is a JUnit test and does not have a corresponding feature file or english sentence to coordinate.


## How to Generate Service-Wrappers 

This project has a tool to build Titan service-wrappers into Java Classes using a gradle task.

As our Titan api's change the tool will be responsible for updating our 'service-wrapper' to 
interact with each backend service. 

to use it:

``gradle run --args='{SERVICE-NAME}'`` (you can generate multiple wrappers at the same time by adding a space)

To generate a whole Java class to interact with Auth-Service and Document-Service run:

``gradle run --args='auth document'``

This command will reach out to swagger in dev-master then parse the whole API and output your class named

`UseAuthServiceTo` and `UseDocumentServiceTo `in the correct folder path for you.

 
 If you already have the class then it will be overwritten, and if there are errors in compiling the classes they will need to be deleted before the task is run again.


## General Test Setup

Some tools for setup and talking to Titan api's( like feeding the service/tenant/entity...) into the path have already been configured.


Each class that holds integration tests must have the `@RunWith(SerenityRunner.class`) annotation.

We need to build our `cast` of actors for the test so we use a `@Before` hook at the top of the class and we give it:
- username
- tenant name
- entity name
for the actor(s) that we want to use for the test.
Then we `setTheStage` with the actors we just built.
This before hook will run before each JUnit test making sure our actors are fresh.

Example:
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

Use the the variable that holds your current actor and access the `.attemptsTo` api which takes in a `Performable`.
The performable we pass will be the service-wrapper Class and Method that are auto-generated.
This example doesn't take any parameters, but a different endpoint that requires an ID or a body for example 
 would be supplied right here.
 
```
    currentActor.attemptsTo(
        UseDocumentServiceTo.getImageUsingGetOnTheBrandingController(id, "original"));
```
Serenity gives access to an easy way to see our last response using:
`SerenityRest.lastResponse()`

Which gives you access to things like `.getStatusCode()`, which makes asserting against status codes
straightforward.

### Reports
After each run, Serenity outputs some pretty reports.
To access the reports locally, after running the test go to 
`target/site/serenity/index.html`

Open the index.html in your browser and you will see the report. When you find your
test results you can click on each step that happened and even each request/response that was sent. Each request sent off will be annotated with the 'summary' from swagger, hopefully making clear what was happening on each request that was sent in the test.
