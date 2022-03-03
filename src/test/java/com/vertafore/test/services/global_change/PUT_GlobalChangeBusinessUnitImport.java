package com.vertafore.test.services.global_change;

import static com.vertafore.test.actor.BuildEMSCast.GetAnAccessToken;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.models.EMSActor;
import com.vertafore.test.models.ems.BasicPolicyInfoResponse;
import com.vertafore.test.models.ems.BusinessUnitDetailResponse;
import com.vertafore.test.servicewrappers.UseEmployeeTo;
import com.vertafore.test.servicewrappers.UseGlobalChangeTo;
import com.vertafore.test.util.PolicyUtil;
import java.util.*;
import java.util.stream.Collectors;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class PUT_GlobalChangeBusinessUnitImport {

  private List<EMSActor> actors = new ArrayList<>();

  @Before
  public void getAnAccessToken() {
    actors.addAll(List.of(new EMSActor().called("bob").withContext("userContext")));
    OnStage.setTheStage(GetAnAccessToken(actors));
  }

  // helpers
  public String generateCSVHeaders(String type, Boolean reverse) {
    String firstColumnHeader;
    switch (type.toUpperCase()) {
      case "POLICY":
        firstColumnHeader = "Policy Id";
        break;
      case "CUSTOMER":
        firstColumnHeader = "Customer Id";
        break;
      default:
        throw new IllegalArgumentException("Type must be POLICY or CUSTOMER.");
    }

    String currentBUHeaders =
        "Current Policy Division,Current Policy Branch,Current Policy Department,Current Policy Group";
    String newBUHeaders =
        "New Policy Division,New Policy Branch,New Policy Department,New Policy Group";

    // The headers can be flipped to revert Global Change Business Unit
    if (reverse) {
      return firstColumnHeader
          + ","
          + newBUHeaders
          + ","
          + currentBUHeaders
          + System.lineSeparator();
    }
    return firstColumnHeader + "," + currentBUHeaders + "," + newBUHeaders + System.lineSeparator();
  }

  public String formatBusinessUnitForCSVRow(BusinessUnitDetailResponse buDetails) {
    return buDetails.getDivisionInfo().getName()
        + ","
        + buDetails.getBranchInfo().getName()
        + ","
        + buDetails.getDepartmentInfo().getName()
        + ","
        + buDetails.getGroupInfo().getName();
  }

  /* Given a policy object, get the matching BU as well as a non-matching BU to serve as the
  / destination */
  public String generateCSVRowFromPolicy(Actor actor, BasicPolicyInfoResponse policy) {
    String policyId = policy.getPolicyId();
    String groupCode = policy.getGlGroupCode();

    UseEmployeeTo employeeApi = new UseEmployeeTo();
    actor.attemptsTo(employeeApi.GETEmployeeBusinessUnitsOnTheEmployeesController());

    List<BusinessUnitDetailResponse> buResponse =
        LastResponse.received()
            .answeredBy(actor)
            .getBody()
            .jsonPath()
            .getList("", BusinessUnitDetailResponse.class);

    BusinessUnitDetailResponse fromBUDetails =
        buResponse
            .stream()
            .filter(divName -> divName.getGroupInfo().getCode().equals(groupCode))
            .collect(Collectors.toList())
            .get(0);

    BusinessUnitDetailResponse toBUDetails =
        buResponse
            .stream()
            .filter(divName -> !divName.getGroupInfo().getCode().equals(groupCode))
            .collect(Collectors.toList())
            .get(0);

    String fromBU = formatBusinessUnitForCSVRow(fromBUDetails);
    String toBU = formatBusinessUnitForCSVRow(toBUDetails);

    return policyId + "," + fromBU + "," + toBU;
  }

  @Test
  public void globalChangeBusinessUnitForPolicyOnlyUpdatesPolicyBusinessUnit() {
    Actor bob = theActorCalled("bob");

    // Part 1: Perform change

    // get random policy & create the row for the Global Change BU CSV
    BasicPolicyInfoResponse randomPolicy = PolicyUtil.selectRandomPolicy(bob, "policy");
    String randomPolicyId = randomPolicy.getPolicyId();
    String csvRow = generateCSVRowFromPolicy(bob, randomPolicy);

    // combine headers with csv row to create the CSV & request for performing the change
    String csvHeaders = generateCSVHeaders("policy", false);
    String csv = csvHeaders + csvRow + System.lineSeparator();
    System.out.println(csv);
    String csvContent = new String(Base64.getEncoder().encode(csv.getBytes()));
    HashMap<String, Object> body1 = new HashMap<>();
    body1.put("csvFileData", csvContent);
    body1.put("globalChangeBusinessUnitType", "Policy Only");
    body1.put("fileName", "GCBU1.csv");

    UseGlobalChangeTo globalChangeApi = new UseGlobalChangeTo();

    // save a copy of policy object before change in order to wait for policy to update
    BasicPolicyInfoResponse policyBeforeChange = PolicyUtil.getPolicyById(bob, randomPolicyId);
    bob.attemptsTo(
        globalChangeApi.PUTGlobalChangeBusinessUnitImportOnTheBusinessunitglobalchangeController(
            body1, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    SerenityRest.lastResponse().prettyPrint();

    // wait for policy to update before comparing glGroupCodes to verify BU has changed
    BasicPolicyInfoResponse changedPolicy =
        PolicyUtil.waitForDelayedPolicyUpdateAndGetPolicyById(
            bob, randomPolicyId, policyBeforeChange);
    assertThat(changedPolicy.getGlGroupCode()).isNotEqualTo(randomPolicy.getGlGroupCode());

    // Part 2: Revert change

    // combine REVERSED headers with original csv row to create the CSV & request for reverting the
    // change
    String reversedCsvHeaders = generateCSVHeaders("policy", true);
    String reversedCsv = reversedCsvHeaders + csvRow + System.lineSeparator();
    System.out.println(reversedCsv);
    String reversedCsvContent = new String(Base64.getEncoder().encode(reversedCsv.getBytes()));
    HashMap<String, Object> body2 = new HashMap<>();
    body2.put("csvFileData", reversedCsvContent);
    body2.put("globalChangeBusinessUnitType", "Policy Only");
    body2.put("fileName", "GCBU2.csv");

    // no need to save a copy of policy object since it was already saved after the first GCBU
    // request
    bob.attemptsTo(
        globalChangeApi.PUTGlobalChangeBusinessUnitImportOnTheBusinessunitglobalchangeController(
            body2, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    SerenityRest.lastResponse().prettyPrint();

    // wait for policy to update before comparing glGroupCodes to verify BU is the same as it was
    // before GCBU request
    BasicPolicyInfoResponse revertedPolicy =
        PolicyUtil.waitForDelayedPolicyUpdateAndGetPolicyById(bob, randomPolicyId, changedPolicy);
    assertThat(revertedPolicy.getGlGroupCode()).isEqualTo(randomPolicy.getGlGroupCode());
  }
}
