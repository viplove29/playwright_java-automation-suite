package com.vertafore.test.services.employees;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.CommissionSetupResponse;
import com.vertafore.test.servicewrappers.UseEmployeeTo;
import com.vertafore.test.util.EmployeeUtil;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;

public class DELETE_EmployeeCommissionSetup extends TokenSuperClass {

  @Test
  public void deleteEmployeeCommisionSetupBaselineTest() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseEmployeeTo employeeAPI = new UseEmployeeTo();

    // Delete Employee Commission with random id for VADM_Admin user
    VADM_Admin.attemptsTo(
        employeeAPI.DELETEEmployeeCommissionSetupOnTheEmployeesController("xxxx", ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    // Post commission setup and delete for the ORAN_app user
    CommissionSetupResponse commissionSetupResponse =
        EmployeeUtil.postRandomEmployeeCommissionSetup(ORAN_App);
    ORAN_App.attemptsTo(
        employeeAPI.DELETEEmployeeCommissionSetupOnTheEmployeesController(
            commissionSetupResponse.getCommissionSetupId(), ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Post commission setup and delete for the AADM_User
    commissionSetupResponse = EmployeeUtil.postRandomEmployeeCommissionSetup(AADM_User);
    AADM_User.attemptsTo(
        employeeAPI.DELETEEmployeeCommissionSetupOnTheEmployeesController(
            commissionSetupResponse.getCommissionSetupId(), ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
  }
}
