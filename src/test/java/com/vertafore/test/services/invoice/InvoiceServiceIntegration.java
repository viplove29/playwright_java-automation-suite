package com.vertafore.test.services.invoice;

import com.vertafore.test.models.TitanUser;
import com.vertafore.test.tasks.servicewrappers.accounting.UseAccountingServiceTo;
import com.vertafore.test.tasks.servicewrappers.customer.UseCustomerServiceTo;
import com.vertafore.test.tasks.servicewrappers.invoice.UseInvoiceServiceTo;
import com.vertafore.test.tasks.servicewrappers.policy.UsePolicyServiceTo;
import com.vertafore.test.tasks.utilities.UpdateTheir;
import com.vertafore.test.utilities.actorextractor.JsonToActorsConverter;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.ensure.Ensure;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;

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
        if(SerenityRest.lastResponse().statusCode() == 404){
            String journal = "{\n" +
                    "\t\"label\": \"test accrual journal\",\n" +
                    "\t\"journalType\": \"ACCRUAL\",\n" +
                    "\t\"fiscalMonth\": 4\n" +
                    "}";
//            currentActor.attemptsTo(UseAccountingServiceTo.createJournal());
        }

        currentActor.attemptsTo(UpdateTheir.serviceTo("customer"));
        //add customer request body
//        currentActor.attemptsTo(UseCustomerServiceTo.createCustomerUsingPost());

        currentActor.attemptsTo(UpdateTheir.serviceTo("policy"));
        //add policy request body
//        currentActor.attemptsTo(UsePolicyServiceTo.createPolicyUsingPost());

        currentActor.attemptsTo(UpdateTheir.serviceTo("invoice"));
        //add policy id, charge body and filter params?
//        currentActor.attemptsTo(UseInvoiceServiceTo.getApplicablePolicyChargesUsingGet());
//        currentActor.attemptsTo(UseInvoiceServiceTo.createInvoiceUsingPost());
        currentActor.attemptsTo(Ensure.that(SerenityRest.lastResponse().statusCode()).isBetween(200, 299));


        //add journal id and delete journal after test
        //        currentActor.attemptsTo(UpdateTheir.serviceTo("accounting"), UseAccountingServiceTo.deleteJournalById());

    }
}
