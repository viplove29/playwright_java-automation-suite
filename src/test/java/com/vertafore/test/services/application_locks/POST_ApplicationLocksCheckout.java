package com.vertafore.test.services.application_locks;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UseApplicationLocksTo;
import com.vertafore.test.util.AppLockUtil;
import com.vertafore.test.util.CustomerUtil;
import java.util.ArrayList;
import java.util.List;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;

public class POST_ApplicationLocksCheckout extends TokenSuperClass {

  @Test
  public void postApplicationLocksCheckoutTest() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseApplicationLocksTo applicationLocksApi = new UseApplicationLocksTo();

    // stage customers to lock
    CustomerIdResponse stagedCustomerOne = CustomerUtil.stageARandomCustomer(AADM_User);
    CustomerIdResponse stagedCustomerTwo = CustomerUtil.stageARandomCustomer(AADM_User);
    CustomerIdResponse stagedCustomerThree = CustomerUtil.stageARandomCustomer(AADM_User);

    ApplicationLocksCheckoutPostRequest applicationLocksCheckoutPostRequest =
        new ApplicationLocksCheckoutPostRequest();
    List<ApplicationLockCheckoutPostRequest> applicationLockCheckoutPostRequestList =
        new ArrayList<ApplicationLockCheckoutPostRequest>();
    ApplicationLockCheckoutPostRequest customerOneApplicationLockCheckoutPostRequest =
        new ApplicationLockCheckoutPostRequest();
    customerOneApplicationLockCheckoutPostRequest.setApplicationLockType("10");
    customerOneApplicationLockCheckoutPostRequest.setRecordId(stagedCustomerOne.getCustomerId());
    customerOneApplicationLockCheckoutPostRequest.setIgnoreMyLock(false);
    customerOneApplicationLockCheckoutPostRequest.setApplicationLockDescription(
        "VADM APP LOCKS CHECKOUT TEST");
    applicationLockCheckoutPostRequestList.add(customerOneApplicationLockCheckoutPostRequest);

    ApplicationLockCheckoutPostRequest customerTwoApplicationLockCheckoutPostRequest =
        new ApplicationLockCheckoutPostRequest();
    customerTwoApplicationLockCheckoutPostRequest.setApplicationLockType("10");
    customerTwoApplicationLockCheckoutPostRequest.setRecordId(stagedCustomerTwo.getCustomerId());
    customerTwoApplicationLockCheckoutPostRequest.setIgnoreMyLock(false);
    customerTwoApplicationLockCheckoutPostRequest.setApplicationLockDescription(
        "ORAN APP LOCKS CHECKOUT TEST");
    applicationLockCheckoutPostRequestList.add(customerTwoApplicationLockCheckoutPostRequest);

    ApplicationLockCheckoutPostRequest customerThreeApplicationLockCheckoutPostRequest =
        new ApplicationLockCheckoutPostRequest();
    customerThreeApplicationLockCheckoutPostRequest.setApplicationLockType("10");
    customerThreeApplicationLockCheckoutPostRequest.setRecordId(
        stagedCustomerThree.getCustomerId());
    customerThreeApplicationLockCheckoutPostRequest.setIgnoreMyLock(false);
    customerThreeApplicationLockCheckoutPostRequest.setApplicationLockDescription(
        "AADM APP LOCKS CHECKOUT TEST");
    applicationLockCheckoutPostRequestList.add(customerThreeApplicationLockCheckoutPostRequest);

    applicationLocksCheckoutPostRequest.setApplicationLocks(applicationLockCheckoutPostRequestList);

    VADM_Admin.attemptsTo(
        applicationLocksApi.POSTApplicationLocksCheckoutOnTheApplicationlocksController(
            applicationLocksCheckoutPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(
        applicationLocksApi.POSTApplicationLocksCheckoutOnTheApplicationlocksController(
            applicationLocksCheckoutPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    ApplicationLocksCheckoutResponse applicationLocksCheckoutResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", ApplicationLocksCheckoutResponse.class);

    assertThat(applicationLocksCheckoutResponse).isNotNull();
    assertThat(applicationLocksCheckoutResponse.getApplicationLocks().size())
        .isGreaterThanOrEqualTo(3);

    ORAN_App.attemptsTo(
        applicationLocksApi.POSTApplicationLocksCheckoutOnTheApplicationlocksController(
            applicationLocksCheckoutPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AppLockUtil.releaseCustomerByCustomerId(stagedCustomerOne.getCustomerId(), AADM_User);
    AppLockUtil.releaseCustomerByCustomerId(stagedCustomerTwo.getCustomerId(), AADM_User);
    AppLockUtil.releaseCustomerByCustomerId(stagedCustomerThree.getCustomerId(), AADM_User);
  }
}
