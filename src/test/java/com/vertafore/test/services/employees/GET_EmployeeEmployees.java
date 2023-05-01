package com.vertafore.test.services.employees;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.servicewrappers.UseEmployeeTo;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;

public class GET_EmployeeEmployees extends TokenSuperClass {

  @Test
  public void getEmployeeEmployeesBaselineTest() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseEmployeeTo employeeApi = new UseEmployeeTo();

    VADM_Admin.attemptsTo(employeeApi.GETEmployeeEmployeesOnTheEmployeesController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(employeeApi.GETEmployeeEmployeesOnTheEmployeesController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AADM_User.attemptsTo(employeeApi.GETEmployeeEmployeesOnTheEmployeesController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
  }
}
