package generators

import com.google.common.base.CaseFormat
import org.apache.commons.lang3.text.WordUtils
import org.openapitools.codegen.*;
import org.openapitools.codegen.languages.SpringCodegen;


public class ScreenPlay extends SpringCodegen implements CodegenConfig {

    // source folder where to write the files
    protected String sourceFolder = "";
    protected String apiVersion = "1.0.0";

//    protected String basePackage = "com.vertafore.test.tasks.servicewrappers";
    protected String baseName = "ServiceWrapperPlugin";

    /**
     * Configures the type of generator.
     *
     * @return the CodegenType for this generator
     */
    public CodegenType getTag() {
        return CodegenType.CLIENT;
    }

    /**
     * Configures a friendly name for the generator.  This will be used by the generator
     * to select the library with the -l flag.
     *
     * @return the friendly name for the generator
     */
    public String getName() {
        return "emsservicewrappers";
    }

    /**
     * Returns human-friendly help for the generator.  Provide the consumer with help
     * tips, parameters here
     *
     * @return A string value for the help message
     */
    public String getHelp() {
        return "Generates screenplay service-wrappers for Ems";
    }

    public ScreenPlay() {
        super();

        outputFolder = "";

        setReservedWordsLowerCase(
                Arrays.asList(
                        // used as internal variables, can collide with parameter names
                        "localVarPath", "localVarQueryParams", "localVarHeaderParams", "localVarFormParams",
                        "localVarPostBody", "localVarAccepts", "localVarAccept", "localVarContentTypes",
                        "localVarContentType", "localVarAuthNames", "localReturnType",
                        "ApiClient", "ApiException", "ApiResponse", "Configuration", "StringUtil",

                        // language reserved words
                        "abstract", "continue", "for", "new", "switch", "assert",
                        "default", "if", "package", "synchronized", "boolean", "do", "goto", "private",
                        "this", "break", "double", "implements", "protected", "throw", "byte", "else",
                        "import", "public", "throws", "case", "enum", "instanceof", "return", "transient",
                        "catch", "extends", "int", "short", "try", "char", "final", "interface", "static",
                        "void", "class", "finally", "long", "strictfp", "volatile", "const", "float",
                        "native", "super", "while")
        );

        languageSpecificPrimitives = new HashSet<String>(
                Arrays.asList(
                        "String",
                        "boolean",
                        "Boolean",
                        "Double",
                        "Integer",
                        "Long",
                        "Float",
                        "Object",
                        "byte[]")
        );

        instantiationTypes.put("array", "ArrayList");
        instantiationTypes.put("map", "HashMap");
        typeMapping.put("date", "Date");
        typeMapping.put("file", "File");
        typeMapping.put("UUID", "String");

        additionalProperties.put("apiVersion", apiVersion);
        additionalProperties.put("basePackage", basePackage);
        additionalProperties.put("baseName", baseName);
        singleContentTypes = true;

        /**
         *      commenting out for now, this is happening in our build.gradle config.
         *
         * Supporting Files.  You can write single files for the generator with the
         * entire object tree available.  If the input file has a suffix of `.mustache
         * it will be processed by the template engine.  Otherwise, it will be copied

         supportingFiles.add(new SupportingFile("myFile.mustache",   // the input template or file
         "",                                                       // the destination folder, relative `outputFolder`
         "myFile.sample")                                          // the output file
         );*/
    }

    /**
     *      commenting out for now
     *
    */
//    @Override
//    public String escapeReservedWord(String name) {
//        return "_" + name;
//    }

    /**
     * Location to write model files.  You can use the modelPackage() as defined when the class is
     * instantiated
     */
    public String modelFileFolder() {
        return outputFolder + "/" + sourceFolder + "/" + modelPackage();
    }

    /**
     * Location to write api files.  You can use the apiPackage() as defined when the class is
     * instantiated
     */
    @Override
    public String apiFileFolder() {
        return outputFolder + "/" + sourceFolder + "/" + apiPackage();
    }

    @Override
    void processOpts() {
        super.processOpts()
        this.typeMapping.put("file", "File");
        this.importMapping.put("File", "java.io.File");
    }

    @Override
    Map<String, Object> postProcessOperationsWithModels(Map<String, Object> objs, List<Object> allModels) {
        Map<String, Object> endpoints = (Map<String, Object>) objs.get("operations");
        List<CodegenOperation> ops = (List<CodegenOperation>) endpoints.get("operation");
        for (CodegenOperation op : ops) {
            // Convert httpMethod from "GET" to "Get" to use in serenity-screenplay static ex: "Get" methods.
            op.httpMethod = op.httpMethod.toLowerCase().capitalize();

            // This is a hack to normalize our paths from swagger - we put query strings in the paths
            // and that is a violation of swagger/openAPI spec. So this removes them.

            // replace all '{?' -> end of path string with "" to remove query strings from path
            op.path = op.path.replaceAll(	'\\{[\\?].*$', "");
            // replace all '?' -> end of path string with "" to remove query strings from path
            op.path = op.path.replaceAll(	'[\\?].*$', "");

            // clean and normalize the operationId to remove duplicates and unhelpful names.
            op.operationId = generateMethodName(op.operationId, op.tags.get(0), op.summary, op.isDeprecated)

            // HACK bc swagger for EMS is terrible.
            op.summary = op.summary
                    .replace("\"", "")
                    .replace("\\r\\n", "")
                    .replace("\\", "")
            .replace("\'", "");
        }

        return super.postProcessOperationsWithModels(endpoints, allModels)
    }

    @Override
    Map<String, Object> updateAllModels(Map<String, Object> objs) {
        if(objs.containsKey("ObjectNode")){
            objs.remove("ObjectNode")
        }
        else if (objs.containsKey("JsonNode")){
                objs.remove("JsonNode")
            }
        return super.updateAllModels(objs)
    }

    @Override
    public String toApiName(String name) {
        if (name.contains("-")){
            name = CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_CAMEL, name);
        }
        return "Use" + handleProblematicEndpoints(name).capitalize() + "To";
    }

    private static String generateMethodName(String operationId, Object tag, String summary, Boolean deprecated) {
        String rawControllerName = tag.name ? tag.name : tag.description;
                String controllerName = "";
                String isDeprecated = "";

                    // we have to format the name of the controller
                    controllerName = capitalizeAndCleanString(rawControllerName);

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
                if (result.matches(".*\\d.*")) {
                    def cleanedSummary = capitalizeAndCleanString(summary)
                    controllerName = WordUtils.uncapitalize(cleanedSummary);
                }

                else if (deprecated) {
                    isDeprecated = "Deprecated";
                }

                String newResult = result.substring(0, 1).toUpperCase() + result.substring(1);

                return newResult + "OnThe" + controllerName + "Controller" + isDeprecated;
            }

    private static String capitalizeAndCleanString(String stringToClean) {
        return WordUtils.capitalizeFully(stringToClean)
        // remove whitespace
                .replaceAll("\\s+", "")
        // remove periods
                .replaceAll("\\.", "")
        // remove apostrophes
                .replaceAll("'", "")
        // remove hyphens
                .replaceAll("-", "")
        // remove parentheses
                .replaceAll("[()]","")
        // remove colons
                .replaceAll(":","")
        // remove forward slashes
                .replaceAll("/","");

    }

    private static String handleProblematicEndpoints(String name) {
        switch(name) {
            case "errorLogs":
                return "errorLogsDuplicate";
            default:
                return name;
        }
    }
}

