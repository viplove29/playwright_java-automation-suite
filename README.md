# EMS-integration-tests

## Where to Write Tests

This project uses JUnit to compose tests there is no cucumber/gherkin in this repo.

To write a test:
- go to `src/test/java/com/verafore/test/services/`
- find or make the folder for the service: `document`
- find or make the class for the test: `DocumentServiceIntegration`

`DocumentServiceIntegration` Class will have all the tests for Document-Service.

Each integration test is a JUnit test and does not have a corresponding feature file or english sentence to coordinate.


## How to Generate Service-Wrappers 

This project has a tool to build EMS Service Wrappers and Models into Java Classes using a gradle task.

As our EMS api changes the tool will be responsible for updating our 'service-wrapper' & models to 
interact with each backend service. 

to use it:

``./gradlew generateSwaggerCode``

note: you need to run ./gradlew spotlessJavaApply to clean unused imports that may break the build.

This command will reach out to swagger in dev-master then parse the whole API and output your class and models into your /build folder and into the source sets for use by your JUnit tests.

## General Test Setup
Each class that holds integration tests must have the `@RunWith(SerenityRunner.class`) annotation.

We need to build our `cast` of actors for the test so we use a `@Before` hook at the top of the class and we give it:
- name
- context
- login type

For the actor(s) that we want to use for the test.

Then we `setTheStage` with the actors we just built.
 
 This method will handle logging in and preparing the actor for the test.
This before hook will run before each JUnit test making sure our actors are fresh.

Example:
`````
@RunWith(SerenityRunner.class)
public class UnderwriterServiceIntegration {

  private List<EMSActor> actors = new ArrayList<>();

  @Before
  public void getAnAccessToken() {
    actors.add(new EMSActorBuilder().actorName("bob").context("userContext").buildEMSActor());
    OnStage.setTheStage(GetAnAccessToken(actors));
  }

  @Test
  public void UnderwritersReturnsAllUnderwriters() {

    Actor bob = theActorCalled("bob");

    UseUnderwritersTo underwritersAPI = new UseUnderwritersTo();

    bob.attemptsTo(
        underwritersAPI
            .GETUnderwritersOnTheUnderwritersController(null, "string"));

    bob.should(
       seeThatResponse("successfully gets underwriters",
                       res -> res.statusCode(200)));
  }
}

`````

Next we just need to write our tests.
Each test should have the JUnit `@Test` annotation.

To get access to the actor you need use the `theActorCalled`
 and give it the username of the actor that you want to use.

From there just build your setup test data and send your requests off. 
The general format for sending requests off in this project look like:

```
bob.attemptsTo(customersApi.GETCustomersOnTheCustomersController(259, "string"));
```

Use the the variable that holds your current actor and access the `.attemptsTo` api which takes in a `Performable`.
The performable we pass will be the service-wrapper Class and Method that are auto-generated.
This example doesn't take any parameters, but a different endpoint that requires an ID or a body for example 
 would be supplied right here.
 


## Using cookies
Use these arguments for gradle task used for running test:

`-Dcookies="{COOKIE_VALUE}"`
 

#### Getting The Last Request Sent
Serenity gives access to an easy way to see our last response using:
`SerenityRest.lastResponse()`
Where you're welcome to deserialize the response or pull properties off and validate against.

`lastResponse()` also has `.getStatusCode()`, making asserts against status codes
straightforward.

We can also use the serenity-screenplay-rest libraries to write these asserts fluently.
Ex:
```
    currentActor.should(
        seeThatResponse("Create Branding response is successful", res -> res.statusCode(201)));
```
This example asserts that the last response was a 201, and it will be annotated in the reports
with the string we provide. If the response is *not* a 201 then the test will fail.

##### What about getting getting properties off of a response?
Having all of our models from each service in the project make it easy to cast responses and get properties.
Here is an example:
```
    String brandingId =
        LastResponse.received()
            .answeredBy(currentActor)
            .getBody()
            .jsonPath()
            .getList("content", LogoV1.class)
            .get(0)
            .getId();
```

### Running Tests Selectively
We might not want to run all the tests each time, but target tests instead.
Maybe we want to only run the test suite to hit titan to titan 
tests.
Or maybe we only want to run tests that reach out to 3rd parties.
Here is how we would set our runners.
  ```
@RunWith(SerenityRunner.class)
@WithTags({
      @WithTag("titan"),
      @WithTag("3rdParty"),
      @WithTag("AMS360")
})
public class TestClass {

  @Test
  @WithTag("important") 
  public Performable testingImportantThings(){...}

  @Test
  @WithTag("smallstuff") 
  public Performable testingSmallStuff(){...}
  ```

So, at run-time we pass in `-Dtags="{tagName}" `

EX:
passing in `-Dtags="titan" ` or `-Dtags="3rdParty"` would make the whole `TestClass` run.

passing in `-Dtags="important" `  would only cause `testingImportantThings` to run.
  

### Reports
After each run, Serenity outputs some pretty reports.
To access the reports locally, after running the test go to 
`target/site/serenity/index.html`

Open the index.html in your browser and you will see the report. When you find your
test results you can click on each step that happened and even each request/response that was sent. Each request sent off will be annotated with the 'summary' from swagger, hopefully making clear what was happening on each request that was sent in the test.


## Helpful Links & Resources

#### [Swagger Editor](https://editor.swagger.io/)
 - This helpful swagger tool allows us to paste swagger json and lint/validate it. Or convert it to openAPI v3. Very helpful in debugging special edge-case
things in writing our templates.


#### [OpenAPI templates/mustache files](https://github.com/OpenAPITools/openapi-generator/tree/master/modules/openapi-generator/src/main/resources)
 - This is where all the remote templates live for code-generation. There are a variety of 
templates here and is helpful to see how other templates are organized. You will also need to
download these and override them to do any customization.

#### [Spring Code Generator Class](https://github.com/OpenAPITools/openapi-generator/blob/master/modules/openapi-generator/src/main/java/org/openapitools/codegen/languages/SpringCodegen.java)
 - This is the Spring Code Generator that we extend from in this project.
 - [All Generator Classes](https://github.com/OpenAPITools/openapi-generator/blob/master/modules/openapi-generator/src/main/java/org/openapitools/codegen/languages) This is where all the remote language specific code generator classes live.

#### [Gradle Swagger Plugin](https://github.com/int128/gradle-swagger-generator-plugin)
 - This is the plugin used to coordinate jarring up the custom code-gen class and running the
command `./gradlew generateSwaggerCode`. I found it easier to use than the official openAPI generator gradle plugin.
We are still using OPENAPI though, so this plugin is really only used to make gradle
scripting easier.


