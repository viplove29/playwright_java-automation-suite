package com.vertafore.test.utilities;

import java.util.ArrayList;
import java.util.List;

public class ServiceWrapperMethodGenerator {
    static final String methodFormat = "public static Performable %s(%s){\n" +
            "\treturn Task.where(\n\t\t\"{0} %s\", \n\t\t\tactor -> {\n\t\t\t\t%s;\n\t\t\t}\n\t)" +
        ";\n}\n\n";

    static final String start = "rest().with().";
    static final String pathParam = "pathParam(%s).";
    static final String body = "body(body).";
    static final String get = "get(as(actor).toEndpoint(%s))";
    static final String post = "post(as(actor).toEndpoint(%s))";
    static final String patch = "patch(as(actor).toEndpoint(%s))";
    static final String put = "put(as(actor).toEndpoint(%s))";
    static final String delete = "delete(as(actor).toEndpoint(%s))";

    static final String matcher = ".*\\{.*} .*";

    public static void generateMethods(){
        List<ServiceWrapperMethod> methods = new ArrayList<>();

        methods.add(new ServiceWrapperMethod("POST_CHARGE", "/charges", "POST", "creates a charge"));
        methods.add(new ServiceWrapperMethod("GET_CHARGE_BY_ID", "/{id}/stuff/{id2}", "GET", "gets a charge by id"));
        methods.add(new ServiceWrapperMethod("DELETE_CHARGE_BY_ID", "/{id}", "DELETE", "deletes a charge by id"));

        String constantsResults = "";
        String methodResults = "";
        for(ServiceWrapperMethod m : methods){
            constantsResults += "private static final String " + m.name + " = \"" + m.endpoint + "\";\n";
            String args = getMethodArgumentsFromEndPoint(m.endpoint);
            boolean needsABodyArg = m.type.equalsIgnoreCase("post") || m.type.equalsIgnoreCase("put");
            args += needsABodyArg ? "Object body" : "";
            String restCall = getRestCall(m.type, getRestPathParamsFromEndPoint(m.endpoint), m.name);
            methodResults += String.format(methodFormat, getCamalCaseMethodName(m.name), args, m.description, restCall);
        }
        System.out.println("\n" + constantsResults + "\n" + methodResults);
    }

    private static String getCamalCaseMethodName(String str){
        String result = "";
        String[] strArr = str.split("_");
        for(int i = 0; i < strArr.length; i++){
            if (i == 0){
                result += strArr[i].toLowerCase();
            }
            else {
                String s = strArr[i].toLowerCase();
                char c = s.charAt(0);
                result += Character.toUpperCase(c) + (s.length() > 1 ? s.substring(1) : "");
            }
        }
        return result;
    }

    private static String getMethodArgumentsFromEndPoint(String endpoint){
        String[] arr = endpoint.split("/");
        String result = "";
        for(int i = 0; i < arr.length; i++){
            if(arr[i].contains("{")){
                String r = arr[i].replaceAll("\\{", "String ");

                r = r.replaceAll("}", i < arr.length - 1 ? ", " : "");

                result += r;
            }
        }
        return result;
    }

    private static String getRestPathParamsFromEndPoint(String endpoint){
        String[] arr = endpoint.split("/");
        String result = "";
        for(int i = 0; i < arr.length; i++){
            if(arr[i].contains("{")){
                String part1 = arr[i].replaceAll("\\{", "\"");
                part1 = part1.replaceAll("}", "\"");

                String part2 = arr[i].replaceAll("\\{", "");
                part2 = part2.replaceAll("}", "");
                result += String.format(pathParam, part1 + ", " + part2);
            }
        }
        return result;
    }

    private static String getRestCall(String type, String params, String endpoint){
        params = params.replaceAll("Object body,", "");
        String[] arr = endpoint.split("/");
        String result = "";
        if(type.equalsIgnoreCase("get")){
            if(params.isEmpty()){
                result = start + String.format(get, endpoint);
            }
            else {
                result = start + params + String.format(get, endpoint);
            }
        }
        else if(type.equalsIgnoreCase("delete")){
            if(params.isEmpty()){
                result = start + String.format(delete, endpoint);
            }
            else {
                result = start + params + String.format(delete, endpoint);
            }
        }
        else if(type.equalsIgnoreCase("put")){
            if(params.isEmpty()){
                result = start + body + String.format(put, endpoint);
            }
            else {
                result = start + body + params + String.format(put, endpoint);
            }
        }
        else if(type.equalsIgnoreCase("post")){
            if(params.isEmpty()){
                result = start + body + String.format(post, endpoint);
            }
            else {
                result = start + body + params + String.format(post, endpoint);
            }
        }
        else if(type.equalsIgnoreCase("patch")){
            if(params.isEmpty()){
                result = start + body + String.format(patch, endpoint);
            }
            else {
                result = start + body + params + String.format(patch, endpoint);
            }
        }
        else {
            throw new IllegalArgumentException("set the type right, fool");
        }
        return result;
    }

}

class ServiceWrapperMethod {
    String name;
    String endpoint;
    String type;
    String description;

    public ServiceWrapperMethod(String name, String endpoint, String type, String description){
        this.name = name;
        this.endpoint = endpoint;
        this.type = type;
        this.description = description;
    }
}
