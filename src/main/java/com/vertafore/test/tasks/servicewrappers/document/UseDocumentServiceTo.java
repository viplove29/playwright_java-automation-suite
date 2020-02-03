package com.vertafore.test.tasks.servicewrappers.document;

import static com.vertafore.test.abilities.CallTitanApi.as;
import static net.serenitybdd.rest.SerenityRest.rest;

import java.io.File;
import java.util.Map;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import org.apache.http.entity.ContentType;

public class UseDocumentServiceTo {

  private static final String POST_BRANDING = "brandings";
  private static final String GET_BRANDING_METADATA_BY_ID = "brandings/{brandingId}";

  public static Performable createBrandingUsingMultiPartFormPost(
      Map<String, String> metaData, File file) {
    return Task.where(
        "{0} posts branding in document service",
        actor ->
            rest()
                .with()
                .contentType(ContentType.MULTIPART_FORM_DATA.toString())
                .multiPart("file", file)
                .multiPart("branding", metaData.toString())
                .post(as(actor).toEndpoint(POST_BRANDING)));
  }

  public static Performable getMetadataOfBrandingById(String id) {
    return Task.where(
        "{0} gets metadata of the branding by id : #id ",
        actor ->
            rest()
                .with()
                .pathParam("brandingId", id)
                .get(as(actor).toEndpoint(GET_BRANDING_METADATA_BY_ID)));
  }
}
