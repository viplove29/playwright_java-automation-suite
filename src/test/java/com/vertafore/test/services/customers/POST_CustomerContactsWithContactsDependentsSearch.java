package com.vertafore.test.services.customers;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.*;
import com.vertafore.test.util.AuthGroupUtility;
import com.vertafore.test.util.CustomerUtil;
import com.vertafore.test.util.EmployeeUtil;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;

public class POST_CustomerContactsWithContactsDependentsSearch extends TokenSuperClass {

  @Test
  public void contactsDependentsSearchFiltersBySecuredCustomerAccessForServiceEmployees() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");

    String serviceEmployeeEmpCode = AuthGroupUtility.getCurrentServiceEmployeeEmpCode(AADM_User);

    // give service employee access to random secured customer
    String randomSecuredCustomerId =
        CustomerUtil.getRandomSecuredCustomer(AADM_User).getCustomerId();
    EmployeeUtil.insertSecuredCustomerAccessForEmployee(
        AADM_User, serviceEmployeeEmpCode, randomSecuredCustomerId);

    // get all customer contacts, then filter list by custId
    CustomerContactDependentResponse securedCustomerContactDependent =
        CustomerUtil.getCustomerContactDependentByCustomerId(
            ORAN_App, null, randomSecuredCustomerId);

    // the search is successful, service employee has access and returned object is not null
    assertThat(securedCustomerContactDependent).isNotNull();
    assertThat(securedCustomerContactDependent.getCustomerId()).isEqualTo(randomSecuredCustomerId);

    // remove employee access to Secured Customer
    EmployeeUtil.deleteSecuredCustomerAccessForEmployee(
        AADM_User, serviceEmployeeEmpCode, randomSecuredCustomerId);

    // no error message validation since it's a search, an empty list is returned once filtered by
    // the secured customer Id
    securedCustomerContactDependent =
        CustomerUtil.getCustomerContactDependentByCustomerId(
            ORAN_App, null, randomSecuredCustomerId);
    assertThat(securedCustomerContactDependent).isNull();
  }
}
