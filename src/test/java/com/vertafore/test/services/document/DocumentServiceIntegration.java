package com.vertafore.test.services.document;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

import com.vertafore.core.util.JsonHelper;
import com.vertafore.test.models.TitanUser;
import com.vertafore.test.tasks.servicewrappers.document.UseDocumentServiceTo;
import com.vertafore.test.tasks.utilities.UpdateTheir;
import com.vertafore.test.utilities.actorextractor.JsonToActorsConverter;
import com.vertafore.test.utilities.misc.HelperUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.ensure.Ensure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

// JUNIT TEST ("step-definition"- like layer) for form-service integration tests
@RunWith(SerenityRunner.class)
public class DocumentServiceIntegration {

  private List<TitanUser> users = new ArrayList<>();

  @Before
  public void setupActors() {
    users.add(new TitanUser("donald donald", "Nguyen Company", "LIZZY123"));
    OnStage.setTheStage(JsonToActorsConverter.castOfAuthenticatedActors(users));
  }

  @Test
  public void documentServiceBrandingSetsConfigCorrectly() throws IOException {
    Actor currentActor = theActorCalled("donald donald");
    currentActor.attemptsTo(UpdateTheir.serviceTo("document"));

    // build metadata
    Map<String, String> metaData = new HashMap<>();
    metaData.put("name", "brandingTestName");
    metaData.put("description", "brandingTestDescription");

    // get file
    File imageToUpload = new HelperUtils().getFileByFileName("doge", ".jpg");

    // send off multi-part post request to branding controller on doc-svc
    currentActor.attemptsTo(
        UseDocumentServiceTo.createUsingPost(JsonHelper.serializeAsJson(metaData), imageToUpload));
    checkStatusForSuccess();

    Map postResponse =
        (Map) SerenityRest.lastResponse().getBody().jsonPath().getList("content").get(0);
    String id = postResponse.get("id").toString();
    // GET the /brandings
    // tests CONFIG-SVC
    currentActor.attemptsTo(UseDocumentServiceTo.getBrandingsUsingGet());
    checkStatusForSuccess();
    //    // GET /bytes
    //    // tests AWS S3 connectivity
    currentActor.attemptsTo(UseDocumentServiceTo.getImageUsingGet(id, "original"));
    checkStatusForSuccess();

    // DELETE IT TO CLEAN UP:
    currentActor.attemptsTo(UseDocumentServiceTo.deleteByIdUsingDelete(id));
    checkStatusForSuccess();
  }

  private void checkStatusForSuccess() {
    theActorInTheSpotlight()
        .attemptsTo(Ensure.that(SerenityRest.lastResponse().statusCode()).isBetween(200, 299));
  }
}
