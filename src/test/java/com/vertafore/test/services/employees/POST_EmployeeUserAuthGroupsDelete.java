package com.vertafore.test.services.employees;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UseEmployeeTo;
import com.vertafore.test.util.EmployeeUtil;
import com.vertafore.test.util.EnvVariables;
import java.util.ArrayList;
import java.util.List;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;

public class POST_EmployeeUserAuthGroupsDelete extends TokenSuperClass {

  @Test
  public void postEmployeeUserAuthGroupsDeleteBaselineTest() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseEmployeeTo employeeApi = new UseEmployeeTo();

    // get random employee code
    List<EmployeeResponse> employees = EmployeeUtil.getAllEmployees(AADM_User);

    String employeeCode = "";
    String authGroupId = "";
    for (EmployeeResponse employee : employees) {

      // EMSAuto is the ems suite user, qaown is the UI suite user. Make sure not to touch either
      if (!(employee.getShortName().equalsIgnoreCase(EnvVariables.USERNAME)
          || employee.getShortName().equalsIgnoreCase("qaown"))) {

        // Get employees auth groups
        AADM_User.attemptsTo(
            employeeApi.GETEmployeeUserAuthGroupsOnTheEmployeesController(
                employee.getEmpCode(), ""));
        assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

        List<UserAuthGroupResponse> userAuthGroupResponses =
            LastResponse.received()
                .answeredBy(AADM_User)
                .getBody()
                .jsonPath()
                .getList("", UserAuthGroupResponse.class);

        // Get auth group id if list isn't empty
        if (!userAuthGroupResponses.isEmpty()) {
          employeeCode = employee.getEmpCode();
          authGroupId = userAuthGroupResponses.get(0).getAuthGroupId();
          break;
        }
      }
    }

    UserAuthGroupsPostRequest userAuthGroupsPostRequest = new UserAuthGroupsPostRequest();
    userAuthGroupsPostRequest.setEmpCode(employeeCode);
    userAuthGroupsPostRequest.setAuthGroupId(authGroupId);

    // Create delete request
    UserAuthGroupsDeletePostRequest userAuthGroupsDeletePostRequest =
        new UserAuthGroupsDeletePostRequest();
    userAuthGroupsDeletePostRequest.setEmpCode(employeeCode);
    userAuthGroupsDeletePostRequest.setAuthGroupId(authGroupId);

    List<UserAuthGroupsDeletePostRequest> deletePostRequest = new ArrayList<>();
    deletePostRequest.add(userAuthGroupsDeletePostRequest);

    // VADM Test
    VADM_Admin.attemptsTo(
        employeeApi.POSTEmployeeUserAuthGroupsDeleteOnTheEmployeesController(
            deletePostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    // ORAN Test
    ORAN_App.attemptsTo(
        employeeApi.POSTEmployeeUserAuthGroupsDeleteOnTheEmployeesController(
            deletePostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    // AADM Test
    AADM_User.attemptsTo(
        employeeApi.POSTEmployeeUserAuthGroupsDeleteOnTheEmployeesController(
            deletePostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    DeleteGenericResponse deleteResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", DeleteGenericResponse.class);

    assertThat(deleteResponse).isNotNull();
    assertThat(deleteResponse.getNumberOfRecordsDeleted()).isEqualTo(1);

    // REINSTATE PERMISSIONS POST TEST
    List<UserAuthGroupsPostRequest> postRequest = new ArrayList<>();
    postRequest.add(userAuthGroupsPostRequest);

    AADM_User.attemptsTo(
        employeeApi.POSTEmployeeUserAuthGroupsOnTheEmployeesController(postRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
  }
}
