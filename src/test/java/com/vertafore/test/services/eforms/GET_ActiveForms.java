package com.vertafore.test.services.eforms;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.AcordFormInfoResponse;
import com.vertafore.test.servicewrappers.UseEFormsTo;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GET_ActiveForms extends TokenSuperClass {

  @Test
  public void eformsActiveFormsReturnsAllActiveForms() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseEFormsTo eformsApi = new UseEFormsTo();

    // TODO what's the difference between true or false for LatestOnly param?
    ORAN_App.attemptsTo(eformsApi.GETEFormsActiveFormsOnTheEformsController(false, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    ORAN_App.attemptsTo(eformsApi.GETEFormsActiveFormsOnTheEformsController(true, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(eformsApi.GETEFormsActiveFormsOnTheEformsController(false, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
    VADM_Admin.attemptsTo(eformsApi.GETEFormsActiveFormsOnTheEformsController(true, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(eformsApi.GETEFormsActiveFormsOnTheEformsController(false, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    AADM_User.attemptsTo(eformsApi.GETEFormsActiveFormsOnTheEformsController(true, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AcordFormInfoResponse formResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getList("", AcordFormInfoResponse.class)
            .get(0);

    // basic field name assertions
    assertThat(formResponse.getClass().getDeclaredFields().length).isEqualTo(9);
    assertThat(formResponse.getClass().getDeclaredFields()[0].getName()).isEqualTo("description");
    assertThat(formResponse.getClass().getDeclaredFields()[1].getName()).isEqualTo("number");
    assertThat(formResponse.getClass().getDeclaredFields()[2].getName()).isEqualTo("state");
    assertThat(formResponse.getClass().getDeclaredFields()[3].getName()).isEqualTo("edition");
    assertThat(formResponse.getClass().getDeclaredFields()[4].getName()).isEqualTo("mapRuleIdList");
    assertThat(formResponse.getClass().getDeclaredFields()[5].getName())
        .isEqualTo("resourceIdList");
    assertThat(formResponse.getClass().getDeclaredFields()[6].getName())
        .isEqualTo("hasACORDFieldNames");
    assertThat(formResponse.getClass().getDeclaredFields()[7].getName())
        .isEqualTo("isMappedToDatabase");
    assertThat(formResponse.getClass().getDeclaredFields()[8].getName())
        .isEqualTo("elfFormVersionId");
  }
}
