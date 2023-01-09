package com.vertafore.test.services.suspenses;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.SuspenseIdResponse;
import com.vertafore.test.models.ems.SuspensePostRequest;
import com.vertafore.test.servicewrappers.UseSuspenseTo;
import com.vertafore.test.util.EmployeeUtil;
import com.vertafore.test.util.SuspenseUtil;
import com.vertafore.test.util.Util;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class POST_Suspense extends TokenSuperClass {

  @Test
  public void postSuspensePostsSuspense() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseSuspenseTo suspenseApi = new UseSuspenseTo();

    String randomEmployeeCode = EmployeeUtil.getRandomEmployee(AADM_User).getEmpCode();
    SuspensePostRequest suspensePostRequest =
        SuspenseUtil.createRandomSuspenseTiedToEmployee(randomEmployeeCode, AADM_User);

    VADM_Admin.attemptsTo(
        suspenseApi.POSTSuspenseOnTheSuspensesController(suspensePostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(suspenseApi.POSTSuspenseOnTheSuspensesController(suspensePostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // update record so duplicates are not posted
    suspensePostRequest =
        SuspenseUtil.createRandomSuspenseTiedToEmployee(randomEmployeeCode, AADM_User);

    AADM_User.attemptsTo(suspenseApi.POSTSuspenseOnTheSuspensesController(suspensePostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    SuspenseIdResponse idResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", SuspenseIdResponse.class);

    assertThat(idResponse).isNotNull();
    assertThat(idResponse.getSuspenseId()).isNotNull();
    assertThat(Util.isValidGUID(idResponse.getSuspenseId())).isTrue();
  }
}
