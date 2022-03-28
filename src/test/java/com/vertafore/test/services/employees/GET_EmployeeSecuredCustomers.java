package com.vertafore.test.services.employees;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.SecuredCustomerBasicInfoResponse;
import com.vertafore.test.util.CustomerUtil;
import com.vertafore.test.util.EmployeeUtil;
import java.util.List;
import java.util.stream.Collectors;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;

public class GET_EmployeeSecuredCustomers extends TokenSuperClass {

  @Test
  public void getSecuredCustomerForAnEmployeeReturnsSecuredCustomersForEmployee() {
    Actor AADM_User = theActorCalled("AADM_User");

    // Get the necessary service employee who has access to all secured customers
    String randomEmployeeEmpCode = EmployeeUtil.getRandomEmployee(AADM_User).getEmpCode();

    // Get the number of all secured customers
    List<SecuredCustomerBasicInfoResponse> allSecuredCustomers =
        CustomerUtil.getAllSecuredCustomers(AADM_User);
    int numOfSecuredCustomers = allSecuredCustomers.size();

    // get list of secured customer id strings from last response
    List<String> securedCustomerIds =
        allSecuredCustomers
            .stream()
            .map(securedCustomer -> securedCustomer.getCustomerId())
            .collect(Collectors.toList());

    // insert all secured customers into employee
    EmployeeUtil.insertMultipleSecuredCustomerAccessesForEmployee(
        AADM_User, randomEmployeeEmpCode, securedCustomerIds);

    List<SecuredCustomerBasicInfoResponse> securedCustomersUnderAnEmployee =
        EmployeeUtil.getAllSecuredCustomersUnderAnEmployee(AADM_User, randomEmployeeEmpCode);

    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    assertThat(securedCustomersUnderAnEmployee.size()).isEqualTo(numOfSecuredCustomers);
  }
}
