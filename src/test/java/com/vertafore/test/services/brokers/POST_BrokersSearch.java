package com.vertafore.test.services.brokers;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.BrokerResponse;
import com.vertafore.test.models.ems.BrokersSearchPostRequest;
import com.vertafore.test.models.ems.PagingRequestBrokersSearchPostRequest;
import com.vertafore.test.servicewrappers.UseBrokersTo;
import java.util.List;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class POST_BrokersSearch extends TokenSuperClass {

  @Test
  public void postBrokersSearchBaselineTest() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseBrokersTo brokersAPI = new UseBrokersTo();

    // Get a broker
    AADM_User.attemptsTo(brokersAPI.GETBrokersOnTheBrokersController(0, 5, 5, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    List<BrokerResponse> brokerResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getList("response", BrokerResponse.class);

    String shortName = brokerResponse.get(0).getShortName();

    PagingRequestBrokersSearchPostRequest brokersSearchPostRequest =
        new PagingRequestBrokersSearchPostRequest();
    BrokersSearchPostRequest brokersSearchModel = new BrokersSearchPostRequest();
    brokersSearchModel.setShortName(shortName);

    brokersSearchPostRequest.setModel(brokersSearchModel);

    VADM_Admin.attemptsTo(
        brokersAPI.POSTBrokersSearchOnTheBrokersController(brokersSearchPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(
        brokersAPI.POSTBrokersSearchOnTheBrokersController(brokersSearchPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AADM_User.attemptsTo(
        brokersAPI.POSTBrokersSearchOnTheBrokersController(brokersSearchPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
  }
}
