package com.vertafore.test.services.global_change_business_unit;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.BasicPolicyInfoResponse;
import com.vertafore.test.models.ems.CustomerBasicInfoResponse;
import com.vertafore.test.models.ems.CustomerInfoResponse;
import com.vertafore.test.servicewrappers.UseGlobalChangeTo;
import com.vertafore.test.util.CSVUtil;
import com.vertafore.test.util.CustomerUtil;
import com.vertafore.test.util.GlobalChangeUtil;
import com.vertafore.test.util.PolicyUtil;
import java.util.*;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class PUT_GlobalChangeBusinessUnitImport extends TokenSuperClass {

  @Test
  public void globalChangeBusinessUnitForPolicyOnlyUpdatesPolicyBusinessUnit()
      throws InterruptedException {
    Actor AADM_User = theActorCalled("AADM_User");

    // Part 1: Perform change
    BasicPolicyInfoResponse randomPolicy = PolicyUtil.selectRandomPolicy(AADM_User, "policy");
    String randomPolicyId = randomPolicy.getPolicyId();
    String csvRow = CSVUtil.generateGlobalChangeBUCSVRowFromPolicy(AADM_User, randomPolicy);

    String csvHeaders = CSVUtil.generateGlobalChangeBUCSVHeaders("policy", false);
    String csv = csvHeaders + csvRow + System.lineSeparator();
    HashMap<String, Object> body1 =
        GlobalChangeUtil.getBodyForGlobalChangeBusinessUnit(
            CSVUtil.toByteArray(csv),
            "policy",
            "EMS Automation",
            CSVUtil.generateUniqueFilename("GCBU"));

    UseGlobalChangeTo globalChangeApi = new UseGlobalChangeTo();

    BasicPolicyInfoResponse policyBeforeChange =
        PolicyUtil.getPolicyById(AADM_User, randomPolicyId);
    AADM_User.attemptsTo(
        globalChangeApi.PUTGlobalChangeBusinessUnitImportOnTheBusinessunitglobalchangeController(
            body1, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    BasicPolicyInfoResponse changedPolicy =
        PolicyUtil.waitForDelayedPolicyUpdateAndGetPolicyById(
            AADM_User, randomPolicyId, policyBeforeChange);

    assertThat(changedPolicy.getGlGroupCode()).isNotEqualTo(randomPolicy.getGlGroupCode());

    // Part 2: Revert change
    String reversedCsvHeaders = CSVUtil.generateGlobalChangeBUCSVHeaders("policy", true);
    String reversedCsv = reversedCsvHeaders + csvRow + System.lineSeparator();
    HashMap<String, Object> body2 =
        GlobalChangeUtil.getBodyForGlobalChangeBusinessUnit(
            CSVUtil.toByteArray(reversedCsv),
            "policy",
            "EMS Automation",
            CSVUtil.generateUniqueFilename("GCBU"));

    AADM_User.attemptsTo(
        globalChangeApi.PUTGlobalChangeBusinessUnitImportOnTheBusinessunitglobalchangeController(
            body2, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    BasicPolicyInfoResponse revertedPolicy =
        PolicyUtil.waitForDelayedPolicyUpdateAndGetPolicyById(
            AADM_User, randomPolicyId, changedPolicy);

    assertThat(revertedPolicy.getGlGroupCode()).isEqualTo(randomPolicy.getGlGroupCode());
  }

  @Test
  public void globalChangeBusinessUnitForCustomerOnlyUpdatesCustomerBusinessUnit()
      throws InterruptedException {
    Actor AADM_User = theActorCalled("AADM_User");

    // Part 1: Perform change
    CustomerBasicInfoResponse randomCustomer =
        CustomerUtil.selectRandomCustomer(AADM_User, "customer");
    String customerId = randomCustomer.getCustomerId();
    CustomerInfoResponse randomCustomerInfo =
        CustomerUtil.getCustomerInfoByCustomerId(AADM_User, customerId);
    String csvRow = CSVUtil.generateGlobalChangeBUCSVRowFromCustomer(AADM_User, randomCustomerInfo);

    String csvHeaders = CSVUtil.generateGlobalChangeBUCSVHeaders("customer", false);
    String csv = csvHeaders + csvRow + System.lineSeparator();
    HashMap<String, Object> body1 =
        GlobalChangeUtil.getBodyForGlobalChangeBusinessUnit(
            CSVUtil.toByteArray(csv),
            "customer",
            "EMS Automation",
            CSVUtil.generateUniqueFilename("GCBU"));
    UseGlobalChangeTo globalChangeApi = new UseGlobalChangeTo();

    AADM_User.attemptsTo(
        globalChangeApi.PUTGlobalChangeBusinessUnitImportOnTheBusinessunitglobalchangeController(
            body1, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    Thread.sleep(5000);

    CustomerInfoResponse updatedCustomerInfo =
        CustomerUtil.getCustomerInfoByCustomerId(AADM_User, customerId);
    assertThat(updatedCustomerInfo.getBusinessUnits().getGroupShortName())
        .isNotEqualTo(randomCustomerInfo.getBusinessUnits().getGroupShortName());

    // Part 2: Revert change
    String reversedCsvHeaders = CSVUtil.generateGlobalChangeBUCSVHeaders("customer", true);
    String reversedCsv = reversedCsvHeaders + csvRow + System.lineSeparator();
    HashMap<String, Object> body2 =
        GlobalChangeUtil.getBodyForGlobalChangeBusinessUnit(
            CSVUtil.toByteArray(reversedCsv),
            "customer",
            "EMS Automation",
            CSVUtil.generateUniqueFilename("GCBU"));

    AADM_User.attemptsTo(
        globalChangeApi.PUTGlobalChangeBusinessUnitImportOnTheBusinessunitglobalchangeController(
            body2, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    Thread.sleep(5000);

    CustomerInfoResponse revertedCustomerInfo =
        CustomerUtil.getCustomerInfoByCustomerId(AADM_User, customerId);
    assertThat(revertedCustomerInfo.getBusinessUnits().getGroupShortName())
        .isEqualTo(randomCustomerInfo.getBusinessUnits().getGroupShortName());
  }
}
