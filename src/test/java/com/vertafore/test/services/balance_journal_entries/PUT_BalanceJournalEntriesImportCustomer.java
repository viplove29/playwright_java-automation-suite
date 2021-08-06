package com.vertafore.test.services.balance_journal_entries;

import static com.vertafore.test.actor.BuildEMSCast.GetAnAccessToken;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.models.EMSActor;
import com.vertafore.test.models.ems.BasicPolicyInfoResponse;
import com.vertafore.test.models.ems.CustomerResponse;
import com.vertafore.test.models.ems.ImportBalanceJournalEntryResponse;
import com.vertafore.test.servicewrappers.UseBalanceJournalEntriesTo;
import com.vertafore.test.servicewrappers.UseCustomersTo;
import com.vertafore.test.servicewrappers.UsePoliciesTo;
import java.time.*;
import java.util.*;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class PUT_BalanceJournalEntriesImportCustomer {
  private List<EMSActor> actors = new ArrayList<>();

  @Before
  public void getAnAccessToken() {
    actors.addAll(List.of(new EMSActor().called("bob").withContext("userContext")));
    OnStage.setTheStage(GetAnAccessToken(actors));
  }

  // Helpers
  public String generateCSVHeaders() {
    return "BJE Type,Customer Id,Customer Name,Policy Id,Policy Number,Invoice Balance,Late Charge Balance,Number Of Days Old,Financed Balance,Description"
        + System.lineSeparator();
  }

  public String generateCSVRowForFirstPolicyFound() {
    Actor bob = theActorCalled("bob");

    // Get list of all customers in environment
    UseCustomersTo customersApi = new UseCustomersTo();
    bob.attemptsTo(customersApi.GETCustomersOnTheCustomersController(null, "string"));
    List<CustomerResponse> customers =
        LastResponse.received()
            .answeredBy(bob)
            .getBody()
            .jsonPath()
            .getList("customerList", CustomerResponse.class);

    // Check each customer until a policy is found
    boolean policyFound = false;
    String customerId = "";
    String policyId = "";

    List<Integer> checkedCustIndexes = new ArrayList<>();
    Random randNum = new Random();
    int customerIndex;

    while (!policyFound && checkedCustIndexes.size() < customers.size()) {
      customerIndex = randNum.nextInt(customers.size());
      while (checkedCustIndexes.contains(customerIndex)) {
        customerIndex = randNum.nextInt(customers.size());
      }
      checkedCustIndexes.add(customerIndex);
      CustomerResponse customer = customers.get(customerIndex);

      // TODO change this endpoint to POST_policies/search
      UsePoliciesTo policiesApi = new UsePoliciesTo();
      bob.attemptsTo(
          policiesApi.GETPoliciesOnThePoliciesControllerDeprecated(
              customer.getCustId(), true, "string"));

      List<BasicPolicyInfoResponse> policies =
          LastResponse.received()
              .answeredBy(bob)
              .getBody()
              .jsonPath()
              .getList("", BasicPolicyInfoResponse.class);

      if (policies.size() > 0) {
        policyFound = true;
        BasicPolicyInfoResponse policy = policies.get(0);
        customerId = policy.getCustomerId();
        policyId = policy.getPolicyId();
      } else {
        System.out.println("No policies exist for Customer Id " + customer.getCustId());
      }
    }

    // If no policy is found, the test cannot be run
    assertThat(policyFound)
        .as("No policies exist for the current agency so BJE cannot be imported.")
        .isTrue();

    return "Customer," + customerId + ",," + policyId + ",,";
  }

  @Test
  public void balanceJournalEntriesWithExistingCustomerAndPolicyReturnsSuccessfulResponse() {
    Actor bob = theActorCalled("bob");
    String headers = generateCSVHeaders();
    String testCsv = generateCSVRowForFirstPolicyFound();

    // Create body and import first BJE
    int invBalance1 = (int) (Math.random() * (200001) - 100000);
    int lateCh1 = (int) (Math.random() * 11);
    int daysOld1 = (int) (Math.random() * (10) + 1);
    String currentDate = LocalDateTime.now().toString();

    String firstCsv =
        headers
            + testCsv
            + invBalance1
            + ","
            + lateCh1
            + ","
            + daysOld1
            + ",0,Automated Test Part 1"
            + System.lineSeparator();
    String firstCsvContent = new String(Base64.getEncoder().encode(firstCsv.getBytes()));
    HashMap<String, Object> body1 = new HashMap<>();
    body1.put("balanceJournalEntryType", "Customer");
    body1.put("csvFileData", firstCsvContent);
    body1.put("journalEntryDate", currentDate);
    body1.put("ignoreWarnings", "true");
    body1.put("description", "Automated Test Part 1");

    UseBalanceJournalEntriesTo balanceJournalEntriesApi = new UseBalanceJournalEntriesTo();

    bob.attemptsTo(
        balanceJournalEntriesApi
            .PUTBalanceJournalEntriesCustomerImportOnTheBalancejournalentriesController(
                body1, "string"));

    SerenityRest.lastResponse().prettyPrint();

    ImportBalanceJournalEntryResponse response1 =
        LastResponse.received()
            .answeredBy(bob)
            .getBody()
            .jsonPath()
            .getObject("", ImportBalanceJournalEntryResponse.class);

    assertThat(response1.getBalanceJournalEntryCollectionId()).isNotNull();
    assertThat(response1.getBalanceJournalEntryType()).isEqualTo("Customer");
    assertThat(response1.getCollectionDescription()).isEqualTo("Automated Test Part 1");
    assertThat(response1.getNumberOfBalanceJournalEntries()).isEqualTo(1);
    assertThat(response1.getNumberOfErrors()).isEqualTo(0);

    // Create body from a different CSV and import
    int invBalance2 = (int) (Math.random() * (200001) - 100000);
    int lateCh2 = (int) (Math.random() * 11);
    int daysOld2 = (int) (Math.random() * (10) + 1);

    String secondCsv =
        headers
            + testCsv
            + invBalance2
            + ","
            + lateCh2
            + ","
            + daysOld2
            + ",0,Automated Test Part 6"
            + System.lineSeparator();
    String secondCsvContent = new String(Base64.getEncoder().encode(secondCsv.getBytes()));
    HashMap<String, Object> body2 = new HashMap<>();
    body2.put("balanceJournalEntryType", "Customer");
    body2.put("csvFileData", secondCsvContent);
    body2.put("journalEntryDate", currentDate);
    body2.put("ignoreWarnings", "true");
    body2.put("description", "Automated Test Part 2");

    bob.attemptsTo(
        balanceJournalEntriesApi
            .PUTBalanceJournalEntriesCustomerImportOnTheBalancejournalentriesController(
                body2, "string"));

    SerenityRest.lastResponse().prettyPrint();

    ImportBalanceJournalEntryResponse response2 =
        LastResponse.received()
            .answeredBy(bob)
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

    Actor bob = theActorCalled("bob");

    String headers = generateCSVHeaders();

    // Create csv with different customers and policies
    StringBuilder testCsv = new StringBuilder(headers);
    for (int i = 0; i < balanceJournalEntriesToCreate; i++) {
      testCsv
          .append(generateCSVRowForFirstPolicyFound())
          .append("61424,5,1,0,Automated Batch Test")
          .append(System.lineSeparator());
    }

    String csvContent = new String(Base64.getEncoder().encode(testCsv.toString().getBytes()));
    HashMap<String, Object> body = new HashMap<>();
    body.put("balanceJournalEntryType", "Customer");
    body.put("csvFileData", csvContent);
    body.put("journalEntryDate", "2021-04-15T15:39:47.337Z");
    body.put("ignoreWarnings", "true");
    body.put("description", "Batch of " + balanceJournalEntriesToCreate + " BJEs");

    UseBalanceJournalEntriesTo balanceJournalEntriesApi = new UseBalanceJournalEntriesTo();

    bob.attemptsTo(
        balanceJournalEntriesApi
            .PUTBalanceJournalEntriesCustomerImportOnTheBalancejournalentriesController(
                body, "string"));

    SerenityRest.lastResponse().prettyPrint();

    ImportBalanceJournalEntryResponse response1 =
        LastResponse.received()
            .answeredBy(bob)
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
