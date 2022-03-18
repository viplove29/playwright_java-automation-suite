package com.vertafore.test.services.agency;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.PrCodeResponse;
import com.vertafore.test.servicewrappers.UseAgencyTo;
import java.util.List;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GET_AgencyLists extends TokenSuperClass {

  /* This a smoke test that validates the GET agency/lists endpoint against user, app, and admin context
  as well as making sure the list returned by passing in the master PRcode (^^^) is not empty and contains
  the right number of fields.*/
  @Test
  public void agencyListsReturnsAllPRCodes() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseAgencyTo agencyAPI = new UseAgencyTo();

    String masterCode = "^^^";

    VADM_Admin.attemptsTo(agencyAPI.GETAgencyListsOnTheAgencyController(masterCode, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(agencyAPI.GETAgencyListsOnTheAgencyController(masterCode, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AADM_User.attemptsTo(agencyAPI.GETAgencyListsOnTheAgencyController(masterCode, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    List<PrCodeResponse> prCodeResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getList("", PrCodeResponse.class);

    assertThat(prCodeResponse.get(0).getClass().getDeclaredFields().length).isEqualTo(6);
    assertThat(prCodeResponse.size()).isGreaterThan(0);
  }
}
