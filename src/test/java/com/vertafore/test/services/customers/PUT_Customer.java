package com.vertafore.test.services.customers;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.CustomerIdResponse;
import com.vertafore.test.models.ems.CustomerInfoPutRequest;
import com.vertafore.test.servicewrappers.UseCustomerTo;
import com.vertafore.test.util.AuthGroupUtility;
import com.vertafore.test.util.CustomerUtil;
import com.vertafore.test.util.EmployeeUtil;
import com.vertafore.test.util.Util;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;

public class PUT_Customer extends TokenSuperClass {

  @Test
  public void putCustomerFiltersBySecuredCustomerAccessForServiceEmployees() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");

    // Services for endpoint methods
    UseCustomerTo customerAPI = new UseCustomerTo();

    String serviceEmployeeEmpCode = AuthGroupUtility.getCurrentServiceEmployeeEmpCode(AADM_User);

    // give service employee access to random secured customer
    String randomSecuredCustomerId =
        CustomerUtil.getRandomSecuredCustomer(AADM_User).getCustomerId();
    EmployeeUtil.insertSecuredCustomerAccessForEmployee(
        AADM_User, serviceEmployeeEmpCode, randomSecuredCustomerId);

    // get the customer number which we need for the PUT request
    Integer randomSecuredCustomerNumber =
        CustomerUtil.getCustomerInfoByCustomerId(ORAN_App, randomSecuredCustomerId)
            .getCustomerNumber();
    CustomerInfoPutRequest customerInfoPutRequest = new CustomerInfoPutRequest();
    customerInfoPutRequest.setCustomerNumber(randomSecuredCustomerNumber);

    // Employee has access, can access the customer and the response returned corresponds to the
    // securedCustomer Id
    ORAN_App.attemptsTo(
        customerAPI.PUTCustomerOnTheCustomersController(customerInfoPutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    CustomerIdResponse customerPutResponse =
        LastResponse.received()
            .answeredBy(ORAN_App)
            .getBody()
            .jsonPath()
            .getObject("", CustomerIdResponse.class);
    assertThat(customerPutResponse.getCustomerId()).isEqualTo(randomSecuredCustomerId);

    // remove Employee access to secured customer
    EmployeeUtil.deleteSecuredCustomerAccessForEmployee(
        AADM_User, serviceEmployeeEmpCode, randomSecuredCustomerId);

    // Validate that the employee can no longer access customer using endpoint
    ORAN_App.attemptsTo(
        customerAPI.PUTCustomerOnTheCustomersController(customerInfoPutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
    Util.validateErrorResponse("No customer was found using the arguments supplied", ORAN_App);
  }
}
