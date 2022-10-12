package com.vertafore.test.services.balance_journal_entries;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.BasicPolicyInfoResponse;
import com.vertafore.test.models.ems.CustomerInfoResponse;
import com.vertafore.test.models.ems.ImportBalanceJournalEntryResponse;
import com.vertafore.test.servicewrappers.UseBalanceJournalEntriesTo;
import com.vertafore.test.servicewrappers.UseCustomerTo;
import com.vertafore.test.util.CustomerUtil;
import com.vertafore.test.util.PolicyUtil;
import java.time.*;
import java.util.*;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;
import org.junit.runner.RunWith;

// TODO We still need a test that checks for financed policies, as it's a very common workflow
@RunWith(SerenityRunner.class)
public class PUT_BalanceJournalEntriesImportCustomer extends TokenSuperClass {

  // Helpers
  String currentDate = LocalDateTime.now().toString();

  public String generateCSVHeaders() {
    return "BJE Type,Customer Id,Customer Name,Policy Id,Policy Number,Invoice Balance,Late Charge Balance,Number Of Days Old,Financed Balance,Description"
        + System.lineSeparator();
  }

  public String generateCSVRowForFirstPolicyFound() {
    Actor AADM_User = theActorCalled("AADM_User");

    String policyId = "";
    String customerId = "";
    String customerType = "";
    BasicPolicyInfoResponse randomPolicy = new BasicPolicyInfoResponse();
    CustomerInfoResponse randomCustomer = new CustomerInfoResponse();

    UseCustomerTo customersApi = new UseCustomerTo();

    // get policy info ensuring that customer is not of type Suspect or Prospect
    do {
      randomPolicy = PolicyUtil.selectRandomPolicy(AADM_User, "policy");
      assertThat(randomPolicy).isNotNull();
      assertThat(randomPolicy.getPolicyId()).isNotNull();
      assertThat(randomPolicy.getCustomerId()).isNotNull();

      policyId = randomPolicy.getPolicyId();
      customerId = randomPolicy.getCustomerId();

      randomCustomer = CustomerUtil.getCustomerInfoByCustomerId(AADM_User, customerId);
      assertThat(randomCustomer).isNotNull();
    } while (!randomCustomer.getCustomerType().equals("Customer"));

    int invBalance = (int) (Math.random() * (200001) - 100000);
    int lateCh = (int) (Math.random() * 11);
    int daysOld = (int) (Math.random() * (10) + 1);
    int financedBal = 0;

    return "Customer,"
        + customerId
        + ",,"
        + policyId
        + ",,"
        + invBalance
        + ","
        + lateCh
        + ","
        + daysOld
        + ","
        + financedBal;
  }

