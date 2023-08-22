package com.vertafore.test.services.web_service_logs;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.PagingResponseWebServiceLogsResponse;
import com.vertafore.test.models.ems.WebServiceLogsResponse;
import com.vertafore.test.servicewrappers.UseWebServiceLogsTo;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;

public class GET_WebServiceLogsWsapi extends TokenSuperClass {

  @Test
  public void getWebServiceLogsWsapiBaselineTest() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseWebServiceLogsTo webServiceLogsApi = new UseWebServiceLogsTo();

    ORAN_App.attemptsTo(
        webServiceLogsApi.GETWebServiceLogsWsapiOnTheWebservicelogsController(
            "v3.0", null, "wsapi", null, null, null, null, null, null, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(
        webServiceLogsApi.GETWebServiceLogsWsapiOnTheWebservicelogsController(
            "v3.0", null, "wsapi", null, null, null, null, null, null, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(
        webServiceLogsApi.GETWebServiceLogsWsapiOnTheWebservicelogsController(
            "v3.0", null, "wsapi", null, null, null, null, null, null, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    PagingResponseWebServiceLogsResponse pagingResponseWebServiceLogsResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponseWebServiceLogsResponse.class);
    assertThat(pagingResponseWebServiceLogsResponse.getClass().getDeclaredFields().length)
        .isEqualTo(4);

    for (WebServiceLogsResponse webServiceLogsResponse :
        pagingResponseWebServiceLogsResponse.getResponse()) {
      assertThat(webServiceLogsResponse.getClass().getDeclaredFields().length).isEqualTo(8);
      assertThat(webServiceLogsResponse.getWsUserName()).isEqualTo("wsapi");
      assertThat(webServiceLogsResponse.getWsVersion()).isEqualTo("v3.0");
    }
  }
}
