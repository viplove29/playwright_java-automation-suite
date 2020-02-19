package com.vertafore.test.utilities.classgenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import com.vertafore.core.util.JsonHelper;
import groovyjarjarcommonscli.MissingArgumentException;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.serenitybdd.rest.SerenityRest;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ServiceWrapperGenerator extends ClassGenerator {

  static final String SERVICE_WRAPPER_PACKAGE_AND_IMPORT_TEMPLATE =
      "package com.vertafore.test.tasks.servicewrappers.%s;\n\n"
          + "import static com.vertafore.test.abilities.CallTitanApi.as;\n"
          + "import static net.serenitybdd.rest.SerenityRest.rest;\n"
          + "import net.serenitybdd.screenplay.Performable;\n"
          + "import net.serenitybdd.screenplay.Task;\n\n";

  static final String SERVICE_WRAPPER_CLASS_TEMPLATE = "public class Use%sServiceTo {\n\n%s}";
  static final String SERVICE_WRAPPER_METHOD_TEMPLATE =
      "\tpublic static Performable %s(%s){\n"
          + "\t\treturn Task.where(\n\t\t\"{0} %s\", \n\t\t\tactor -> {\n\t\t\t\t%s\n\t\t\t}\n\t\t)"
          + ";\n\t}\n\n";

  static final String REST_CALL_METHOD_CHAIN_TEMPLATE =
      "rest().with().%s%s%s(as(actor).toEndpoint(%s));";
  static final String CONTENT_TYPE_TEMPLATE = "contentType(\"%s\").";
  static final String FORM_DATA_TEMPLATE_ = "multiPart(\"%s\", %s).";
  static final String PATH_PARAM_TEMPLATE = "pathParam(\"%s\", %s).";
  static final String QUERY_PARAM_TEMPLATE = "queryParam(\"%s\", %s).";
  static final String BODY_TEMPLATE = "body(body).";

  static final String SERVICE_WRAPPER_DEFAULT_PATH =
      "./src/main/java/com/vertafore/test/tasks/servicewrappers/%s/";
  static final String SERVICE_WRAPPER_FILE_NAME_TEMPLATE = "Use%sServiceTo.java";

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
    for (String service : args) {
      Response response = SerenityRest.get(String.format(SWAGGER_API_URL, service, service));
      String json = response.getBody().asString();
      generateServiceWrapperClass(json);
    }
  }

  public static void generateServiceWrapperClass(String json) throws ParseException {
    StringBuilder constantsResults = new StringBuilder();
    StringBuilder methodResults = new StringBuilder();
    StringBuilder importsResults = new StringBuilder();

    JSONObject swaggerJson = (JSONObject) new JSONParser().parse(json);

    String servicePath = (String) swaggerJson.get("basePath");
    List<Path> paths = convertPathsMapToPathsList((Map) swaggerJson.get("paths"));

    importsResults.append(
        String.format(
            SERVICE_WRAPPER_PACKAGE_AND_IMPORT_TEMPLATE, generatePackageNameFromPath(servicePath)));

    paths.forEach(
        p -> {
          p.apiCallMethods.forEach(
              ac -> {
                constantsResults.append(
                    String.format(STRING_CONSTANT_TEMPLATE, ac.endpointName, p.endpoint));

                String restCallMethodChain =
                    String.format(
                        REST_CALL_METHOD_CHAIN_TEMPLATE,
                        String.format(CONTENT_TYPE_TEMPLATE, ac.consumes),
                        ac.restParamMethodChain,
                        ac.restVerb,
                        ac.endpointName);

                methodResults.append(
                    String.format(
                        SERVICE_WRAPPER_METHOD_TEMPLATE,
                        ac.methodName,
                        ac.methodArguments,
                        ac.summary,
                        restCallMethodChain));

                importsResults.append(generateImports(importsResults.toString(), ac.parameters));
              });
        });

    String className = generateClassName(servicePath);

    String fileContent =
        importsResults.toString()
            + "\n"
            + String.format(
                SERVICE_WRAPPER_CLASS_TEMPLATE,
                className,
                constantsResults.toString() + "\n" + methodResults.toString());

    String basePath =
        String.format(SERVICE_WRAPPER_DEFAULT_PATH, generatePackageNameFromPath(servicePath));

    generateBaseDirectory(basePath);

    basePath += String.format(SERVICE_WRAPPER_FILE_NAME_TEMPLATE, className);

    generateBaseFile(basePath);
    writeUsingBufferedWriter(basePath, fileContent);
  }

  private static List<Path> convertPathsMapToPathsList(Map paths) {
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

  private static List<ApiCallMethod> convertApiCallsMapToApiCallsList(Map apiCalls) {
    List<ApiCallMethod> results = new ArrayList<>();
    apiCalls
        .keySet()
        .forEach(
            ac -> {
              Map call = (Map) apiCalls.get(ac);
              ApiCallMethod nextApiCallMethod = new ApiCallMethod();

              nextApiCallMethod.restVerb = (String) ac;

              nextApiCallMethod.summary = (String) call.get("summary");

              nextApiCallMethod.endpointName =
                  generateEndpointConstantName(nextApiCallMethod.summary);

              nextApiCallMethod.methodName = generateMethodName(nextApiCallMethod.summary);

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

  private static String generateImports(String importsResults, List<Parameter> params) {
    StringBuilder result = new StringBuilder();
    params.forEach(
        p -> {
          if (p.type != null) {
            if (p.type.equalsIgnoreCase("file") && !importsResults.contains("java.io.File;")) {
              result.append(String.format(IMPORT_STATEMENT_TEMPLATE, "java.io.File"));
            }
          }
        });
    return result.toString();
  }

  private static String generateEndpointConstantValue(String path) {
    return path.replaceAll("^.*\\{entityId}/", "").replaceAll("\\{\\?.*", "");
  }

  private static String generateEndpointConstantName(String summary) {
    return summary
        .toUpperCase()
        .trim()
        .replaceAll("[^A-z_ ]+", "")
        .replaceAll("\\s", "_")
        .replaceAll("UPDATEPATCH", "PATCH");
  }

  private static String generateMethodName(String summary) {
    StringBuilder result = new StringBuilder();
    String[] arr =
        summary
            .toLowerCase()
            .trim()
            .replaceAll("Update/Patch", "patch")
            .replaceAll("[^A-z]", " ")
            .split("\\s");
    result.append(capitalizeStringArrToString(arr));
    result.setCharAt(0, Character.toLowerCase(result.charAt(0)));
    return result.toString().replaceAll("update/patch", "patch").replaceAll("\\.", "");
  }

  private static String generateMethodArguments(List<Parameter> params) {
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

  private static String generateClassName(String path) {
    String[] arr = path.toLowerCase().replaceAll("/", "").trim().split("[^A-z]+");
    return capitalizeStringArrToString(arr);
  }

  private static String generateRestParamsMethodChain(List<Parameter> params) {
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
                result.append(String.format(FORM_DATA_TEMPLATE_, p.name, p.name));
              }
              // else treat param as a path param
              else {
                result.append(String.format(PATH_PARAM_TEMPLATE, p.name, p.name));
              }
            });
    return result.toString();
  }
}
