package com.vertafore.test.services.eforms;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.servicewrappers.UseEFormsTo;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;

public class GET_EformsResource extends TokenSuperClass {

  @Test
  public void getEformsResourceBaselineTest() {

    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseEFormsTo formsApi = new UseEFormsTo();

    String eFormName = "Complete.xml";

    AADM_User.attemptsTo(formsApi.GETEFormsResourceOnTheEformsController(eFormName, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    ORAN_App.attemptsTo(formsApi.GETEFormsResourceOnTheEformsController(eFormName, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(formsApi.GETEFormsResourceOnTheEformsController(eFormName, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
  }
}
