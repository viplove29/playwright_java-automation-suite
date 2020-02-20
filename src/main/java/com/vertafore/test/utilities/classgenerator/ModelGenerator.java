//package com.vertafore.test.utilities.classgenerator;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import groovyjarjarcommonscli.MissingArgumentException;
//import io.restassured.response.Response;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import net.serenitybdd.rest.SerenityRest;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//
//public class ModelGenerator {
//
//  static final String MODEL_CLASS_TEMPLATE =
//      "\n@JsonInclude(JsonInclude.Include.NON_NULL)\n";
//  static final String MODEL_SETTER_METHOD_DEFINTION_TEMPLATE = "this.%s = %s;";
//  static final String MODEL_GETTER_METHOD_DEFINITION_TEMPLATE = "return this.%s;";
//  static final String MODEL_PACKAGE_TEMPLATE = "package com.vertafore.test.models";
//
//  static final String MODEL_DEFAULT_PATH = "./src/main/java/com/vertafore/test/models/%s/";
//
//  @JsonIgnoreProperties(ignoreUnknown = true)
//  public static class Model {
//    public String name;
//    public List<Field> fields;
//  }
//
//  @JsonIgnoreProperties(ignoreUnknown = true)
//  public static class Field {
//    public String name;
//    public String type;
//    public String format;
//    public String $ref;
//  }
//
//  public static void main(String[] args) throws ParseException, MissingArgumentException {
//    if (args.length == 0) {
//      throw new MissingArgumentException("must provide service arguments when running this tasks");
//    }
//    for (String service : args) {
//      Response response = SerenityRest.get(String.format(SWAGGER_API_URL, service, service));
//      String json = response.getBody().asString();
//      generateModelClasses(json);
//    }
//  }
//
//  public static void generateModelClasses(String json) throws ParseException {
//    JSONObject swaggerJson = (JSONObject) new JSONParser().parse(json);
//    String servicePath = (String) swaggerJson.get("basePath");
//
//    String basePath = String.format(MODEL_DEFAULT_PATH, generatePackageNameFromPath(servicePath));
//
//    generateBaseDirectory(basePath);
//    List<Model> models = convertModelsMapToModelsList((Map) swaggerJson.get("definitions"));
//    models.forEach(
//        m -> {
//          StringBuilder fieldResults = new StringBuilder();
//          StringBuilder setterGetterResults = new StringBuilder();
//
//          // another forEach here soon
//          m.fields
//              .stream()
//              .forEach(
//                  f -> {
//                    String dataType = generateFieldType(f);
//                    fieldResults.append(String.format(MODEL_FIELD_TEMPLATE, dataType, f.name));
//                    setterGetterResults
//                        .append(generateGetters(dataType, f.name))
//                        .append(generateSetters(dataType, f.name))
//                        .append("\n");
//                  });
//
//          String packageName = generatePackageNameFromPath(servicePath);
//          String fileContent = generatePackageAndImports(packageName, fieldResults.toString());
//
//          fileContent +=
//              String.format(
//                  MODEL_CLASS_TEMPLATE,
//                  m.name,
//                  fieldResults.toString() + "\n" + setterGetterResults.toString());
//
//          String newPath = basePath + m.name + ".java";
//          generateBaseFile(newPath);
//          writeUsingBufferedWriter(newPath, fileContent);
//        });
//  }
//
//  private static String generatePackageAndImports(String packageName, String fieldResults) {
//    StringBuilder result = new StringBuilder(String.format(MODEL_PACKAGE_TEMPLATE, packageName));
//
//    result.append(
//        String.format(IMPORT_STATEMENT_TEMPLATE, "com.fasterxml.jackson.annotation.JsonInclude"));
//
//    if (fieldResults.contains("List<")) {
//      result.append(String.format(IMPORT_STATEMENT_TEMPLATE, "java.util.List"));
//    }
//    if (fieldResults.contains(" Instant ")) {
//      result.append(String.format(IMPORT_STATEMENT_TEMPLATE, "java.time.Instant"));
//    }
//    return result.toString();
//  }
//
//  private static String generateGetters(String dataType, String field) {
//    String methodName =
//        dataType.equalsIgnoreCase("boolean") ? field : "get" + capitalizeString(field);
//    return String.format(MODEL_GETTER_METHOD_DEFINITION_TEMPLATE, dataType, methodName, field);
//  }
//
//  private static String generateSetters(String dataType, String field) {
//    return String.format(
//            MODEL_SETTER_METHOD_DEFINTION_TEMPLATE,
//        capitalizeString(field.replaceAll("is", "")),
//        dataType,
//        field,
//        field,
//        field);
//  }
//
//  private static String generateFieldType(Field field) {
//    String result = null;
//    boolean formatPresent = field.format != null && !field.format.isEmpty();
//    boolean typePresent = field.type != null && !field.type.isEmpty();
//
//    if (!typePresent) {
//      if (field.$ref != null) {
//        result = field.$ref.replace("#/definitions/", "");
//      }
//    } else if (field.type.equalsIgnoreCase("string")) {
//      if (formatPresent && field.format.equalsIgnoreCase("date-time")) {
//        result = "Instant";
//      } else {
//        result = "String";
//      }
//    } else if (field.type.equalsIgnoreCase("boolean")) {
//      result = "Boolean";
//    } else if (field.type.equalsIgnoreCase("integer")) {
//      result = "Integer";
//    } else if (field.type.equalsIgnoreCase("number")) {
//      result = "Double";
//    } else if (field.type.equalsIgnoreCase("array")) {
//      result = "List<" + field.$ref.replaceAll("#/definitions/", "") + ">";
//    } else {
//      throw new IllegalArgumentException(
//          "could not determine or have not set up logic to handle type: "
//              + field.type
//              + " or format: "
//              + field.format);
//    }
//    return result;
//  }
//
//  private static List<Model> convertModelsMapToModelsList(Map models) {
//    List<Model> results = new ArrayList<>();
//    models
//        .keySet()
//        .stream()
//        .filter(
//            m ->
//                !((String) m)
//                    .matches(
//                        "JsonPatch.*|LimitOffsetPagingInfoV1.*|PagedResponseV1.*|SingleResponseV1.*|EmptyResponseV1.*|ErrorResponseV1.*"))
//        .forEach(
//            m -> {
//              Model nextModel = new Model();
//              nextModel.name = (String) m;
//              Map modelJson = (Map) models.get(m);
//              nextModel.fields = convertFieldsMapToFieldsList((Map) modelJson.get("properties"));
//              results.add(nextModel);
//            });
//    return results;
//  }
//
//  private static List<Field> convertFieldsMapToFieldsList(Map fields) {
//    List<Field> results = new ArrayList<>();
//    fields
//        .keySet()
//        .forEach(
//            f -> {
//              Field nextField = new Field();
//              Map fieldJson = (Map) fields.get(f);
//              nextField.name = (String) f;
//              nextField.type = (String) fieldJson.get("type");
//              nextField.format = (String) fieldJson.get("format");
//              nextField.$ref =
//                  (String)
//                      (fieldJson.get("items") == null
//                          ? fieldJson.get("$ref")
//                          : ((Map) fieldJson.get("items")).get("$ref"));
//              results.add(nextField);
//            });
//    return results;
//  }
//}
