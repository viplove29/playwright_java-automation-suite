package com.vertafore.test.services.titan;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.servicewrappers.UseTitanTo;
import com.vertafore.test.util.CustomerUtil;
import java.util.List;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GET_TitanLandingPage extends TokenSuperClass {

  @Test
  public void getTitanLandingPage() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");
    UseTitanTo titanAPI = new UseTitanTo();

    List<String> securedCustomerIds = CustomerUtil.getAllSecuredCustomerIds(AADM_User);
    String custId = securedCustomerIds.get(0);
    ORAN_App.attemptsTo(
        titanAPI.GETTitanLandingPageUrlOnTheTitanController("CLSDASHBOARD", custId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(
        titanAPI.GETTitanLandingPageUrlOnTheTitanController("CLSDASHBOARD", custId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(
        titanAPI.GETTitanLandingPageUrlOnTheTitanController("CLSDASHBOARD", custId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    TitanLandingPageResponse response =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", TitanLandingPageResponse.class);

    assertThat(response).isNotNull();
    assertThat(response.getClass().getDeclaredFields()[0].getName()).isEqualTo("landingPageUrl");
  }
}
