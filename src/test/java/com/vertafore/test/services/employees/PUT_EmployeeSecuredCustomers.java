package com.vertafore.test.services.employees;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.util.CustomerUtil;
import com.vertafore.test.util.EmployeeUtil;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class PUT_EmployeeSecuredCustomers extends TokenSuperClass {

  @Test
  public void insertSecuredCustomerForEmployeeInsertsSecuredCustomer() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    // get random employee to add access to a random secured customer
    String randomEmployeeEmpCode = EmployeeUtil.getRandomEmployee(AADM_User).getEmpCode();
    String randomSecuredCustomerId =
        CustomerUtil.getRandomSecuredCustomer(AADM_User).getCustomerId();

    // AADM Test
    assertThat(
            EmployeeUtil.insertSecuredCustomerAccessForEmployee(
                AADM_User, randomEmployeeEmpCode, randomSecuredCustomerId))
        .isEqualTo(200);

    assertThat(
            EmployeeUtil.doesEmployeeHaveAccessToSecuredCustomer(
                AADM_User, randomEmployeeEmpCode, randomSecuredCustomerId))
        .isTrue();

    // VADM Test
    assertThat(
            EmployeeUtil.insertSecuredCustomerAccessForEmployee(
                VADM_Admin, randomEmployeeEmpCode, randomSecuredCustomerId))
        .isEqualTo(403);

    // ORAN Test
    assertThat(
            EmployeeUtil.insertSecuredCustomerAccessForEmployee(
                ORAN_App, randomEmployeeEmpCode, randomSecuredCustomerId))
        .isEqualTo(403);

    // Clean up data
    EmployeeUtil.deleteSecuredCustomerAccessForEmployee(
        AADM_User, randomEmployeeEmpCode, randomSecuredCustomerId);
  }
}
