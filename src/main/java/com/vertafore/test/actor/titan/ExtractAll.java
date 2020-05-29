// package com.vertafore.test.actor.titan;
//
// import static net.serenitybdd.rest.SerenityRest.rest;
//
// import io.restassured.builder.RequestSpecBuilder;
//
// public class ExtractAll {
//  private static RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
//  private static String TOKEN = "n729GYvEU6qM2qqWvCQg";
//
//  static {
//    requestSpecBuilder.addHeader("PRIVATE-TOKEN", TOKEN);
//    requestSpecBuilder.setBaseUri("https://git.util.v4af.com");
//    requestSpecBuilder.setBasePath("/api/v4/projects/{PROJECT_ID}/repository/files/{PATH}/raw");
//    requestSpecBuilder.addParam("ref", "master");
//  }
//
//  protected static String titanUsers(String projectId, String path) {
//
//    requestSpecBuilder.addPathParam("PATH", path);
//    requestSpecBuilder.addPathParam("PROJECT_ID", projectId);
//
//    return rest()
//        .given()
//        .spec(requestSpecBuilder.build())
//        .when()
//        .get()
//        .then()
//        .extract()
//        .response()
//        .asString();
//  }
// }
