package com.vertafore.test.services.purge;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.PurgeFiscalYearDivisionResponse;
import com.vertafore.test.servicewrappers.UsePurgeTo;
import com.vertafore.test.util.AppLockUtil;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GET_PurgeFiscalYear extends TokenSuperClass {
  @Test
  public void getPurgeFiscalYear() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    AppLockUtil.releaseAllPolicyApplicationLocks(AADM_User);

    UsePurgeTo purgeAPI = new UsePurgeTo();

    VADM_Admin.attemptsTo(purgeAPI.GETPurgeFiscalYearOnThePurgeController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(purgeAPI.GETPurgeFiscalYearOnThePurgeController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);

    AADM_User.attemptsTo(purgeAPI.GETPurgeFiscalYearOnThePurgeController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    PurgeFiscalYearDivisionResponse purgeFiscalYearDivisionResponses =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getList("", PurgeFiscalYearDivisionResponse.class)
            .get(0);

    assertThat(purgeFiscalYearDivisionResponses != null).isTrue();
    assertThat(purgeFiscalYearDivisionResponses.getClass().getDeclaredFields().length).isEqualTo(3);
    assertThat(purgeFiscalYearDivisionResponses.getClass().getDeclaredFields()[0].getName())
        .isEqualTo("fiscalEndDate");
    assertThat(purgeFiscalYearDivisionResponses.getClass().getDeclaredFields()[1].getName())
        .isEqualTo("divisionCode");
    assertThat(purgeFiscalYearDivisionResponses.getClass().getDeclaredFields()[2].getName())
        .isEqualTo("divisionName");
  }
}
