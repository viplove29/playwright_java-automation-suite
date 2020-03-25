package com.vertafore.test.utilities.classgenerator;

import static com.vertafore.test.utilities.classgenerator.ClassBuilder.capitalizeStringArrToString;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.base.CaseFormat;
import com.vertafore.core.util.JsonHelper;
import groovyjarjarcommonscli.MissingArgumentException;
import io.restassured.response.Response;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import liquibase.util.StringUtils;
import net.serenitybdd.rest.SerenityRest;
import org.apache.commons.text.WordUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ServiceWrapperGenerator {

  private static final String SWAGGER_API_URL =
      "https://api.dev.titan.v4af.com/%s/v2/api-docs?group=%s-service";

  private static String[] ALL_SERVICES = {"accounting",
          "alert",
          "auth",
          "carrier",
          "carrier-update-ingestion",
          "claim",
          "commission",
          "communication",
          "company",
          "config",
          "contact",
          "customer",
          "document",
          "event",
          "exposure",
          "form",
          "file-intake",
          "invoice",
          "license",
          "notification",
          "opportunity",
          "policy",
          "product",
          "question",
          "quickbooks-integration",
          "rating-data",
          "rating",
          "render",
          "report",
          "schedule",
          "small-agency-ams-web-orchestration",
          "to-do"};

  private final String BASE_PACKAGE_PATH = "com.vertafore.test.tasks.servicewrappers.%s";

  private final String[] DEFAULT_IMPORTS = {
    "net.serenitybdd.screenplay.Performable",
    "net.serenitybdd.screenplay.Task",
    "com.vertafore.test.abilities.CallTitanApi"
  };

  private final String CLASS_NAME_TEMPLATE = "Use%sServiceTo";
  private final String METHOD_DEFINITION_TEMPLATE =
      "return Task.where(\n\t\t\"{0} %s\", \n\t\t\tactor -> {\n\t\t\t\t%s\n\t\t\t}\n\t\t);";

  private final String GET_FILE_MIME_TYPE =
      "String mime = URLConnection.guessContentTypeFromName(file.getName());";

  private final String BEFORE_RETURN_STATEMENT = "%s";

  private final String REST_CALL_METHOD_CHAIN_TEMPLATE =
      "CallTitanApi.asActorUsingService(actor, THIS_SERVICE).%s%s%s(%s);";
  private final String CONTENT_TYPE_TEMPLATE = "contentType(\"%s\").";
  private final String FORM_DATA_TEMPLATE_ = "multiPart(\"%s\", %s %s).";
  private final String PATH_PARAM_TEMPLATE = "pathParam(\"%s\", %s).";
  private final String QUERY_PARAM_TEMPLATE = "queryParam(\"%s\", %s).";
  private final String BODY_TEMPLATE = "body(body).";

  private final String DEFAULT_BASE_PATH =
      "./src/main/java/com/vertafore/test/tasks/servicewrappers/%s";

  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Parameter {
    public String name;
    public String in;
    public String required;
    public String type;
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class ApiCallMethod {
    public String restVerb;
    public String summary;
    public String consumes;
    public String endpointName;
    public List<Parameter> parameters;
    public String methodName;
    public String methodArguments;
    public String restParamMethodChain;
    public String operationId;
    public String tag;
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Path {
    public String endpoint;
    public List<ApiCallMethod> apiCallMethods;
  }

  public static void main(String[] args) throws ParseException, MissingArgumentException {
    if (args.length == 0) {
      throw new MissingArgumentException("must provide service arguments when running this tasks");
    }
    String[] services = args.length == 1 && args[0].equalsIgnoreCase("all") ? ALL_SERVICES : args;

    for (String service : services) {
        System.out.println("next service: " + service + "\n");
      Response response = SerenityRest.get(String.format(SWAGGER_API_URL, service, service));
      new ServiceWrapperGenerator().generateServiceWrapperClass(response.getBody().asString());
    }
  }

  public void generateServiceWrapperClass(String json) throws ParseException {
    JSONObject swaggerJson = (JSONObject) new JSONParser().parse(json);

    List<Path> paths = convertPathsMapToPathsList((Map) swaggerJson.get("paths"));
    String servicePath = (String) swaggerJson.get("basePath");

    String className = String.format(CLASS_NAME_TEMPLATE, generateClassName(servicePath));
    String packageName = generatePackageName(servicePath);
    String packagePath = String.format(BASE_PACKAGE_PATH, packageName);

    ClassBuilder classBuilder = new ClassBuilder(packagePath, className);

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();

    classBuilder.addClassDocumentationLine("This class was automatically generated on " + dtf.format(now));

    classBuilder.addArrayOfImportStatements(DEFAULT_IMPORTS);
    classBuilder.addPrivateStaticFinalStringField(
        "THIS_SERVICE", "\"" + servicePath.replaceAll("/", "") + "\"");

    paths.forEach(
        p -> {
          p.apiCallMethods.forEach(
              ac -> {
                classBuilder.addPrivateStaticFinalStringField(ac.endpointName, p.endpoint);

                String restCallMethodChain =
                    String.format(
                        REST_CALL_METHOD_CHAIN_TEMPLATE,
                        String.format(CONTENT_TYPE_TEMPLATE, ac.consumes),
                        ac.restParamMethodChain,
                        ac.restVerb,
                        ac.endpointName);

                String beforeReturnStatement = generateBeforeReturnStatementCode(ac);

                classBuilder.addPublicStaticMethod(
                    "Performable",
                    ac.methodName,
                    ac.methodArguments,
                    beforeReturnStatement,
                    String.format(METHOD_DEFINITION_TEMPLATE, ac.summary, restCallMethodChain));
                classBuilder.addCollectionOfImportStatements(generateImportPaths(ac.parameters));
              });
        });
    String path =
        Paths.get(String.format(DEFAULT_BASE_PATH, packageName))
            .toAbsolutePath()
            .normalize()
            .toString();
    classBuilder.generateClassAt(path);
  }

  private List<Path> convertPathsMapToPathsList(Map paths) {
    List<Path> results = new ArrayList<>();
    paths
        .keySet()
        .forEach(
            p -> {
              Path nextPath = new Path();
              nextPath.endpoint = generateEndpointConstantValue((String) p);
              nextPath.apiCallMethods = convertApiCallsMapToApiCallsList((Map) paths.get(p));
              results.add(nextPath);
            });
    return results;
  }

  private List<ApiCallMethod> convertApiCallsMapToApiCallsList(Map apiCalls) {
    List<ApiCallMethod> results = new ArrayList<>();
    apiCalls
        .keySet()
        .forEach(
            ac -> {
              Map call = (Map) apiCalls.get(ac);
              ApiCallMethod nextApiCallMethod = new ApiCallMethod();

              nextApiCallMethod.restVerb = (String) ac;

              nextApiCallMethod.operationId = (String) call.get("operationId");

              nextApiCallMethod.summary = (String) call.get("summary");

              nextApiCallMethod.tag = (String) ((JSONArray) call.get("tags")).get(0);

              nextApiCallMethod.methodName =
                  generateMethodName(
                      nextApiCallMethod.operationId,
                      nextApiCallMethod.tag,
                      nextApiCallMethod.summary);

              nextApiCallMethod.endpointName =
                  generateEndpointConstantName(nextApiCallMethod.methodName);

              nextApiCallMethod.consumes = (String) ((JSONArray) call.get("consumes")).get(0);

              nextApiCallMethod.parameters =
                  JsonHelper.deserializeJsonAsList(
                      call.get("parameters").toString(), new TypeReference<>() {});

              nextApiCallMethod.methodArguments =
                  generateMethodArguments(nextApiCallMethod.parameters);

              nextApiCallMethod.restParamMethodChain =
                  generateRestParamsMethodChain(nextApiCallMethod.parameters);

              results.add(nextApiCallMethod);
            });
    return results;
  }

  public String generatePackageName(String path) {
    return path.replaceAll("[^A-z]+", "").toLowerCase();
  }

  private List<String> generateImportPaths(List<Parameter> params) {
    List<String> result = new ArrayList<>();
    params
        .stream()
        .filter(p -> p.type != null)
        .forEach(
            p -> {
              if (p.type.equalsIgnoreCase("file")) {
                result.add("java.io.File");
                result.add("java.net.URLConnection");
              }
            });
    return result;
  }

  private String generateEndpointConstantValue(String path) {
    if (path.contains("filter")) {
      int filterIndex = path.lastIndexOf("{");
      path = path.substring(0, filterIndex);
    }
    return "\"" + path.replaceAll("^.*\\{entityId}/", "").replaceAll("\\{\\?.*", "") + "\"";
  }

  private String generateEndpointConstantName(String methodName) {
    return CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, methodName);
  }

  private String generateMethodName(String operationId, String tag, String summary) {
    String controllerName = "";
    // we have to normalize the tag name to get the name of the controller
    if (tag.contains("-controller-v-1")) {
      controllerName = tag.replaceAll("-controller-v-1", "");
      controllerName = CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_CAMEL, controllerName);
    } else {
      // because some services don't have -controller-v-1 but instead have 'Realm Management'
      // we have to format the name of the controller
      controllerName = capitalizeAndCleanString(tag);
    }
    String result =
        operationId
            .replaceAll("GET", "Get")
            .replaceAll("PUT", "Put")
            .replaceAll("POST", "Post")
            .replaceAll("DELETE", "Delete")
            .replaceAll("PATCH", "Patch");

    // if our operationId contains _1 or _2... then the Endpoint Constant name should be
    // the summary formatted. This prevents endpoints being named the same thing
    // and from being named getUsingGet_1/getUsingGet_2
    if (result.matches(".*[_]\\d.*")) {
      result = StringUtils.lowerCaseFirst(capitalizeAndCleanString(summary));
    }

    return result + "OnThe" + controllerName + "Controller";
  }

  private String generateMethodArguments(List<Parameter> params) {
    StringBuilder result = new StringBuilder();
    params.forEach(
        param -> {
          if (param.name.matches("^(?!productId$|entityId$|tenantId$).*")) {
            if (param.type != null && param.type.equalsIgnoreCase("file")) {
              result.append("File file, ");
            } else if (param.in.equalsIgnoreCase("body")) {
              result.append("Object body, ");
            } else {
              result.append("String " + param.name + ", ");
            }
          }
        });

    return result.toString().replaceAll(", $", "").replaceAll("\"", "");
  }

  private String generateClassName(String path) {
    String[] arr = path.toLowerCase().replaceAll("/", "").trim().split("[^A-z]+");
    return capitalizeStringArrToString(arr);
  }

  private String generateRestParamsMethodChain(List<Parameter> params) {
    StringBuilder result = new StringBuilder();
    params
        .stream()
        .filter(p -> p.name.matches("^(?!productId$|entityId$|tenantId$).*"))
        .forEach(
            p -> {
              // if body param, add body param method
              if (p.in.equalsIgnoreCase("body")) {
                result.append(BODY_TEMPLATE);
              }
              // handle queryParam
              else if (p.in.equalsIgnoreCase("query")) {
                result.append(String.format(QUERY_PARAM_TEMPLATE, p.name, p.name));
              }
              // handle formData
              else if (p.in.equalsIgnoreCase("formData")) {
                if (p.type.equalsIgnoreCase("file")) {
                  result.append(String.format(FORM_DATA_TEMPLATE_, p.name, p.name, ", mime"));
                } else {
                  result.append(String.format(FORM_DATA_TEMPLATE_, p.name, p.name, ""));
                }
              }
              // else treat param as a path param
              else {
                result.append(String.format(PATH_PARAM_TEMPLATE, p.name, p.name));
              }
            });
    return result.toString();
  }

  private String generateBeforeReturnStatementCode(ApiCallMethod apiCallMethod) {
    String result = "";
    if (apiCallMethod.consumes.contains("multipart")) {
      result = String.format(BEFORE_RETURN_STATEMENT, GET_FILE_MIME_TYPE);
    }
    return result;
  }

  private String capitalizeAndCleanString(String stringToClean) {
    return WordUtils.capitalizeFully(stringToClean)
        // remove whitespace
        .replaceAll("\\s+", "")
        // remove periods
        .replaceAll("\\.", "")
        // remove apostrophes
        .replaceAll("'", "")
        // remove hyphens
        .replaceAll("-", "");
  }
}
