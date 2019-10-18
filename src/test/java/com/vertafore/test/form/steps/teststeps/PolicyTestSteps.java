package com.vertafore.test.form.steps.teststeps;

import com.vertafore.test.common.models.services.policy.ContextualIdV1;
import com.vertafore.test.common.models.services.policy.PolicyProductLineOfBusinessV1;
import com.vertafore.test.common.models.services.policy.PolicyV1;
import com.vertafore.test.common.models.services.policy.PolicyVersionV1;
import com.vertafore.test.common.servicewrappers.policy.PolicyService;
import com.vertafore.test.common.util.ServiceUtils;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class PolicyTestSteps extends ScenarioSteps {

  private PolicyService policyService;
  private ServiceUtils serviceUtils;

  public PolicyTestSteps() throws IOException {
    policyService = new PolicyService();
    serviceUtils = new ServiceUtils();
  }
  // variables
  private PolicyV1 policy;
  private PolicyVersionV1 policyVersion;

  @Step
  public void createTitanPolicy() throws IOException {
    // log in and change contexts
    serviceUtils.checkLoginOrLogIn("DIA User", "policy", null, "DIA Entity Context");
    // create generic titan policy
    policy = policyService.postPolicy(createGenericPolicyBody());
    // save the created policy in session for later steps
    Serenity.setSessionVariable("policy").to(policy);
    Serenity.setSessionVariable("policyId").to(policy.id);
  }

  @Step
  public void createTitanPolicyVersion() throws IOException {
    // log in and change contexts
    serviceUtils.checkLoginOrLogIn("DIA User", "policy", null, "DIA Entity Context");

    // create the generic titan policy version
    policyVersion = policyService.postPolicyVersion(buildGenericPolicyVersionBody(), policy.id);
    // save the created policy version in session for later steps
    Serenity.setSessionVariable("policyVersion").to(policyVersion);
    Serenity.setSessionVariable("policyVersionId").to(policyVersion.id);
  }

  // private methods
  private PolicyV1 createGenericPolicyBody() {
    PolicyV1 policy = new PolicyV1();
    policy.policyProductLinesOfBusiness.add(
        new PolicyProductLineOfBusinessV1().createGenericProductLineOfBusiness());
    policy.namedInsureds.add("Queso");
    policy.namedInsureds.add("Salsa");
    policy.policyNumber = "12345";
    policy.policyStatus = "ACTIVE";
    policy.effectiveDate = LocalDate.parse("1970-01-01");
    policy.expirationDate = LocalDate.parse("2020-01-01");
    policy.carrierId = "931";
    policy.exposureGroupId = new ContextualIdV1().createDefaultContextualIdV1();
    policy.premium = BigDecimal.valueOf(1233);
    policy.billingType = "DIRECT";
    policy.agencyCommission = BigDecimal.valueOf(0.314);
    policy.producerCommission = BigDecimal.valueOf(.2718);
    return policy;
  }

  // POLICY VERSION
  public PolicyVersionV1 buildGenericPolicyVersionBody() {
    PolicyVersionV1 policyVersion = new PolicyVersionV1();
    ContextualIdV1 context = new ContextualIdV1().createDefaultContextualIdV1();
    policyVersion.namedInsureds.add("Taco");
    policyVersion.namedInsureds.add("Burrito");
    policyVersion.policyNumber = "12345";
    policyVersion.policyStatus = "ACTIVE";
    policyVersion.carrierId = "931";
    policyVersion.exposureGroupId = context;
    policyVersion.premium = BigDecimal.valueOf(1233.00);
    policyVersion.billingType = "DIRECT";
    policyVersion.earnedAgencyCommission = BigDecimal.valueOf(333.00);
    policyVersion.unearnedAgencyCommission = BigDecimal.valueOf(444.00);
    policyVersion.fullTermPremium = BigDecimal.valueOf(555.00);
    policyVersion.unearnedPremium = BigDecimal.valueOf(666.00);
    policyVersion.agencyCommission = BigDecimal.valueOf(0.314);
    policyVersion.producerCommission = BigDecimal.valueOf(.2718);

    policyVersion.cancellationType = "FLAT";
    policyVersion.cancellationReason = "OTHER";
    policyVersion.cancellationDescription = "cancellation_description";
    policyVersion.reinstatementDescription = "reinstatement_description";
    policyVersion.voidedReason = "OTHER";

    //    Reinstatement date must be after the cancellation date.
    //    Reinstatement date must be before the expiration date.
    //    policyVersion.reinstatementDate = LocalDate.parse("2019-01-01");
    //    Cancellation date must be before the expiration date
    //    Cancellation date must be after the effective date.
    policyVersion.cancellationDate = LocalDate.parse("2010-01-01");
    policyVersion.effectiveDate = LocalDate.parse("1970-01-01");
    policyVersion.expirationDate = LocalDate.parse("2020-01-01");
    return policyVersion;
  }
}
