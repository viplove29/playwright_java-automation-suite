package com.vertafore.test.services.customers;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.SecuredCustomerBasicInfoResponse;
import com.vertafore.test.util.CustomerUtil;
import java.util.List;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;

public class GET_SecuredCustomers extends TokenSuperClass {

  @Test
  public void getSecuredCustomersReturnsAllSecuredCustomers() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");
    List<SecuredCustomerBasicInfoResponse> securedCustomers;

    // AADM TEST, only one that has access
    securedCustomers = CustomerUtil.getAllSecuredCustomers(AADM_User);
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    assertThat(securedCustomers).isNotNull();
    assertThat(securedCustomers.size()).isGreaterThan(0);

    // ORAN Partner test, no access to this endpoint
    securedCustomers = CustomerUtil.getAllSecuredCustomers(ORAN_App);
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
    assertThat(securedCustomers).isNull();

    // VADM test, no access to this endpoint
    securedCustomers = CustomerUtil.getAllSecuredCustomers(VADM_Admin);
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
    assertThat(securedCustomers).isNull();
  }
}
