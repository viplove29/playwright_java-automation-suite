package com.vertafore.test.services.service_agreements;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.BasicPolicyInfoResponse;
import com.vertafore.test.models.ems.PagingRequestServiceAgreementsSearchPostRequest;
import com.vertafore.test.models.ems.PagingResponseBasicServiceAgreementInfoResponse;
import com.vertafore.test.models.ems.ServiceAgreementsSearchPostRequest;
import com.vertafore.test.servicewrappers.UseServiceAgreementsTo;
import com.vertafore.test.util.AuthGroupUtility;
import com.vertafore.test.util.CustomerUtil;
import com.vertafore.test.util.EmployeeUtil;
import com.vertafore.test.util.PolicyUtil;
import java.util.List;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;

public class POST_ServiceAgreementsSearch extends TokenSuperClass {

  @Test
  public void serviceAgreementsSearchFiltersBySecuredCustomerAccess() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");

    String serviceEmployeeEmpCode = AuthGroupUtility.getCurrentServiceEmployeeEmpCode(AADM_User);

    // give Service Employee access to all secured customer Ids
    // have to iterate through all of them because not every customer might have a policy
    List<String> securedCustomerIds = CustomerUtil.getAllSecuredCustomerIds(AADM_User);
    EmployeeUtil.insertMultipleSecuredCustomerAccessesForEmployee(
        AADM_User, serviceEmployeeEmpCode, securedCustomerIds);

    // get a random policy attributed to a secured customer
    BasicPolicyInfoResponse randomSecuredPolicy;
    String randomSecuredCustomerId = "";
    String randomSecuredPolicyId = "";
    try {
      randomSecuredPolicy = PolicyUtil.getRandomSecuredCustomerPolicy(ORAN_App, securedCustomerIds);
      randomSecuredCustomerId = randomSecuredPolicy.getCustomerId();
      randomSecuredPolicyId = randomSecuredPolicy.getPolicyId();

    } catch (NullPointerException e) {
      System.out.println(
          "no policies found within list of secured customers, need to stage data in environment");
    }

    UseServiceAgreementsTo serviceAgreementsApi = new UseServiceAgreementsTo();

    // format request body with data
    ServiceAgreementsSearchPostRequest postRequest = new ServiceAgreementsSearchPostRequest();
    postRequest.setCustomerId(randomSecuredCustomerId);
    postRequest.setPolicyId(randomSecuredPolicyId);
    postRequest.setIncludeAllPolicyTypes(true);
    postRequest.setIsCurrentlyInForce(false);
    PagingRequestServiceAgreementsSearchPostRequest pagingRequest =
        new PagingRequestServiceAgreementsSearchPostRequest();
    pagingRequest.setModel(postRequest);

    // call endpoint, service employee has access, data retrieval is successful
    ORAN_App.attemptsTo(
        serviceAgreementsApi.POSTServiceAgreementsSearchOnTheServiceagreementsController(
            pagingRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    PagingResponseBasicServiceAgreementInfoResponse serviceAgreementInfoResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponseBasicServiceAgreementInfoResponse.class);
    assertThat(serviceAgreementInfoResponse.getTotalCount()).isEqualTo(1);
    assertThat(serviceAgreementInfoResponse.getResponse().get(0).getCustomerId())
        .isEqualTo(randomSecuredCustomerId);
    assertThat(serviceAgreementInfoResponse.getResponse().get(0).getPolicyId())
        .isEqualTo(randomSecuredPolicyId);

    // delete secured customer access to employee
    EmployeeUtil.deleteMultipleSecuredCustomerAccessForEmployee(
        AADM_User, serviceEmployeeEmpCode, securedCustomerIds);

    // call endpoint, service employee has no access, data retrieval unsuccessful
    ORAN_App.attemptsTo(
        serviceAgreementsApi.POSTServiceAgreementsSearchOnTheServiceagreementsController(
            pagingRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    serviceAgreementInfoResponse =
        LastResponse.received()
            .answeredBy(ORAN_App)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponseBasicServiceAgreementInfoResponse.class);

    assertThat(serviceAgreementInfoResponse.getTotalCount()).isEqualTo(0);
  }
}