  @Test
  public void balanceJournalEntriesWithExistingCustomerAndPolicyReturnsSuccessfulResponse() {
    Actor AADM_User = theActorCalled("AADM_User");
    String headers = generateCSVHeaders();
    String testCsv = generateCSVRowForFirstPolicyFound();

    // Create body and import first BJE
    String firstCsv = headers + testCsv + ",Automated Test Part 1" + System.lineSeparator();
    String firstCsvContent = new String(Base64.getEncoder().encode(firstCsv.getBytes()));
    HashMap<String, Object> body1 = new HashMap<>();
    body1.put("balanceJournalEntryType", "Customer");
    body1.put("csvFileData", firstCsvContent);
    body1.put("journalEntryDate", currentDate);
    body1.put("ignoreWarnings", "true");
    body1.put("description", "Automated Test Part 1");

    UseBalanceJournalEntriesTo balanceJournalEntriesApi = new UseBalanceJournalEntriesTo();

    AADM_User.attemptsTo(
        balanceJournalEntriesApi
            .PUTBalanceJournalEntriesCustomerImportOnTheBalancejournalentriesController(
                body1, "string"));

    SerenityRest.lastResponse().prettyPrint();

    ImportBalanceJournalEntryResponse response1 =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", ImportBalanceJournalEntryResponse.class);

    assertThat(response1.getBalanceJournalEntryCollectionId()).isNotNull();
    assertThat(response1.getBalanceJournalEntryType()).isEqualTo("Customer");
    assertThat(response1.getCollectionDescription()).isEqualTo("Automated Test Part 1");
    assertThat(response1.getNumberOfBalanceJournalEntries()).isEqualTo(1);
    assertThat(response1.getNumberOfErrors()).isEqualTo(0);

    // Create body from a different CSV and import
    String secondCsv = headers + testCsv + ",Automated Test Part 2" + System.lineSeparator();
    String secondCsvContent = new String(Base64.getEncoder().encode(secondCsv.getBytes()));
    HashMap<String, Object> body2 = new HashMap<>();
    body2.put("balanceJournalEntryType", "Customer");
    body2.put("csvFileData", secondCsvContent);
    body2.put("journalEntryDate", currentDate);
    body2.put("ignoreWarnings", "true");
    body2.put("description", "Automated Test Part 2");

    AADM_User.attemptsTo(
        balanceJournalEntriesApi
            .PUTBalanceJournalEntriesCustomerImportOnTheBalancejournalentriesController(
                body2, "string"));

    SerenityRest.lastResponse().prettyPrint();

    ImportBalanceJournalEntryResponse response2 =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", ImportBalanceJournalEntryResponse.class);

    assertThat(response2.getBalanceJournalEntryCollectionId()).isNotNull();
    assertThat(response2.getNumberOfBalanceJournalEntries()).isEqualTo(1);
    assertThat(response2.getNumberOfErrors()).isEqualTo(0);

    // Importing multiple times for same policy should respond with a warning
    assertThat(response2.getNumberOfWarnings()).isGreaterThanOrEqualTo(1);
  }

  @Test
  public void
      multipleBalanceJournalEntriesWithSameCustomerAndPolicyReturnsMultipleSuccessfulResponses() {
    int balanceJournalEntriesToCreate = 10;

    Actor AADM_User = theActorCalled("AADM_User");

    String headers = generateCSVHeaders();

    // Create csv with different customers and policies
    StringBuilder testCsv = new StringBuilder(headers);
    for (int i = 0; i < balanceJournalEntriesToCreate; i++) {
      testCsv
          .append(generateCSVRowForFirstPolicyFound())
          .append(",Automated Batch Test")
          .append(System.lineSeparator());
    }

    String csvContent = new String(Base64.getEncoder().encode(testCsv.toString().getBytes()));
    HashMap<String, Object> body = new HashMap<>();
    body.put("balanceJournalEntryType", "Customer");
    body.put("csvFileData", csvContent);
    body.put("journalEntryDate", currentDate);
    body.put("ignoreWarnings", "true");
    body.put("description", "Batch of " + balanceJournalEntriesToCreate + " BJEs");

    UseBalanceJournalEntriesTo balanceJournalEntriesApi = new UseBalanceJournalEntriesTo();

    AADM_User.attemptsTo(
        balanceJournalEntriesApi
            .PUTBalanceJournalEntriesCustomerImportOnTheBalancejournalentriesController(
                body, "string"));

    SerenityRest.lastResponse().prettyPrint();

    ImportBalanceJournalEntryResponse response1 =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", ImportBalanceJournalEntryResponse.class);

    assertThat(response1.getBalanceJournalEntryCollectionId()).isNotNull();
    assertThat(response1.getBalanceJournalEntryType()).isEqualTo("Customer");
    assertThat(response1.getCollectionDescription())
        .isEqualTo("Batch of " + balanceJournalEntriesToCreate + " BJEs");
    assertThat(response1.getNumberOfBalanceJournalEntries())
        .isEqualTo(balanceJournalEntriesToCreate);
    assertThat(response1.getNumberOfErrors()).isEqualTo(0);
  }
}
