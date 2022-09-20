package com.vertafore.test.services.application_locks;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.ApplicationLockCheckinPostRequest;
import com.vertafore.test.models.ems.ApplicationLockCheckinResponse;
import com.vertafore.test.models.ems.ApplicationLockResponse;
import com.vertafore.test.models.ems.CustomerIdResponse;
import com.vertafore.test.servicewrappers.UseApplicationLockTo;
import com.vertafore.test.util.AppLockUtil;
import com.vertafore.test.util.CustomerUtil;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class POST_ApplicationLockCheckin extends TokenSuperClass {

  @Test
  public void postApplicationLockCheckinBaselineTests() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseApplicationLockTo applicationLockApi = new UseApplicationLockTo();

    // stage customers to lock
    CustomerIdResponse stagedCustomerOne = CustomerUtil.stageARandomCustomer(AADM_User);
    CustomerIdResponse stagedCustomerTwo = CustomerUtil.stageARandomCustomer(AADM_User);
    CustomerIdResponse stagedCustomerThree = CustomerUtil.stageARandomCustomer(AADM_User);

    // lock the customers
    ApplicationLockResponse customerOneLock =
        AppLockUtil.lockCustomer(
            stagedCustomerOne.getCustomerId(), "VADM LOCK CHECKIN TEST", AADM_User);
    ApplicationLockResponse customerTwoLock =
        AppLockUtil.lockCustomer(
            stagedCustomerTwo.getCustomerId(), "ORAN APP LOCK CHECKIN TEST", AADM_User);
    ApplicationLockResponse customerThreeLock =
        AppLockUtil.lockCustomer(
            stagedCustomerThree.getCustomerId(), "AADM LOCK CHECKIN TEST", AADM_User);

    // set release request models
    ApplicationLockCheckinPostRequest customerOneLockReleaseRequest =
        new ApplicationLockCheckinPostRequest();
    customerOneLockReleaseRequest.setApplicationLockId(customerOneLock.getApplicationLockId());

    ApplicationLockCheckinPostRequest customerTwoLockReleaseRequest =
        new ApplicationLockCheckinPostRequest();
    customerTwoLockReleaseRequest.setApplicationLockId(customerTwoLock.getApplicationLockId());

    ApplicationLockCheckinPostRequest customerThreeLockReleaseRequest =
        new ApplicationLockCheckinPostRequest();
    customerThreeLockReleaseRequest.setApplicationLockId(customerThreeLock.getApplicationLockId());

    // send the requests
    VADM_Admin.attemptsTo(
        applicationLockApi.POSTApplicationLockCheckinOnTheApplicationlocksController(
            customerOneLockReleaseRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(
        applicationLockApi.POSTApplicationLockCheckinOnTheApplicationlocksController(
            customerTwoLockReleaseRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AADM_User.attemptsTo(
        applicationLockApi.POSTApplicationLockCheckinOnTheApplicationlocksController(
            customerThreeLockReleaseRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // validate response
    ApplicationLockCheckinResponse applicationLockCheckinResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", ApplicationLockCheckinResponse.class);

    assertThat(applicationLockCheckinResponse).isNotNull();
    assertThat(applicationLockCheckinResponse.getNumberOfLocksReleased()).isNotNull();
    assertThat(applicationLockCheckinResponse.getNumberOfLocksReleased()).isEqualTo(1);
  }
}
