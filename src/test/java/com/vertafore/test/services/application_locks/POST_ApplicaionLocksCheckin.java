package com.vertafore.test.services.application_locks;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.ApplicationLockCheckinResponse;
import com.vertafore.test.models.ems.ApplicationLockResponse;
import com.vertafore.test.models.ems.ApplicationLocksCheckinPostRequest;
import com.vertafore.test.models.ems.CustomerIdResponse;
import com.vertafore.test.servicewrappers.UseApplicationLocksTo;
import com.vertafore.test.util.AppLockUtil;
import com.vertafore.test.util.CustomerUtil;
import io.restassured.path.json.JsonPath;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;

public class POST_ApplicaionLocksCheckin extends TokenSuperClass {

  @Test
  public void postApplicationLocksCheckinTest() {
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
            stagedCustomerOne.getCustomerId(), "VADM APP LOCKS CHECKIN TEST", AADM_User);
    ApplicationLockResponse customerTwoLock =
        AppLockUtil.lockCustomer(
            stagedCustomerTwo.getCustomerId(), "ORAN APP LOCKS CHECKIN TEST", AADM_User);
    ApplicationLockResponse customerThreeLock =
        AppLockUtil.lockCustomer(
            stagedCustomerThree.getCustomerId(), "AADM APP LOCKS CHECKIN TEST", AADM_User);

    ApplicationLocksCheckinPostRequest applicationLocksCheckinPostRequest =
        new ApplicationLocksCheckinPostRequest();
    applicationLocksCheckinPostRequest.addApplicationLockIdsItem(
        customerOneLock.getApplicationLockId());
    applicationLocksCheckinPostRequest.addApplicationLockIdsItem(
        customerTwoLock.getApplicationLockId());
    applicationLocksCheckinPostRequest.addApplicationLockIdsItem(
        customerThreeLock.getApplicationLockId());

    VADM_Admin.attemptsTo(
        applicationLocksApi.POSTApplicationLocksCheckinOnTheApplicationlocksController(
            applicationLocksCheckinPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(
        applicationLocksApi.POSTApplicationLocksCheckinOnTheApplicationlocksController(
            applicationLocksCheckinPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    ApplicationLockCheckinResponse applicationLockCheckinResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", ApplicationLockCheckinResponse.class);

    JsonPath jsonPathEvaluator = LastResponse.received().answeredBy(AADM_User).getBody().jsonPath();

    assertThat(applicationLockCheckinResponse.getNumberOfLocksReleased()).isGreaterThanOrEqualTo(3);

    ORAN_App.attemptsTo(
        applicationLocksApi.POSTApplicationLocksCheckinOnTheApplicationlocksController(
            applicationLocksCheckinPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
  }
}
