package com.vertafore.test.services.document;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

import com.vertafore.core.util.JsonHelper;
import com.vertafore.test.abilities.HaveTitanContext;
import com.vertafore.test.actor.titan.BuildCastOfTitanUsers;
import com.vertafore.test.models.TitanUser;
import com.vertafore.test.models.document.LogoV1;
import com.vertafore.test.servicewrappers.UseDocumentTo;
import com.vertafore.test.util.Util;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
@WithTags({
  @WithTag("titan"),
  @WithTag("aws"),
})
public class DocumentServiceIntegration {

  private List<TitanUser> users = new ArrayList<>();

  @Before
  public void setupActors() {
    users.add(new TitanUser("donald@lizzy123.com", "LIZZY123", "LIZZY123"));
    OnStage.setTheStage(BuildCastOfTitanUsers.loadAndAuthenticate(users));
  }

  @Test
  @WithTag("aws")
  public void documentServiceBrandingSetsConfigCorrectly() throws IOException {
    Actor currentActor = theActorCalled("donald@lizzy123.com");

    String productId = HaveTitanContext.theProductIdOf(currentActor);
    String entityId = HaveTitanContext.theEntityIdOf(currentActor);
    String tenantId = HaveTitanContext.theTenantIdOf(currentActor);

    UseDocumentTo documentApi = new UseDocumentTo();

    // build metadata
    Map<String, String> metaData = new HashMap<>();
    metaData.put("name", "brandingTestName");
    metaData.put("description", "brandingTestDescription");

    // get branding image to upload
    File imageToUpload = new Util().getFileByFileName("doge", ".jpg");

    // send off multi-part post request to branding controller on doc-svc
    currentActor.attemptsTo(
        documentApi.createUsingPostOnTheBrandingController(
            productId, tenantId, entityId, imageToUpload, JsonHelper.serializeAsJson(metaData)));

    currentActor.should(
        seeThatResponse("Create Branding response is successful", res -> res.statusCode(201)));

    String brandingId =
        LastResponse.received()
            .answeredBy(currentActor)
            .getBody()
            .jsonPath()
            .getList("content", LogoV1.class)
            .get(0)
            .getId();

    // GET the /brandings
    currentActor.attemptsTo(
        documentApi.getBrandingsUsingGetOnTheBrandingController(productId, tenantId, entityId));

    currentActor.should(
        seeThatResponse(
            "Getting the brandings back tests config-svc set correctly",
            res -> res.statusCode(200)));

    // GET /bytes
    currentActor.attemptsTo(
        documentApi.getImageUsingGetOnTheBrandingController(
            productId, tenantId, entityId, brandingId, "original"));

    currentActor.should(
        seeThatResponse(
            "Getting the bytes back tests AWS S3 connectivity", res -> res.statusCode(200)));

    // DELETE IT TO CLEAN UP:
    currentActor.attemptsTo(
        documentApi.deleteByIdUsingDeleteOnTheBrandingController(
            productId, tenantId, entityId, brandingId));

    currentActor.should(
        seeThatResponse("Deleting branding, for cleanup.", res -> res.statusCode(200)));
  }
}
