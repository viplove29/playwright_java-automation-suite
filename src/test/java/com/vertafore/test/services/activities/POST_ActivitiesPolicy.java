package com.vertafore.test.services.activities;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.ActivityIdResponse;
import com.vertafore.test.models.ems.BasicPolicyInfoResponse;
import com.vertafore.test.models.ems.PolicyActivitiesPostRequest;
import com.vertafore.test.models.ems.PolicyActivityPostRequest;
import com.vertafore.test.servicewrappers.UseActivitiesTo;
import com.vertafore.test.util.PolicyUtil;
import java.util.List;
import java.util.stream.Collectors;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class POST_ActivitiesPolicy extends TokenSuperClass {

  @Test
  public void PostActivitiesPolicySuccessfullyCreatesMultiActivity() {
    Actor AADM_User = theActorCalled("AADM_User");

    // creating models for request
    PolicyActivitiesPostRequest multiPolicyActivityBody = new PolicyActivitiesPostRequest();

    multiPolicyActivityBody.setDefaultTransactionDate(DateTime.now().toString());
    multiPolicyActivityBody.setDefaultComment("EMSActivity");
    multiPolicyActivityBody.setDefaultAction("Application");

    PolicyUtil policyUtil = new PolicyUtil();
    List<BasicPolicyInfoResponse> inputCustPolData =
        policyUtil
            .getAllPoliciesFromRandomCustomerWithMultiplePolicies(AADM_User)
            .stream()
            .limit(2)
            .collect(Collectors.toList());
    String inputCustId = inputCustPolData.get(0).getCustomerId();
    List<String> inptPolIdList =
        inputCustPolData
            .stream()
            .map(BasicPolicyInfoResponse::getPolicyId)
            .collect(Collectors.toList());
    policyUtil
        .getPolicyTransactions(AADM_User, inptPolIdList)
        .forEach(
            pt -> {
              PolicyActivityPostRequest policyActivityPostRequest = new PolicyActivityPostRequest();

              policyActivityPostRequest.setPolicyId(pt.getPolicyId());
              policyActivityPostRequest.setCustomerId(inputCustId);
              policyActivityPostRequest.setEndorsementEffectiveDate(
                  pt.getTransactionEffectiveDate());
              policyActivityPostRequest.setTransactionType(pt.getTransactionType());

              multiPolicyActivityBody.addPolicyActivitiesItem(policyActivityPostRequest);
            });

    // send activities/policy request
    UseActivitiesTo activitiesAPI = new UseActivitiesTo();
    AADM_User.attemptsTo(
        activitiesAPI.POSTActivitiesPolicyOnTheActivitiesController(
            multiPolicyActivityBody, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    List<ActivityIdResponse> activities =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getList("", ActivityIdResponse.class);

    activities.forEach(actId -> assertThat(actId.getActivityId()).isNotNull());
  }
}
