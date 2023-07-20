package com.vertafore.test.services.view_options;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.ViewOptionsResponse;
import com.vertafore.test.servicewrappers.UseViewOptionsTo;
import com.vertafore.test.util.Util;
import java.util.*;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;

public class GET_ViewOptions extends TokenSuperClass {

  @Test
  public void viewOptionsReturnsBankTransactionsViewOptions() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseViewOptionsTo viewOptionsApi = new UseViewOptionsTo();

    ORAN_App.attemptsTo(
        viewOptionsApi.GETViewOptionsOnTheViewoptionsController(
            "System", "BankTransactionsView", "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(
        viewOptionsApi.GETViewOptionsOnTheViewoptionsController(
            "System", "BankTransactionsView", "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(
        viewOptionsApi.GETViewOptionsOnTheViewoptionsController(
            "System", "BankTransactionsView", "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    ViewOptionsResponse response =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", ViewOptionsResponse.class);

    assertThat(response).isNotNull();
    assertThat(response.getOptions()).isNotNull();
    Map<String, Object> viewOptionsMap = Util.convertObjectToMap(response.getOptions());

    assertThat(viewOptionsMap).isNotEmpty();
    assertThat(viewOptionsMap.keySet()).contains("startDate");
    assertThat(viewOptionsMap.get("startDate"))
        .satisfiesAnyOf(
            startDate -> assertThat(startDate).isNull(),
            startDate -> assertThat(startDate).isInstanceOf(String.class));
    assertThat(viewOptionsMap.keySet()).contains("endDate");
    assertThat(viewOptionsMap.get("endDate"))
        .satisfiesAnyOf(
            endDate -> assertThat(endDate).isNull(),
            endDate -> assertThat(endDate).isInstanceOf(String.class));
    assertThat(viewOptionsMap.keySet()).contains("includeMatched");
    assertThat(viewOptionsMap.get("includeMatched")).isInstanceOf(Boolean.class);
    assertThat(viewOptionsMap.keySet()).contains("includeSuggested");
    assertThat(viewOptionsMap.get("includeSuggested")).isInstanceOf(Boolean.class);
    assertThat(viewOptionsMap.keySet()).contains("includeUnmatched");
    assertThat(viewOptionsMap.get("includeUnmatched")).isInstanceOf(Boolean.class);
    assertThat(viewOptionsMap.keySet()).contains("includePayments");
    assertThat(viewOptionsMap.get("includePayments")).isInstanceOf(Boolean.class);
    assertThat(viewOptionsMap.keySet()).contains("includeDeposits");
    assertThat(viewOptionsMap.get("includeDeposits")).isInstanceOf(Boolean.class);
    assertThat(viewOptionsMap.keySet()).contains("includeDeposits");
  }

  @Test
  public void viewOptionsReturnsChecksToApproveViewOptions() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseViewOptionsTo viewOptionsApi = new UseViewOptionsTo();

    ORAN_App.attemptsTo(
        viewOptionsApi.GETViewOptionsOnTheViewoptionsController(
            "System", "ApproveChecksView", "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(
        viewOptionsApi.GETViewOptionsOnTheViewoptionsController(
            "System", "ApproveChecksView", "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(
        viewOptionsApi.GETViewOptionsOnTheViewoptionsController(
            "System", "ApproveChecksView", "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    ViewOptionsResponse response =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", ViewOptionsResponse.class);

    assertThat(response).isNotNull();
    assertThat(response.getOptions()).isNotNull();
    Map<String, Object> viewOptionsMap = Util.convertObjectToMap(response.getOptions());

    assertThat(viewOptionsMap).isNotEmpty();
    assertThat(viewOptionsMap.keySet()).contains("startDate");
    assertThat(viewOptionsMap.get("startDate"))
        .satisfiesAnyOf(
            startDate -> assertThat(startDate).isNull(),
            startDate -> assertThat(startDate).isInstanceOf(String.class));
    assertThat(viewOptionsMap.keySet()).contains("endDate");
    assertThat(viewOptionsMap.get("endDate"))
        .satisfiesAnyOf(
            endDate -> assertThat(endDate).isNull(),
            endDate -> assertThat(endDate).isInstanceOf(String.class));
    assertThat(viewOptionsMap.keySet()).contains("includeACH");
    assertThat(viewOptionsMap.get("includeACH")).isInstanceOf(Boolean.class);
    assertThat(viewOptionsMap.keySet()).contains("includeEFT");
    assertThat(viewOptionsMap.get("includeEFT")).isInstanceOf(Boolean.class);
    assertThat(viewOptionsMap.keySet()).contains("includeHandwritten");
    assertThat(viewOptionsMap.get("includeHandwritten")).isInstanceOf(Boolean.class);
    assertThat(viewOptionsMap.keySet()).contains("includeOSC");
    assertThat(viewOptionsMap.get("includeOSC")).isInstanceOf(Boolean.class);
    assertThat(viewOptionsMap.keySet()).contains("includeStandard");
    assertThat(viewOptionsMap.get("includeStandard")).isInstanceOf(Boolean.class);
    assertThat(viewOptionsMap.keySet()).contains("includeWire");
    assertThat(viewOptionsMap.get("includeWire")).isInstanceOf(Boolean.class);
  }
}
