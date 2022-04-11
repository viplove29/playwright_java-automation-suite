package com.vertafore.test.services.customers;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.servicewrappers.UseCustomerTo;
import com.vertafore.test.util.AuthGroupUtility;
import com.vertafore.test.util.CustomerUtil;
import com.vertafore.test.util.EmployeeUtil;
import com.vertafore.test.util.Util;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;

public class GET_CustomerGroups extends TokenSuperClass {

  @Test
  public void getCustomerGroupsFiltersBySecuredCustomerAccessForServiceEmployee() {
    Actor AADM_User = theActorCalled("AADM_User");
    String serviceEmployeeEmpCode = AuthGroupUtility.getCurrentServiceEmployeeEmpCode(AADM_User);

    String randomSecuredCustomerId =
        CustomerUtil.getRandomSecuredCustomer(AADM_User).getCustomerId();
    EmployeeUtil.insertSecuredCustomerAccessForEmployee(
        AADM_User, serviceEmployeeEmpCode, randomSecuredCustomerId);

    Actor ORAN_App = theActorCalled("ORAN_App");

    UseCustomerTo customerAPI = new UseCustomerTo();

    ORAN_App.attemptsTo(
        customerAPI.GETCustomerGroupsOnTheCustomersController(randomSecuredCustomerId, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    EmployeeUtil.deleteSecuredCustomerAccessForEmployee(
        AADM_User, serviceEmployeeEmpCode, randomSecuredCustomerId);
    ORAN_App.attemptsTo(
        customerAPI.GETCustomerGroupsOnTheCustomersController(randomSecuredCustomerId, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
    Util.validateErrorResponse("No customer was found using the arguments supplied", ORAN_App);
  }
}
