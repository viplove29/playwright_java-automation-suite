package com.vertafore.test.services.document;

import static com.vertafore.test.utilities.misc.HelperUtils.checkStatusForSuccess;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;

import com.vertafore.core.util.JsonHelper;
import com.vertafore.test.models.TitanUser;
import com.vertafore.test.tasks.servicewrappers.document.UseDocumentServiceTo;
import com.vertafore.test.utilities.actorextractor.BuildCastOfTitanUsers;
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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class DocumentServiceIntegration {

  private List<TitanUser> users = new ArrayList<>();

  @Before
  public void setupActors() {
    users.add(new TitanUser("donald@lizzy123.com", "LIZZY123", "LIZZY123"));
    OnStage.setTheStage(BuildCastOfTitanUsers.loadAndAuthenticate(users));
  }

  @Test
  public void documentServiceBrandingSetsConfigCorrectly() throws IOException {
    Actor currentActor = theActorCalled("donald@lizzy123.com");

    // build metadata
    Map<String, String> metaData = new HashMap<>();
    metaData.put("name", "brandingTestName");
    metaData.put("description", "brandingTestDescription");

    // get file
    File imageToUpload = new HelperUtils().getFileByFileName("doge", ".jpg");

    // send off multi-part post request to branding controller on doc-svc
    currentActor.attemptsTo(
        UseDocumentServiceTo.createUsingPostOnTheBrandingController(
            JsonHelper.serializeAsJson(metaData), imageToUpload));
    checkStatusForSuccess();

    Map postResponse =
        (Map) SerenityRest.lastResponse().getBody().jsonPath().getList("content").get(0);
    String id = postResponse.get("id").toString();
    // GET the /brandings
    // tests CONFIG-SVC
    currentActor.attemptsTo(UseDocumentServiceTo.getBrandingsUsingGetOnTheBrandingController());
    checkStatusForSuccess();

    //    // GET /bytes
    //    // tests AWS S3 connectivity
    currentActor.attemptsTo(
        UseDocumentServiceTo.getImageUsingGetOnTheBrandingController(id, "original"));
    checkStatusForSuccess();

    // DELETE IT TO CLEAN UP:
    currentActor.attemptsTo(UseDocumentServiceTo.deleteByIdUsingDeleteOnTheBrandingController(id));
    checkStatusForSuccess();
  }
}
