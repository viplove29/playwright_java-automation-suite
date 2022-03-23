package com.vertafore.test.services.employees;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.SecuredCustomerBasicInfoResponse;
import com.vertafore.test.util.AuthGroupUtility;
import com.vertafore.test.util.CustomerUtil;
import com.vertafore.test.util.EmployeeUtil;
import java.util.List;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;

public class GET_EmployeeSecuredCustomers extends TokenSuperClass {

  @Test
  public void getSecuredCustomerForAnEmployeeReturnsSecuredCustomersForEmployee() {
    Actor AADM_User = theActorCalled("AADM_User");

    // Get the necessary service employee who has access to all secured customers
    String serviceEmployeeEmpCodeWithAllSecuredCustomers =
        AuthGroupUtility.getCurrentServiceEmployeeEmpCode(AADM_User);

    // Get the number of all secured customers
    List<SecuredCustomerBasicInfoResponse> allSecuredCustomers =
        CustomerUtil.getAllSecuredCustomers(AADM_User);
    int numOfSecuredCustomers = allSecuredCustomers.size();

    /* assert that the number of secured customers under the service employee who has them all
    is equal to the number of all secured customers */
    List<SecuredCustomerBasicInfoResponse> securedCustomersUnderAnEmployee =
        EmployeeUtil.getAllSecuredCustomersUnderAnEmployee(
            AADM_User, serviceEmployeeEmpCodeWithAllSecuredCustomers);
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    assertThat(securedCustomersUnderAnEmployee.size()).isEqualTo(numOfSecuredCustomers);
  }
}
