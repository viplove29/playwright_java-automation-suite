package com.vertafore.test.services.document;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;

import com.vertafore.test.actors.JsonToActorsConverter;
import com.vertafore.test.models.TitanUser;
import com.vertafore.test.tasks.servicewrappers.document.UseDocumentServiceTo;
import com.vertafore.test.tasks.utilities.HelperUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

// JUNIT TEST ("step-definition"- like layer) for form-service integration tests
@RunWith(SerenityRunner.class)
public class DocumentServiceIntegration {

  private List<TitanUser> users = new ArrayList<>();

  @Before
  public void setupActors() {
    users.add(new TitanUser("liz", "LIZZY123", "LIZZY123"));
    OnStage.setTheStage(JsonToActorsConverter.castOfAuthenticatedActors(users));
  }

  @Test
  public void documentServiceBrandingSetsConfigCorrectly() {
    Actor currentActor = theActorCalled("liz");

    // build metadata
    HashMap<String, String> metaData = new HashMap<>();
    metaData.put("name", "brandingTestName");
    metaData.put("description", "brandingTestDescription");

    // get file
    File imageToUpload = new HelperUtils().getFileByFileName("doge", ".jpg");

    // send off multi-part post request to branding controller on doc-svc
    currentActor.attemptsTo(
        UseDocumentServiceTo.createBrandingUsingMultiPartFormPost(metaData, imageToUpload));
  }
}
