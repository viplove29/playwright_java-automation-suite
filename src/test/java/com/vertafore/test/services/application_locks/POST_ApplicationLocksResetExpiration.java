package com.vertafore.test.services.application_locks;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.ApplicationLockResponse;
import com.vertafore.test.models.ems.ApplicationLocksResetExpirationPostRequest;
import com.vertafore.test.models.ems.ApplicationLocksResetExpirationResponse;
import com.vertafore.test.models.ems.CustomerIdResponse;
import com.vertafore.test.servicewrappers.UseApplicationLocksTo;
import com.vertafore.test.util.AppLockUtil;
import com.vertafore.test.util.CustomerUtil;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class POST_ApplicationLocksResetExpiration extends TokenSuperClass {
  @Test
  public void postApplicationLocksResetExpirationTest() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseApplicationLocksTo applicationLocksApi = new UseApplicationLocksTo();

    // stage customers to lock
    CustomerIdResponse stagedCustomerOne = CustomerUtil.stageARandomCustomer(AADM_User);
    CustomerIdResponse stagedCustomerTwo = CustomerUtil.stageARandomCustomer(AADM_User);
    CustomerIdResponse stagedCustomerThree = CustomerUtil.stageARandomCustomer(AADM_User);

    // lock the customers
    ApplicationLockResponse customerOneLock =
        AppLockUtil.lockCustomer(
            stagedCustomerOne.getCustomerId(), "VADM LOCK RESET TEST", AADM_User);
    ApplicationLockResponse customerTwoLock =
        AppLockUtil.lockCustomer(
            stagedCustomerTwo.getCustomerId(), "ORAN APP LOCK RESET TEST", AADM_User);
    ApplicationLockResponse customerThreeLock =
        AppLockUtil.lockCustomer(
            stagedCustomerThree.getCustomerId(), "AADM LOCK RESET TEST", AADM_User);

    ApplicationLocksResetExpirationPostRequest applicationLocksResetExpirationPostRequest =
        new ApplicationLocksResetExpirationPostRequest();
    applicationLocksResetExpirationPostRequest.setApplicationLockType("10");

    // send the requests
    VADM_Admin.attemptsTo(
        applicationLocksApi.POSTApplicationLocksResetExpirationOnTheApplicationlocksController(
            applicationLocksResetExpirationPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(
        applicationLocksApi.POSTApplicationLocksResetExpirationOnTheApplicationlocksController(
            applicationLocksResetExpirationPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AADM_User.attemptsTo(
        applicationLocksApi.POSTApplicationLocksResetExpirationOnTheApplicationlocksController(
            applicationLocksResetExpirationPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    ApplicationLocksResetExpirationResponse applicationLocksResetExpirationResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", ApplicationLocksResetExpirationResponse.class);

    assertThat(applicationLocksResetExpirationResponse).isNotNull();
    assertThat(applicationLocksResetExpirationResponse.getNumberOfLocksUpdated())
        .isGreaterThanOrEqualTo(1);

    AppLockUtil.releaseCustomerByCustomerId(stagedCustomerOne.getCustomerId(), AADM_User);
    AppLockUtil.releaseCustomerByCustomerId(stagedCustomerTwo.getCustomerId(), AADM_User);
    AppLockUtil.releaseCustomerByCustomerId(stagedCustomerThree.getCustomerId(), AADM_User);
  }
}
