package com.vertafore.test.services.suspenses;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.EntityTypeResponse;
import com.vertafore.test.servicewrappers.UseSuspensesTo;
import java.util.List;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GET_SuspensesEntityTypes extends TokenSuperClass {

  @Test
  public void getSuspensesEntityTypesReturnsAllEntityTypes() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseSuspensesTo suspensesApi = new UseSuspensesTo();

    VADM_Admin.attemptsTo(suspensesApi.GETSuspensesEntityTypesOnTheSuspensesController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(suspensesApi.GETSuspensesEntityTypesOnTheSuspensesController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AADM_User.attemptsTo(suspensesApi.GETSuspensesEntityTypesOnTheSuspensesController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    List<EntityTypeResponse> response =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getList("", EntityTypeResponse.class);

    assertThat(response.isEmpty()).isFalse();
    assertThat(response.get(0).getClass()).isEqualTo(EntityTypeResponse.class);
  }
}
