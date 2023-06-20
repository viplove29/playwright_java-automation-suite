package com.vertafore.test.services.employees;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.CommissionSetupResponse;
import com.vertafore.test.models.ems.CommissionSetupSearchPostRequest;
import com.vertafore.test.models.ems.PagingRequestCommissionSetupSearchPostRequest;
import com.vertafore.test.servicewrappers.UseEmployeeTo;
import com.vertafore.test.util.EmployeeUtil;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;

public class POST_EmployeeCommissionSetupSearch extends TokenSuperClass {

  @Test
  public void postEmployeeCommisionSetupSearchBaselineTest() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseEmployeeTo employeeAPI = new UseEmployeeTo();

    // Post a commission setup request
    CommissionSetupResponse commissionSetupResponse =
        EmployeeUtil.postRandomEmployeeCommissionSetup(AADM_User);

    PagingRequestCommissionSetupSearchPostRequest commissionSetupSearchPostRequest =
        new PagingRequestCommissionSetupSearchPostRequest();
    CommissionSetupSearchPostRequest request = new CommissionSetupSearchPostRequest();
    request.setPersonnelCode(commissionSetupResponse.getPersonnelCode());
    commissionSetupSearchPostRequest.setModel(request);

    // Post Employee Commission search
    VADM_Admin.attemptsTo(
        employeeAPI.POSTEmployeeCommissionSetupSearchOnTheEmployeesController(
            commissionSetupSearchPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(
        employeeAPI.POSTEmployeeCommissionSetupSearchOnTheEmployeesController(
            commissionSetupSearchPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AADM_User.attemptsTo(
        employeeAPI.POSTEmployeeCommissionSetupSearchOnTheEmployeesController(
            commissionSetupSearchPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Delete the created commission setup
    EmployeeUtil.deleteEmployeeCommissionSetup(
        AADM_User, commissionSetupResponse.getCommissionSetupId());
  }
}
