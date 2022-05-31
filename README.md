# EMS Automation Suite

## About the Project
This project is intended to test the functionality of all the EMS endpoints, and while currently is only available to run against QA it will soon be able to run against MDC and Production as well. It will also soon be provisioned to be able to run against earlier versions of EMS to ensure proper testing of backwards compatibility and the business rules that come with that.

The project uses Serenity with JUnit, and implements the Screenplay pattern. It also uses the Handlebars/Mustache plugin to parse the EMS Swagger code and generate 'service-wrappers', which are the classes that we use to build individual endpoint tests. (This is explained a little more in the next section.)

As previously stated, this project requires the Handlebars/Mustache Plugin - If you're using IntelliJ, go to Files > Settings > Plugins > Marketplace to get it if needed.

 If you have any changes or suggestions for this README please make a merge request.
 
## Initial Project Set Up

Here are the steps you must follow in the order listed when pulling down from this repo for the first time:

1. Clone the repo and open the project in IntelliJ. 
- It will initially try to build itself and will fail. 
    
 
2. Navigate to the terminal and enter the following command: 
- ./gradlew wrapper --gradle-version=6.3

 
3. Go into Settings -> Build, Execution, Deployment-> Build Tools -> Gradle
- Set the Gradle JVM to "Use Project JDK". 
- Make sure that "Build and run using Gradle" is also selected. 
- Hit "Apply" and then "OK". 
    
 
4. Once settings is closed you should do a Rebuild (not a normal build). 
- During rebuild it should pass the configure build task. 


 
 

## Start with Generating Service-Wrappers 

This project has a tool to build EMS Service Wrappers and Models into Java Classes using a gradle task.

As our EMS api changes the tool will be responsible for updating our 'service-wrappers' & models to 
interact with each backend service. 

to use it:

`./gradlew generateSwaggerCode`

The pipeline checks for code quality, so the project automatically runs `./gradlew spotlessJavaApply` for you. You can also run it manually at any time if you wish.

This command will reach out to swagger in QA then parse the whole API and output your class and models into your /build folder and into the source sets for use by your JUnit tests.


## Where to Write Tests

This project uses JUnit to compose tests - there is no cucumber/gherkin in this repo.

To write a test:
- go to `src/test/java/com/verafore/test/services/`
- find or make the folder for the service: `customers`
- find or make the class for the test: `GET_Customers`

`GET_Customers` Class will have all the tests for the GET Customers endpoint.

Each test is a JUnit test and does not have a corresponding feature file or english sentence to coordinate.


## General Test Setup
Each class that holds integration tests must have the `@RunWith(SerenityRunner.class`) annotation.

Each test requires an `actor` which acts as a device to hold the token needed to access whatever endpoint you're testing. The suite gets tokens for a number of actors with different combinations of keyType/loginPath, that can then be used for all tests. This is facilitated through the use of the TokenSuperClass, which all test classes should extend to. 

(If you want to build your own actors for a test you can look at GET_Features to see an example of how to do that.)

Next we just need to write our tests.
Each test should have the JUnit `@Test` annotation.

To get access to the actor you need use the `theActorCalled` and give it the username of the actor that you want to use. To see the username of available actors the suite automatically uses see the TokenSuperClass.

From there just build your setup test data and send your requests off. 
The general format for sending requests off in this project look like:

```
bob.attemptsTo(customersApi.GETCustomersOnTheCustomersController(259, "string"));
```

Use the variable that holds your current actor and access the `.attemptsTo` api which takes in a `Performable`.
The performable we pass will be the service-wrapper Class and Method that are auto-generated.
This example takes two parameters, but a different endpoint that requires an ID or a body for example 
 would be supplied right here.


 #### Running Tests
 The easiest way to run the tests is to click the green arrow to the left fo the test. You can also right-click on the file and select `Run` or `Debug` the entire class. You can do the same thing with the entire package. The tests can also be run from the command line, as explained in more detail below in the 'Running Tests Selectively' section.
 

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

##### What about getting properties from a response?
Having all of our models from each service in the project makes it easy to cast responses and get properties.
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

### Running in Different Environments
There are different environment variables set up in serenity.conf for use in running tests in MDC or Production. Pass in `-Denvironment=<env name>` at runtime to switch.

For example, to run tests in a mdc environment, use this in the command line: `./gradlew test -Denvironment=mdc`

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


