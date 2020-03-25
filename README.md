# titan-integration-tests

## Where to Write Tests

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

To generate all services run:

``gradle run --args='all'``

To generate a whole Java class to interact with Auth-Service and Document-Service run:

``gradle run --args='auth document'``

This command will reach out to swagger in dev-master then parse the whole API and output your class named

`UseAuthServiceTo` and `UseDocumentServiceTo `in the correct folder path for you.

 
 If you already have the class then it will be overwritten, and if there are errors in compiling the classes they will need to be deleted before the task is run again.


## General Test Setup

Some tools for setup and talking to Titan api's( like feeding the service/tenant/entity...) into the path have already been configured.
As we add on 3rd party & non Titan API's we will need to build more tools to easily feed tokens/auth/domains/etc into.

Each class that holds integration tests must have the `@RunWith(SerenityRunner.class`) annotation.

We need to build our `cast` of actors for the test so we use a `@Before` hook at the top of the class and we give it:
- username
- tenant name
- entity name

For the actor(s) that we want to use for the test.

Then we `setTheStage` with the actors we just built.
Right now this project is sets the cast using the class
`BuildCastOfTitanUsers` where we access the method `loadAndAuthenticate` and pass it a list of Titan Users.
 
 This method will handle logging in and preparing the actor for the test.
This before hook will run before each JUnit test making sure our actors are fresh.

Example:
`````
@RunWith(SerenityRunner.class)
public class DocumentServiceIntegration {

  private List<TitanUser> users = new ArrayList<>();

  @Before
  public void setupActors() {
    users.add(new TitanUser("donald@lizzy123.com", "LIZZY123", "LIZZY123"));
    OnStage.setTheStage(BuildCastOfTitanUsers.loadAndAuthenticate(users));
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

What about being able to configure path-params inside of the test?
```
    Actor currentActor = theActorCalled("donald@lizzy123.com");

    HaveTitanContext.theNewProductOf(currentActor, "FORM-ADMIN-WEB-UI");
```
`HaveTitanContext` has a variety of methods to set things like tenant/entity/product/domain.

In this example, the default product is loaded as AMS-WEB-UI but for the test
we overrode that with the product of "FORM-ADMIN-WEB-UI" for each request in the test body.

## Using branches
Use these arguments for gradle task used for running test:

Single service
`-Dcookies="{SERVICE_NAME}={BRANCH_NAME}"`
 
Multiple services - seperated by ;
`-Dcookies="form-service=US9999-add-aods;opportunity-service=US1111-add-new-thing"`

#### Getting The Last Request Sent
Serenity gives access to an easy way to see our last response using:
`SerenityRest.lastResponse()`
Where you're welcome to deserialize the response or pull properties off and validate against.

`lastResponse()` also has `.getStatusCode()`, making asserts against status codes
straightforward.

### Reports
After each run, Serenity outputs some pretty reports.
To access the reports locally, after running the test go to 
`target/site/serenity/index.html`

Open the index.html in your browser and you will see the report. When you find your
test results you can click on each step that happened and even each request/response that was sent. Each request sent off will be annotated with the 'summary' from swagger, hopefully making clear what was happening on each request that was sent in the test.
