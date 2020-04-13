package generators

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.Operation
import io.swagger.v3.oas.models.PathItem;
import org.openapitools.codegen.*;
import org.openapitools.codegen.languages.SpringCodegen;


import io.swagger.models.properties.*
import org.openapitools.codegen.utils.URLPathUtils;

import java.util.*;
import java.io.File;

public class ScreenPlay extends SpringCodegen implements CodegenConfig {

    // source folder where to write the files
    protected String sourceFolder = "";
    protected String apiVersion = "1.0.0";

    protected String basePackage = "com.vertafore.test.tasks.servicewrappers";
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
        return "titanservicewrappers";
    }

    /**
     * Returns human-friendly help for the generator.  Provide the consumer with help
     * tips, parameters here
     *
     * @return A string value for the help message
     */
    public String getHelp() {
        return "Generates screenplay service-wrappers for Titan";
    }

    public ScreenPlay() {
        super();

        outputFolder = "";

        apiTemplateFiles.put("api.mustache", ".java");

        //	supportingFiles.add(new SupportingFile("build.mustache", "", "build.gradle"));
        //	supportingFiles.add(new SupportingFile("plugin.mustache", (sourceFolder + File.separator + basePackage).replace(".", java.io.File.separator), "SparkServerPlugin.java"));

//        embeddedTemplateDir = templateDir = "spark";

        apiPackage = "com.vertafore.test.tasks.codegen.api";
        modelPackage = "com.vertafore.test.tasks.codegen.model";

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

        /**
         * Supporting Files.  You can write single files for the generator with the
         * entire object tree available.  If the input file has a suffix of `.mustache
         * it will be processed by the template engine.  Otherwise, it will be copied

         supportingFiles.add(new SupportingFile("myFile.mustache",   // the input template or file
         "",                                                       // the destination folder, relative `outputFolder`
         "myFile.sample")                                          // the output file
         );*/

        supportingFiles.add(new SupportingFile("api.mustache",   // the input template or file
                "/templates",                                                       // the destination folder, relative `outputFolder`
                "api.mustache"));
    }

    @Override
    public String escapeReservedWord(String name) {
        return "_" + name;
    }

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
    Map<String, Object> postProcessOperationsWithModels(Map<String, Object> objs, List<Object> allModels) {
        Map<String, Object> endpoints = (Map<String, Object>) objs.get("operations");
        List<CodegenOperation> ops = (List<CodegenOperation>) endpoints.get("operation");
        for (CodegenOperation op : ops) {
            // Convert httpMethod to lower case, e.g. "get", "post"
            op.httpMethod = op.httpMethod.toLowerCase();

            // replace all '{?' -> end of path string with "" to remove query strings from path
            op.path = op.path.replaceAll(	'\\{[\\?].*$', "");
            // replace all '?' -> end of path string with "" to remove query strings from path
            op.path = op.path.replaceAll(	'[\\?].*$', "");
            // Change path parameters {var} to :var
//            op.path = op.path.replaceAll("\\{", ":");
//            op.path = op.path.replaceAll("\\}", "");


        }

        return super.postProcessOperationsWithModels(endpoints, allModels)
    }

    @Override
    public String toApiName(String name) {
        return name;
    }
}

