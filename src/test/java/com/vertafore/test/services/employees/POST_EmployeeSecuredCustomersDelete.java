package com.vertafore.test.services.employees;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.util.CustomerUtil;
import com.vertafore.test.util.EmployeeUtil;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;

public class POST_EmployeeSecuredCustomersDelete extends TokenSuperClass {

  @Test
  public void deleteSecuredCustomerAccessForEmployeeDeletesSecuredCustomerAccess() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    // get random employee to add access to a random secured customer
    String randomEmployeeEmpCode = EmployeeUtil.getRandomEmployee(AADM_User).getEmpCode();
    String randomSecuredCustomerId =
        CustomerUtil.getRandomSecuredCustomer(AADM_User).getCustomerId();

    // stage data, only AADM has ability to insert
    EmployeeUtil.insertSecuredCustomerAccessForEmployee(
        AADM_User, randomEmployeeEmpCode, randomSecuredCustomerId);

    // VADM Test
    assertThat(
            EmployeeUtil.deleteSecuredCustomerAccessForEmployee(
                VADM_Admin, randomEmployeeEmpCode, randomSecuredCustomerId))
        .isEqualTo(403);
    assertThat(
            EmployeeUtil.doesEmployeeHaveAccessToSecuredCustomer(
                AADM_User, randomEmployeeEmpCode, randomSecuredCustomerId))
        .isTrue(); // delete un-successful, one in the list

    // ORAN Test
    assertThat(
            EmployeeUtil.deleteSecuredCustomerAccessForEmployee(
                ORAN_App, randomEmployeeEmpCode, randomSecuredCustomerId))
        .isEqualTo(403);
    assertThat(
            EmployeeUtil.doesEmployeeHaveAccessToSecuredCustomer(
                AADM_User, randomEmployeeEmpCode, randomSecuredCustomerId))
        .isTrue(); // delete un-successful, one in the list

    // AADM Test
    assertThat(
            EmployeeUtil.insertSecuredCustomerAccessForEmployee(
                AADM_User, randomEmployeeEmpCode, randomSecuredCustomerId))
        .isEqualTo(200);

    // Clean up data
    EmployeeUtil.deleteSecuredCustomerAccessForEmployee(
        AADM_User, randomEmployeeEmpCode, randomSecuredCustomerId);
    assertThat(
            EmployeeUtil.doesEmployeeHaveAccessToSecuredCustomer(
                AADM_User, randomEmployeeEmpCode, randomSecuredCustomerId))
        .isFalse(); // delete successful, none in the list
  }
}
