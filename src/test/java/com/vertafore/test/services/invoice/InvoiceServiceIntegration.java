package com.vertafore.test.services.invoice;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;

import com.vertafore.test.models.TitanUser;
import com.vertafore.test.tasks.servicewrappers.accounting.UseAccountingServiceTo;
import com.vertafore.test.tasks.servicewrappers.customer.UseCustomerServiceTo;
import com.vertafore.test.tasks.servicewrappers.invoice.UseInvoiceServiceTo;
import com.vertafore.test.tasks.servicewrappers.policy.UsePolicyServiceTo;
import com.vertafore.test.tasks.utilities.UpdateTheir;
import com.vertafore.test.utilities.actorextractor.JsonToActorsConverter;
import java.util.ArrayList;
import java.util.List;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.ensure.Ensure;
import org.junit.Before;
import org.junit.Test;

public class InvoiceServiceIntegration {

  private List<TitanUser> users = new ArrayList<>();

  @Before
  public void setupActors() {
    users.add(new TitanUser("Have Everything", "Testing all permissions", "TESTALL"));
    users.add(new TitanUser("Jon Duncan", "Risk Advisors LLC", "RISK123"));
    OnStage.setTheStage(JsonToActorsConverter.castOfAuthenticatedActors(users));
  }

  @Test
  public void testCreatingChargePolicyThenInvoice() {
    Actor currentActor = theActorCalled("Jon Duncan");

    currentActor.attemptsTo(UpdateTheir.serviceTo("accounting"));
    currentActor.attemptsTo(UseAccountingServiceTo.getActiveJournal());
    if (SerenityRest.lastResponse().statusCode() == 404) {
      String journal =
          "{\n"
              + "\t\"label\": \"test accrual journal\",\n"
              + "\t\"journalType\": \"ACCRUAL\",\n"
              + "\t\"fiscalMonth\": 4\n"
              + "}";
      currentActor.attemptsTo(UseAccountingServiceTo.createJournal(journal));
    }

    currentActor.attemptsTo(UpdateTheir.serviceTo("customer"));
    String customer =
        "{\n"
            + "    \"name\": {\n"
            + "      \"familyName\": \"Sunshine\",\n"
            + "      \"givenName\": \"Johnny\",\n"
            + "      \"middleName\": \"\"\n"
            + "    },\n"
            + "        \"type\": \"Personal\",\n"
            + "    \"currentStatus\": \"PROSPECT\"\n"
            + "  }";
    currentActor.attemptsTo(UseCustomerServiceTo.createCustomerUsingPost(customer));
    String customerId = SerenityRest.lastResponse().getBody().jsonPath().get("content.id");

    currentActor.attemptsTo(UpdateTheir.serviceTo("policy"));
    String policy =
        String.format(
            "{\n"
                + "    \"policyNumber\": \"AcctAction-Test\",\n"
                + "    \"policyStatus\": \"ACTIVE\",\n"
                + "    \"customerId\": \"%s\",\n"
                + "    \"effectiveDate\": \"1970-01-01\",\n"
                + "    \"expirationDate\": \"1970-01-01\",\n"
                + "    \"exposureGroupId\": {\n"
                + "        \"id\": \"{{exposureGroupId}}\",\n"
                + "        \"productId\": \"string\",\n"
                + "        \"tenantId\": \"string\",\n"
                + "        \"entityId\": \"string\"\n"
                + "    },\n"
                + "    \"carrierId\": \"10000000760001\",\n"
                + "    \"premium\": 141,\n"
                + "    \"namedInsureds\": [\n"
                + "    ],\n"
                + "    \"policyProductLinesOfBusiness\": [\n"
                + "        {\n"
                + "            \"productLineOfBusinessId\": {\n"
                + "                \"id\": \"lobID\",\n"
                + "                \"tenantId\": \"VERTAFORE\",\n"
                + "                \"entityId\": \"VERTAFORE\"\n"
                + "            },\n"
                + "            \"productLineOfCoverageId\": {\n"
                + "                \"id\": \"locID\",\n"
                + "                \"tenantId\": \"VERTAFORE\",\n"
                + "                \"entityId\": \"VERTAFORE\"\n"
                + "            }\n"
                + "        }\n"
                + "    ],\n"
                + "    \"billingType\": \"AGENCY\",\n"
                + "    \"agencyCommissionType\": \"PERCENTAGE\",\n"
                + "    \"agencyCommission\": 0.20\n"
                + "}",
            customerId);
    currentActor.attemptsTo(UsePolicyServiceTo.createPolicyUsingPost(policy));

    currentActor.attemptsTo(UpdateTheir.serviceTo("invoice"));
    //
    //        //add policy id, charge body and filter params?
    String policyId = (String) SerenityRest.lastResponse().getBody().jsonPath().get("content.id");
    currentActor.attemptsTo(
        UseInvoiceServiceTo.getApplicablePolicyChargesUsingGet(policyId, "1", "50"));
    //
    String chargeId =
        (String) SerenityRest.lastResponse().getBody().jsonPath().getList("content").get(0);
    String invoice =
        String.format(
            "{\n"
                + "\t\"policyId\": \"%s\",\n"
                + "\t\"currencyCode\": \"USD\",\n"
                + "\t\"notes\": \"This is an invoice to test the accounting actions feature.\",\n"
                + "\t\"dueDate\": \"1978-01-01T00:00:00.000Z\",\n"
                + "\t\"status\": \"POSTED\",\n"
                + "\t\"invoiceContact\": {\n"
                + "\t\t\"recipientName\": \"Dogan Insurance Agency\",\n"
                + "\t\t\"streetAddress\": \"100 Universal City Plaza\",\n"
                + "\t\t\"streetAddress2\": \"#201\",\n"
                + "\t\t\"city\": \"Hollywood\",\n"
                + "\t\t\"state\": \"CA\",\n"
                + "\t\t\"postalCode\": \"91608\"\n"
                + "\t},\n"
                + "\t\"lineItems\": [\n"
                + "\t\t{\n"
                + "\t\t\t\"chargeIds\": [{\n"
                + "\t\t\t\t\"tenantId\": \"RISK123\",\n"
                + "\t\t\t\t\"entityId\": \"RISK123\",\n"
                + "\t\t\t\t\"id\":\"%s\"\n"
                + "\t\t\t}],\n"
                + "\t\t\t\"amount\": 1200,\n"
                + "\t\t\t\"description\": \"Policy Premium\"\n"
                + "\t\t}\n"
                + "\t]\n"
                + "}",
            policyId, chargeId);
    currentActor.attemptsTo(UseInvoiceServiceTo.createInvoiceUsingPost(invoice));
    currentActor.attemptsTo(
        Ensure.that(SerenityRest.lastResponse().statusCode()).isBetween(200, 299));

    // add journal id and delete journal after test
    //        currentActor.attemptsTo(UpdateTheir.serviceTo("accounting"),
    // UseAccountingServiceTo.deleteJournalById());

  }
}
