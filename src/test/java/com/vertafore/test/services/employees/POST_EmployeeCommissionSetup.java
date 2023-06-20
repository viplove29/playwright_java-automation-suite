package com.vertafore.test.services.employees;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.CommissionSetupPostRequest;
import com.vertafore.test.models.ems.CommissionSetupResponse;
import com.vertafore.test.servicewrappers.UseEmployeeTo;
import com.vertafore.test.util.EmployeeUtil;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;

public class POST_EmployeeCommissionSetup extends TokenSuperClass {

  @Test
  public void postEmployeeCommissionSetupBaselineTest() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseEmployeeTo employeeAPI = new UseEmployeeTo();
    String employeeCode = "!!$";
    CommissionSetupPostRequest commissionSetupPostRequest = new CommissionSetupPostRequest();
    commissionSetupPostRequest.setPersonnelCode(employeeCode);
    commissionSetupPostRequest.setAppliesTo("Premium");
    commissionSetupPostRequest.setTypeOfBusiness("3");
    commissionSetupPostRequest.setLineOfBusiness("(All)");
    commissionSetupPostRequest.setWritingCompany("!!1");
    LocalDate dateObj = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
    String date = dateObj.format(formatter);
    commissionSetupPostRequest.setEffectiveDate(date);
    commissionSetupPostRequest.setBusinessOrigin("OR");
    commissionSetupPostRequest.setTransactionType("NBS");
    commissionSetupPostRequest.setParentCompany("!!1");
    commissionSetupPostRequest.setCommissionMethod("P");
    commissionSetupPostRequest.setPlanType("(All)");
    commissionSetupPostRequest.setCommissionPercentage(0.40);

    // Post Employee Commission
    VADM_Admin.attemptsTo(
        employeeAPI.POSTEmployeeCommissionSetupOnTheEmployeesController(
            commissionSetupPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(
        employeeAPI.POSTEmployeeCommissionSetupOnTheEmployeesController(
            commissionSetupPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Delete the created commission setup so other user can create it again
    CommissionSetupResponse commissionSetupResponse =
        LastResponse.received()
            .answeredBy(ORAN_App)
            .getBody()
            .jsonPath()
            .getObject("", CommissionSetupResponse.class);
    EmployeeUtil.deleteEmployeeCommissionSetup(
        ORAN_App, commissionSetupResponse.getCommissionSetupId());

    AADM_User.attemptsTo(
        employeeAPI.POSTEmployeeCommissionSetupOnTheEmployeesController(
            commissionSetupPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Delete the created commission setup so other user case create it again
    commissionSetupResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", CommissionSetupResponse.class);
    EmployeeUtil.deleteEmployeeCommissionSetup(
        AADM_User, commissionSetupResponse.getCommissionSetupId());
  }
}
