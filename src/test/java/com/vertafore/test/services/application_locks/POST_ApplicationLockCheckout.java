package com.vertafore.test.services.application_locks;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UseApplicationLockTo;
import com.vertafore.test.servicewrappers.UseCustomerTo;
import com.vertafore.test.util.AppLockUtil;
import com.vertafore.test.util.CustomerUtil;
import com.vertafore.test.util.Util;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class POST_ApplicationLockCheckout extends TokenSuperClass {

  @Test
  public void postApplicationLockCheckoutBaselineTest() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseApplicationLockTo applicationLockApi = new UseApplicationLockTo();

    // stage record to lock
    CustomerIdResponse customerToLock = CustomerUtil.stageARandomCustomer(AADM_User);

    String recordId = customerToLock.getCustomerId();

    ApplicationLockCheckoutPostRequest appLockPostRequest =
        new ApplicationLockCheckoutPostRequest();

    appLockPostRequest.setRecordId(recordId);
    appLockPostRequest.setApplicationLockType("10"); // customer type checkout type

    // BASELINE TESTS
    VADM_Admin.attemptsTo(
        applicationLockApi.POSTApplicationLockCheckoutOnTheApplicationlocksController(
            appLockPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(
        applicationLockApi.POSTApplicationLockCheckoutOnTheApplicationlocksController(
            appLockPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AADM_User.attemptsTo(
        applicationLockApi.POSTApplicationLockCheckoutOnTheApplicationlocksController(
            appLockPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // VALIDATE RESPONSE MODEL
    ApplicationLockResponse applicationLockResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", ApplicationLockResponse.class);

    assertThat(applicationLockResponse).isNotNull();
    assertThat(applicationLockResponse.getApplicationLockId()).isNotNull();
    assertThat(applicationLockResponse.getRecordId()).isEqualTo(recordId);
  }

  @Test
  public void applicationLockRestrictsCustomerRecord() {
    Actor AADM_User = theActorCalled("AADM_User");

    UseApplicationLockTo applicationLockApi = new UseApplicationLockTo();

    // stage record to lock
    CustomerIdResponse customerToLock = CustomerUtil.stageARandomCustomer(AADM_User);

    String recordId = customerToLock.getCustomerId();

    // Lock Customer
    AppLockUtil.lockCustomer(recordId, "CUSTOMER LOCK TEST", AADM_User);

    // attempt to update
    UseCustomerTo customersApi = new UseCustomerTo();

    CustomerBasicInfoPutRequest customerUpdateRequest =
        CustomerUtil.updateBasicCustomer(
            customerToLock.getCustomerNumber(),
            customerToLock.getCustomerId(),
            "Customer",
            "Individual",
            AADM_User);

    // validate user can't update customer
    AADM_User.attemptsTo(
        customersApi.PUTCustomerBasicInfoOnTheCustomersController(customerUpdateRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(409);
    Util.validateErrorResponseContainsString(
        "The Customer Setup record '" + recordId + "' is currently locked", AADM_User);
  }
}
