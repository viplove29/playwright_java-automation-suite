package com.vertafore.test.services.activities;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.EntityTypeResponse;
import com.vertafore.test.servicewrappers.UseActivityTo;
import java.util.List;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GET_ActivityEntityTypes extends TokenSuperClass {

  @Test
  public void getActivityEntityTypesReturnsActivityEntityTypes() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseActivityTo activityApi = new UseActivityTo();

    ORAN_App.attemptsTo(activityApi.GETActivityEntityTypesOnTheActivitiesController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(activityApi.GETActivityEntityTypesOnTheActivitiesController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(activityApi.GETActivityEntityTypesOnTheActivitiesController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    List<EntityTypeResponse> entityTypeResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getList("", EntityTypeResponse.class);

    assertThat(entityTypeResponse).isNotNull();
    assertThat(entityTypeResponse.size()).isGreaterThan(0);
    assertThat(entityTypeResponse.get(0).getEntityType()).isNotNull();
  }
}
