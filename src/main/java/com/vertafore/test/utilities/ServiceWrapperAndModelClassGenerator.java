package com.vertafore.test.utilities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import com.vertafore.core.util.JsonHelper;
import io.restassured.response.Response;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import net.serenitybdd.rest.SerenityRest;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ServiceWrapperAndModelClassGenerator {

  static final Response response =
      SerenityRest.get(
          "https://api.dev.titan.v4af.com/accounting/v2/api-docs?group=accounting-service");
  static final String json = response.getBody().asString();

  static final String SERVICE_WRAPPER_PACKAGE_AND_IMPORT_TEMPLATE =
      "package com.vertafore.test.tasks.servicewrappers.%s;\n\n"
          + "import static com.vertafore.test.abilities.CallTitanApi.as;\n"
          + "import static net.serenitybdd.rest.SerenityRest.rest;\n"
          + "import net.serenitybdd.screenplay.Performable;\n"
          + "import net.serenitybdd.screenplay.Task;\n\n";

  static final String SERVICE_WRAPPER_CLASS_TEMPLATE = "public class Use%sServiceTo {\n\n%s}";
  static final String CONSTANT_TEMPLATE = "\tprivate static final String %s = \"%s\";\n";
  static final String SERVICE_WRAPPER_METHOD_TEMPLATE =
      "\tpublic static Performable %s(%s){\n"
          + "\t\treturn Task.where(\n\t\t\"{0} %s\", \n\t\t\tactor -> {\n\t\t\t\t%s\n\t\t\t}\n\t\t)"
          + ";\n\t}\n\n";

  static final String REST_CALL_METHOD_CHAIN_TEMPLATE =
      "rest().with().%s%s(as(actor).toEndpoint(%s));";
  static final String PATH_PARAM_TEMPLATE = "pathParam(\"%s\", %s).";
  static final String QUERY_PARAM_TEMPLATE = "queryParam(\"%s\", %s).";
  static final String BODY_TEMPLATE = "body(body).";
  static final String SERVICE_WRAPPER_DEFAULT_PATH =
      "./src/main/java/com/vertafore/test/tasks/servicewrappers/%s/";
  static final String SERVICE_WRAPPER_FILE_NAME_TEMPLATE = "Use%sServiceTo.java";

  static final String MODEL_CLASS_TEMPLATE =
      "\n@JsonInclude(JsonInclude.Include.NON_NULL)\npublic class %s {\n\n%s}";
  static final String MODEL_SETTER_METHOD_TEMPLATE =
      "\tpublic void set%s(%s %s) {\n\t\tthis.%s = %s;\n\t}\n";
  static final String MODEL_GETTER_METHOD_TEMPLATE =
      "\tpublic %s %s() {\n\t\treturn this.%s;\n\t}\n";
  static final String MODEL_FIELD_TEMPLATE = "\tprivate %s %s;\n";
  static final String MODEL_PACKAGE_TEMPLATE = "package com.vertafore.test.models.%s;\n\n";
  static final String IMPORT_TEMPLATE = "import %s;\n";
  static final String MODEL_DEFAULT_PATH = "./src/main/java/com/vertafore/test/models/%s/";

  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Parameter {
    public String name;
    public String in;
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class ApiCallMethod {
    public String type;
    public String summary;
    public String[] consumes;
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

  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Model{
    public String name;
    public List<Field> fields;
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Field{
    public String name;
    public String type;
    public String format;
    public String $ref;
  }

  public static void generateServiceWrapperClasses() throws ParseException {

    StringBuilder constantsResults = new StringBuilder();
    StringBuilder methodResults = new StringBuilder();

    JSONObject swaggerJson = (JSONObject) new JSONParser().parse(json);

    String servicePath = (String) swaggerJson.get("basePath");
    List<Path> paths = convertPathsMapToPathsList((Map) swaggerJson.get("paths"));

    paths.forEach(
        p -> {
          p.apiCallMethods.forEach(
              ac -> {
                constantsResults.append(
                    String.format(CONSTANT_TEMPLATE, ac.endpointName, p.endpoint));

                String restCallMethodChain =
                    String.format(
                        REST_CALL_METHOD_CHAIN_TEMPLATE,
                        ac.restParamMethodChain,
                        ac.type,
                        ac.endpointName);

                methodResults.append(
                    String.format(
                        SERVICE_WRAPPER_METHOD_TEMPLATE,
                        ac.methodName,
                        ac.methodArguments,
                        ac.summary,
                        restCallMethodChain));
              });
        });

    String className = generateServiceWrapperClassNameFromPath(servicePath);
    String packageAndImports =
        String.format(
            SERVICE_WRAPPER_PACKAGE_AND_IMPORT_TEMPLATE, generatePackageNameFromPath(servicePath));
    String fileContent =
        packageAndImports
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

  public static void generateModelClasses() throws ParseException {
        JSONObject swaggerJson = (JSONObject) new JSONParser().parse(json);
        String servicePath = (String) swaggerJson.get("basePath");

        String basePath =
            String.format(
                MODEL_DEFAULT_PATH,
                generatePackageNameFromPath(servicePath));

        generateBaseDirectory(basePath);
        List<Model> models = convertModelsMapToModelsList((Map) swaggerJson.get("definitions"));
        models.
                forEach(m -> {
                    StringBuilder fieldResults = new StringBuilder();
                    StringBuilder setterGetterResults = new StringBuilder();

                    //another forEach here soon
                    m.fields.stream().forEach(f -> {
                        String dataType = generateModelFieldType(f);
                        fieldResults.append(String.format(MODEL_FIELD_TEMPLATE, dataType, f.name));
                        setterGetterResults
                                .append(generateModelGetterMethod(dataType, f.name))
                                .append(generateModelSetterMethod(dataType, f.name))
                                .append("\n");
                    });

                    String packageName = generatePackageNameFromPath(servicePath);
                    String fileContent = generateModelPackageAndImports(packageName,
                            fieldResults.toString());

                    fileContent +=
                            String.format(
                                    MODEL_CLASS_TEMPLATE,
                                    m.name,
                                    fieldResults.toString() + "\n" + setterGetterResults.toString());

                    String newPath = basePath  + m.name + ".java";
                    generateBaseFile(newPath);
                    writeUsingBufferedWriter(newPath, fileContent);
                });
  }

  private static String generateModelPackageAndImports(String packageName, String fieldResults) {
    StringBuilder result = new StringBuilder(String.format(MODEL_PACKAGE_TEMPLATE, packageName));

    result.append(String.format(IMPORT_TEMPLATE, "com.fasterxml.jackson.annotation.JsonInclude"));

    if (fieldResults.contains("List<")) {
      result.append(String.format(IMPORT_TEMPLATE, "java.util.List"));
    }
    if (fieldResults.contains(" Instant ")) {
      result.append(String.format(IMPORT_TEMPLATE, "java.time.Instant"));
    }
    return result.toString();
  }

  private static String generateModelGetterMethod(String dataType, String field) {
    String methodName =
        dataType.equalsIgnoreCase("boolean") ? field : "get" + capitalizeString(field);
    return String.format(MODEL_GETTER_METHOD_TEMPLATE, dataType, methodName, field);
  }

  private static String generateModelSetterMethod(String dataType, String field) {
    return String.format(
        MODEL_SETTER_METHOD_TEMPLATE,
        capitalizeString(field.replaceAll("is", "")),
        dataType,
        field,
        field,
        field);
  }

  private static String generateModelFieldType(Field field) {
    String result = null;
    boolean formatPresent = field.format != null && !field.format.isEmpty();
    boolean typePresent = field.type != null && !field.type.isEmpty();

    if (!typePresent) {
      if (field.$ref != null) {
        result = field.$ref.replace("#/definitions/", "");
      }
    } else if (field.type.equalsIgnoreCase("string")) {
      if (formatPresent && field.format.equalsIgnoreCase("date-time")) {
        result = "Instant";
      } else {
        result = "String";
      }
    } else if (field.type.equalsIgnoreCase("boolean")) {
      result = "Boolean";
    } else if (field.type.equalsIgnoreCase("integer")) {
      result = "Integer";
    } else if (field.type.equalsIgnoreCase("number")) {
      result = "Double";
    } else if (field.type.equalsIgnoreCase("array")) {
      result =
          "List<"
              + field.$ref
                  .replaceAll("#/definitions/", "")
              + ">";
    } else {
      throw new IllegalArgumentException(
          "could not determine or have not set up logic to handle type: "
              + field.type
              + " or format: "
              + field.format);
    }
    return result;
  }

  private static String generateServiceWrapperEndpointFromPath(String path) {
    return path.replaceAll("^.*\\{entityId}/", "");
  }

  private static String generateServiceWrapperConstantNameFromSummary(String summary) {
    return summary
        .toUpperCase()
        .trim()
        .replaceAll("[^A-z_ ]+", "")
        .replaceAll("\\s", "_")
        .replaceAll("UPDATEPATCH", "PATCH");
  }

  private static String generateCamalCaseMethodNameFromSummary(String summary) {
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

  private static String generateServiceWrapperMethodParams(List<Parameter> params) {
    StringBuilder result = new StringBuilder();
    params.forEach(
        param -> {
          if (param.name.matches("^(?!productId$|entityId$|tenantId$).*")) {
            result.append(
                param.in.equalsIgnoreCase("body")
                    ? "Object body, "
                    : "String " + param.name + ", ");
          }
        });
    return result.toString().replaceAll(", $", "").replaceAll("\"", "");
  }

  private static String generateServiceWrapperClassNameFromPath(String path) {
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
              // if query param, add query param method
              else if (p.in.equalsIgnoreCase("query")) {
                result.append(String.format(QUERY_PARAM_TEMPLATE, p.name, p.name));
              }
              // else treat param as a path param
              else {
                result.append(String.format(PATH_PARAM_TEMPLATE, p.name, p.name));
              }
            });
    return result.toString();
  }

  private static String generatePackageNameFromPath(String path) {
    return path.replaceAll("[^A-z]+", "").toLowerCase();
  }

  private static String capitalizeStringArrToString(String[] arr) {
    StringBuilder result = new StringBuilder();
    Arrays.stream(arr)
        .forEach(
            word -> {
              result.append(capitalizeString(word));
            });
    return result.toString();
  }

  private static String capitalizeString(String str) {
    if (str.isEmpty()) {
      return str;
    } else if (str.length() == 1) {
      return Character.toUpperCase(str.charAt(0)) + "";
    } else if (str.length() >= 2) {
      return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    } else {
      throw new IllegalArgumentException("could not capitalize string " + str);
    }
  }

    private static List<Path> convertPathsMapToPathsList(Map paths) {
        List<Path> results = new ArrayList<>();
        paths
                .keySet()
                .forEach(
                        p -> {
                            Path nextPath = new Path();
                            nextPath.endpoint = generateServiceWrapperEndpointFromPath((String) p);
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

                            nextApiCallMethod.type = (String) ac;

                            nextApiCallMethod.summary = (String) call.get("summary");

                            nextApiCallMethod.endpointName =
                                    generateServiceWrapperConstantNameFromSummary(nextApiCallMethod.summary);

                            nextApiCallMethod.methodName =
                                    generateCamalCaseMethodNameFromSummary(nextApiCallMethod.summary);

                            //throws exception. fix when this is needed
//              nextApiCallMethod.consumes = (String[]) call.get("consumes");

                            nextApiCallMethod.parameters =
                                    JsonHelper.deserializeJsonAsList(
                                            call.get("parameters").toString(), new TypeReference<>() {});

                            nextApiCallMethod.methodArguments =
                                    generateServiceWrapperMethodParams(nextApiCallMethod.parameters);

                            nextApiCallMethod.restParamMethodChain =
                                    generateRestParamsMethodChain(nextApiCallMethod.parameters);

                            results.add(nextApiCallMethod);
                        });
        return results;
    }

    private static List<Model> convertModelsMapToModelsList(Map models){
        List<Model> results = new ArrayList<>();
        models.keySet()
                .stream()
                .filter(m -> !((String)m).matches("JsonPatch.*|LimitOffsetPagingInfoV1.*|PagedResponseV1.*|SingleResponseV1.*|EmptyResponseV1.*|ErrorResponseV1.*"))
                .forEach(m -> {
                    Model nextModel = new Model();
                    nextModel.name = (String) m;
                    Map modelJson = (Map) models.get(m);
                    nextModel.fields = convertFieldsMapToFieldsList((Map) modelJson.get("properties"));
                    results.add(nextModel);
                });
        return results;
    }

    private static List<Field> convertFieldsMapToFieldsList(Map fields) {
        List<Field> results = new ArrayList<>();
        fields.keySet().forEach(f -> {
            Field nextField = new Field();
            Map fieldJson = (Map) fields.get(f);
            nextField.name = (String) f;
            nextField.type = (String) fieldJson.get("type");
            nextField.format = (String) fieldJson.get("format");
            nextField.$ref = (String) (fieldJson.get("items") == null ? fieldJson.get("$ref") : ((Map)fieldJson.get("items")).get("$ref"));
            results.add(nextField);
        });
        return results;
    }

  private static void generateBaseDirectory(String basePath) {
    boolean result;
    String newPath = Paths.get(basePath).toAbsolutePath().normalize().toString();
    File file = new File(newPath); // initialize File object and passing new path as argument
    try {
      result = file.mkdir();
      System.out.println(
          result ? "directory successfully created" : "directory already exists at location");
    } catch (Exception e) {
      e.printStackTrace(); // prints exception if any
    }
  }

  private static void generateBaseFile(String basePath) {
    boolean result;
    String newPath = Paths.get(basePath).toAbsolutePath().normalize().toString();
    File file = new File(newPath); // initialize File object and passing path as argument
    try {
      result = file.createNewFile(); // makes a new file
      System.out.println(result ? "file successfully created" : "file already exists at location");
    } catch (Exception e) {
      e.printStackTrace(); // prints exception if any
    }
  }

  private static void writeUsingBufferedWriter(String path, String fileContent) {

    File file = new File(path);
    FileWriter fr = null;
    BufferedWriter br = null;
    String dataWithNewLine = fileContent + System.getProperty("line.separator");
    try {
      fr = new FileWriter(file);
      br = new BufferedWriter(fr);
      br.write(dataWithNewLine);

    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        br.close();
        fr.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
