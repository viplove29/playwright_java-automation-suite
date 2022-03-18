package com.vertafore.test.services.employees;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.BusinessUnitDetailResponse;
import com.vertafore.test.models.ems.BusinessUnitNameCodeResponse;
import com.vertafore.test.servicewrappers.UseEmployeeTo;
import java.util.List;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GET_EmployeeBusinessUnits extends TokenSuperClass {

  /* A smoke test that validates the GET /employee/business-units endpoint against admin,app, and user contexts.
  Validate that only admin does not have access, and that App and User context do have access.
  Returns employee business unit data as a list and validates the response length.*/
  @Test
  public void employeeBusinessUnitsReturnsAllEmployeeBusinessUnits() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseEmployeeTo employeeApi = new UseEmployeeTo();

    VADM_Admin.attemptsTo(employeeApi.GETEmployeeBusinessUnitsOnTheEmployeesController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(employeeApi.GETEmployeeBusinessUnitsOnTheEmployeesController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AADM_User.attemptsTo(employeeApi.GETEmployeeBusinessUnitsOnTheEmployeesController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    List<BusinessUnitDetailResponse> businessUnitDetailResponses =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getList("", BusinessUnitDetailResponse.class);

    /*Created new objects to validate each sub response and validate that the correct fields are being reported*/
    BusinessUnitNameCodeResponse divisionInfo =
        businessUnitDetailResponses.get(0).getDivisionInfo();
    BusinessUnitNameCodeResponse branchInfo = businessUnitDetailResponses.get(0).getBranchInfo();
    BusinessUnitNameCodeResponse deptInfo = businessUnitDetailResponses.get(0).getDepartmentInfo();
    BusinessUnitNameCodeResponse groupInfo = businessUnitDetailResponses.get(0).getGroupInfo();

    assertThat(businessUnitDetailResponses.get(0).getClass().getDeclaredFields().length)
        .isEqualTo(4);
    assertThat(divisionInfo.getClass().getDeclaredFields().length).isEqualTo(3);
    assertThat(branchInfo.getClass().getDeclaredFields().length).isEqualTo(3);
    assertThat(deptInfo.getClass().getDeclaredFields().length).isEqualTo(3);
    assertThat(groupInfo.getClass().getDeclaredFields().length).isEqualTo(3);
    assertThat(businessUnitDetailResponses.size()).isGreaterThan(0);
  }
}
