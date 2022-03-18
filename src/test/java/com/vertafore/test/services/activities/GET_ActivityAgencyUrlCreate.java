package com.vertafore.test.services.activities;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.ActivityUrlResponse;
import com.vertafore.test.servicewrappers.UseActivityTo;
import java.util.UUID;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GET_ActivityAgencyUrlCreate extends TokenSuperClass {

  @Test
  public void ActivityAgencyUrlCreateReturnsCreatedUrl() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    String randomEntityId;
    UseActivityTo activityApi = new UseActivityTo();

    // VADM key returns 403
    VADM_Admin.attemptsTo(
        activityApi.GETActivityAgencyUrlCreateOnTheActivitiesController(
            "Employee", UUID.randomUUID().toString(), "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    // ORAN key successful, tests Employee EntityType
    randomEntityId = UUID.randomUUID().toString();
    ORAN_App.attemptsTo(
        activityApi.GETActivityAgencyUrlCreateOnTheActivitiesController(
            "Employee", randomEntityId, "string"));

    ActivityUrlResponse urlResponse =
        LastResponse.received()
            .answeredBy(ORAN_App)
            .getBody()
            .jsonPath()
            .getObject("", ActivityUrlResponse.class);

    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    assertThat(urlResponse != null).isTrue();
    assertThat(urlResponse.getClass().getDeclaredFields().length).isEqualTo(1);
    assertThat(
            urlResponse.getAgencyActivityUrl().contains(randomEntityId)
                && urlResponse.getAgencyActivityUrl().contains("Employee"))
        .isTrue();

    // AADM key successful, tests Company
    randomEntityId = UUID.randomUUID().toString();
    AADM_User.attemptsTo(
        activityApi.GETActivityAgencyUrlCreateOnTheActivitiesController(
            "Company", randomEntityId, "string"));

    urlResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", ActivityUrlResponse.class);

    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    assertThat(urlResponse != null).isTrue();
    assertThat(urlResponse.getClass().getDeclaredFields().length).isEqualTo(1);
    assertThat(
            urlResponse.getAgencyActivityUrl().contains(randomEntityId)
                && urlResponse.getAgencyActivityUrl().contains("Company"))
        .isTrue();

    // and bob again for Customer
    randomEntityId = UUID.randomUUID().toString();
    AADM_User.attemptsTo(
        activityApi.GETActivityAgencyUrlCreateOnTheActivitiesController(
            "Customer", randomEntityId, "string"));

    urlResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", ActivityUrlResponse.class);

    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    assertThat(urlResponse != null).isTrue();
    assertThat(urlResponse.getClass().getDeclaredFields().length).isEqualTo(1);
    assertThat(
            urlResponse.getAgencyActivityUrl().contains(randomEntityId)
                && urlResponse.getAgencyActivityUrl().contains("Customer"))
        .isTrue();
  }
}
