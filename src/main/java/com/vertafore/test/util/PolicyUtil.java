package com.vertafore.test.util;

import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.models.ems.BasicPolicyInfoResponse;
import com.vertafore.test.models.ems.PagingRequestPoliciesSearchPostRequest;
import com.vertafore.test.models.ems.PagingResponseBasicPolicyInfoResponse;
import com.vertafore.test.models.ems.PoliciesSearchPostRequest;
import com.vertafore.test.servicewrappers.UsePoliciesTo;
import com.vertafore.test.servicewrappers.UseServiceAgreementsTo;
import com.vertafore.test.servicewrappers.UseSubmissionsTo;
import java.util.List;
import java.util.Random;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.joda.time.DateTime;

/*This class will work for any of the policy response endpoints, including Policies, Submissions, and Service Agreements */

public class PolicyUtil {

  // Validation Helpers
  public static String policyId;
  public static String customerId;
  public static String companyCode;
  public static String companyName;
  public static String writingCompanyCode;
  public static String policyNumber;
  public static DateTime policyEffectiveDate;
  public static DateTime policyExpirationDate;
  public static String csrCode;
  public static String executiveCode;
  public static String lineOfBusiness;
  public static String policySubType;
  public static String glDivisionCode;
  public static String glDepartmentCode;
  public static String glBranchCode;
  public static String glGroupCode;
  public static String renewalReportStatus;
  public static String description;
  public static String issuedState;
  public static String agencyNotation;
  public static String latestTransactionTranType;
  public static DateTime latestTransactionDate;

  public static BasicPolicyInfoResponse selectRandomPolicy(Actor actor, String policyType) {

    PagingRequestPoliciesSearchPostRequest pageSearch =
        new PagingRequestPoliciesSearchPostRequest();
    PoliciesSearchPostRequest polPostBody = new PoliciesSearchPostRequest();

    polPostBody.setIsCurrentlyInForce(false);
    polPostBody.setIncludeAllPolicyTypes(false);
    pageSearch.setModel(polPostBody);
    pageSearch.setTop(1000); // 1000 is the max that will return in a page

    switch (policyType) {
      case "policy":
        UsePoliciesTo policiesAPI = new UsePoliciesTo();
        actor.attemptsTo(
            (policiesAPI.POSTPoliciesSearchOnThePoliciesController(pageSearch, "string")));
        assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
        break;
      case "submission":
        UseSubmissionsTo submissionsAPI = new UseSubmissionsTo();
        actor.attemptsTo(
            (submissionsAPI.POSTSubmissionsSearchOnTheSubmissionsController(pageSearch, "string")));
        assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
        break;
      case "serviceAgreement":
        UseServiceAgreementsTo serviceAgreementsAPI = new UseServiceAgreementsTo();
        actor.attemptsTo(
            (serviceAgreementsAPI.POSTServiceAgreementsSearchOnTheServiceagreementsController(
                pageSearch, "string")));
        assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
        break;
      default:
        System.out.println("No policies exist of type " + policyType);
    }

    // this will get a full page of policies of the specified type
    List<BasicPolicyInfoResponse> allPols =
        LastResponse.received()
            .answeredBy(actor)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponseBasicPolicyInfoResponse.class)
            .getResponse();

    int polsIndex = new Random().nextInt(allPols.size());

    return allPols.get(polsIndex);
  }

  public static void setSinglePolicyResponseVariables(BasicPolicyInfoResponse onePolicy) {
    policyId = onePolicy.getPolicyId();
    customerId = onePolicy.getCustomerId();
    companyCode = onePolicy.getCompanyCode();
    companyName = onePolicy.getCompanyName();
    writingCompanyCode = onePolicy.getWritingCompanyCode();
    policyNumber = onePolicy.getPolicyNumber();
    policyEffectiveDate = onePolicy.getPolicyEffectiveDate();
    policyExpirationDate = onePolicy.getPolicyExpirationDate();
    csrCode = onePolicy.getCsrCode();
    executiveCode = onePolicy.getExecutiveCode();
    lineOfBusiness = onePolicy.getLineOfBusiness();
    policySubType = onePolicy.getPolicySubType();
    glDivisionCode = onePolicy.getGlDivisionCode();
    glDepartmentCode = onePolicy.getGlDepartmentCode();
    glBranchCode = onePolicy.getGlBranchCode();
    glGroupCode = onePolicy.getGlGroupCode();
    renewalReportStatus = onePolicy.getRenewalReportStatus();
    description = onePolicy.getDescription();
    issuedState = onePolicy.getIssuedState();
    agencyNotation = onePolicy.getAgencyNotation();
    latestTransactionTranType = onePolicy.getLatestTransactionTranType();
    latestTransactionDate = onePolicy.getLatestTransactionDate();
  }

  public static void validateSinglePolicyResponseVariables(BasicPolicyInfoResponse onePolicy) {
    assertThat(onePolicy.getPolicyId()).isEqualTo(policyId);
    assertThat(onePolicy.getCustomerId()).isEqualTo(customerId);
    assertThat(onePolicy.getCompanyCode()).isEqualTo(companyCode);
    assertThat(onePolicy.getCompanyName()).isEqualTo(companyName);
    assertThat(onePolicy.getWritingCompanyCode()).isEqualTo(writingCompanyCode);
    assertThat(onePolicy.getPolicyNumber()).isEqualTo(policyNumber);
    assertThat(onePolicy.getPolicyEffectiveDate()).isEqualTo(policyEffectiveDate);
    assertThat(onePolicy.getPolicyExpirationDate()).isEqualTo(policyExpirationDate);
    assertThat(onePolicy.getCsrCode()).isEqualTo(csrCode);
    assertThat(onePolicy.getExecutiveCode()).isEqualTo(executiveCode);
    assertThat(onePolicy.getLineOfBusiness()).isEqualTo(lineOfBusiness);
    assertThat(onePolicy.getPolicySubType()).isEqualTo(policySubType);
    assertThat(onePolicy.getGlDivisionCode()).isEqualTo(glDivisionCode);
    assertThat(onePolicy.getGlDepartmentCode()).isEqualTo(glDepartmentCode);
    assertThat(onePolicy.getGlBranchCode()).isEqualTo(glBranchCode);
    assertThat(onePolicy.getGlGroupCode()).isEqualTo(glGroupCode);
    assertThat(onePolicy.getRenewalReportStatus()).isEqualTo(renewalReportStatus);
    assertThat(onePolicy.getDescription()).isEqualTo(description);
    assertThat(onePolicy.getIssuedState()).isEqualTo(issuedState);
    assertThat(onePolicy.getAgencyNotation()).isEqualTo(agencyNotation);
    assertThat(onePolicy.getLatestTransactionTranType()).isEqualTo(latestTransactionTranType);
    assertThat(onePolicy.getLatestTransactionDate()).isEqualTo(latestTransactionDate);
  }
}
