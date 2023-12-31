package com.vertafore.test.services.employees;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.EmployeeResponse;
import com.vertafore.test.models.ems.UserAuthGroupResponse;
import com.vertafore.test.models.ems.UserAuthGroupsPostRequest;
import com.vertafore.test.servicewrappers.UseEmployeeTo;
import com.vertafore.test.util.EmployeeUtil;
import java.util.ArrayList;
import java.util.List;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;

public class POST_EmployeeUserAuthGroups extends TokenSuperClass {

  @Test
  public void postEmployeeUserAuthGroupsBaselineTest() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseEmployeeTo employeeApi = new UseEmployeeTo();

    // get random employee code
    List<EmployeeResponse> employees = EmployeeUtil.getAllEmployees(AADM_User);
    String employeeCode = "";
    String authGroupId = "";
    for (EmployeeResponse employee : employees) {
      // Get employee auth group id
      AADM_User.attemptsTo(
          employeeApi.GETEmployeeUserAuthGroupsOnTheEmployeesController(employee.getEmpCode(), ""));
      assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

      List<UserAuthGroupResponse> userAuthGroupResponses =
          LastResponse.received()
              .answeredBy(AADM_User)
              .getBody()
              .jsonPath()
              .getList("", UserAuthGroupResponse.class);
      if (!userAuthGroupResponses.isEmpty()) {
        employeeCode = employee.getEmpCode();
        authGroupId = userAuthGroupResponses.get(0).getAuthGroupId();
        break;
      }
    }

    UserAuthGroupsPostRequest userAuthGroupsPostRequest = new UserAuthGroupsPostRequest();
    userAuthGroupsPostRequest.setEmpCode(employeeCode);
    userAuthGroupsPostRequest.setAuthGroupId(authGroupId);

    List<UserAuthGroupsPostRequest> postRequest = new ArrayList<>();
    postRequest.add(userAuthGroupsPostRequest);

    // VADM Test
    VADM_Admin.attemptsTo(
        employeeApi.POSTEmployeeUserAuthGroupsOnTheEmployeesController(postRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    // AADM Test
    AADM_User.attemptsTo(
        employeeApi.POSTEmployeeUserAuthGroupsOnTheEmployeesController(postRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // ORAN Test
    ORAN_App.attemptsTo(
        employeeApi.POSTEmployeeUserAuthGroupsOnTheEmployeesController(postRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
  }
}
