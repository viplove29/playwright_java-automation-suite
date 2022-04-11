package com.vertafore.test.services.customers;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.CustomerInfoResponse;
import com.vertafore.test.util.AuthGroupUtility;
import com.vertafore.test.util.CustomerUtil;
import com.vertafore.test.util.EmployeeUtil;
import com.vertafore.test.util.Util;
import java.util.List;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;

public class GET_Customer extends TokenSuperClass {

  @Test
  public void getCustomerFiltersBySecuredCustomerSecurityForServiceEmployees() {
    Actor AADM_User = theActorCalled("AADM_User");
    String serviceEmployeeEmpCode = AuthGroupUtility.getCurrentServiceEmployeeEmpCode(AADM_User);

    // Get all secured customer ids to insert to the service employee and validate against later
    List<String> securedCustomerIds = CustomerUtil.getAllSecuredCustomerIds(AADM_User);

    EmployeeUtil.insertMultipleSecuredCustomerAccessesForEmployee(
        AADM_User, serviceEmployeeEmpCode, securedCustomerIds);

    // The orange app (service employee) will be the one making validating the correct access
    Actor ORAN_App = theActorCalled("ORAN_App");

    for (String custId : securedCustomerIds) {
      CustomerInfoResponse securedCustomerInfo =
          CustomerUtil.getCustomerInfoByCustomerId(ORAN_App, custId);
      assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
      assertThat(securedCustomerInfo).isNotNull();
    }

    // now delete all secured customers from that service employee's access
    EmployeeUtil.deleteMultipleSecuredCustomerAccessForEmployee(
        AADM_User, serviceEmployeeEmpCode, securedCustomerIds);

    for (String custId : securedCustomerIds) {
      CustomerInfoResponse securedCustomerInfo =
          CustomerUtil.getCustomerInfoByCustomerId(ORAN_App, custId);
      assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
      assertThat(securedCustomerInfo).isNull();
      Util.validateErrorResponse("No customer was found using the arguments supplied", ORAN_App);
    }
  }
}
