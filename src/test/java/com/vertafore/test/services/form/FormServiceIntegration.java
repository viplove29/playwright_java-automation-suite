package com.vertafore.test.services.form;

import com.vertafore.test.actors.JsonToActorsConverter;
import com.vertafore.test.models.TitanUser;
import com.vertafore.test.utilities.ServiceWrapperAndModelClassGenerator;
import java.util.ArrayList;
import java.util.List;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.actors.OnStage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

// JUNIT TEST ("step-definition"- like layer) for form-service integration tests
@RunWith(SerenityRunner.class)
public class FormServiceIntegration {

  private List<TitanUser> users = new ArrayList<>();

  @Before
  public void setupActors() {
    users.add(new TitanUser("Have Everything", "Testing all permissions", "TESTALL"));
    users.add(new TitanUser("Jon Duncan", "Risk Advisors LLC", "RISK123"));
    OnStage.setTheStage(JsonToActorsConverter.castOfAuthenticatedActors(users));
  }

  @Test
  public void testJonHasActiveJournal() {
    //        Actor currentActor = theActorCalled("Jon Duncan");
    //
    //        currentActor.attemptsTo(UpdateTheir.serviceTo("accounting"));
    //
    //        currentActor.attemptsTo(UseAccountingServiceTo.getActiveJournal());
    //        int statusCode = SerenityRest.lastResponse().statusCode();
    //        currentActor.attemptsTo(Ensure.that(statusCode).isBetween(200, 299));
    //
    //        String id = SerenityRest.lastResponse().getBody().jsonPath().getString("content.id");
    //
    //        currentActor.attemptsTo(UseAccountingServiceTo.deleteJournalById(id));
    //        statusCode = SerenityRest.lastResponse().statusCode();
    //        currentActor.attemptsTo(Ensure.that(statusCode).isBetween(200, 299));
    //
    //        HashMap<String, String> body = new HashMap<>();
    //        body.put("label", "test accrual journal");
    //        body.put("journalType", "ACCRUAL");
    //        body.put("fiscalMonth", "4");
    //
    //
    //
    // currentActor.attemptsTo(UseAccountingServiceTo.createJournal(JsonHelper.serializeAsJson(body)));
    //        statusCode = SerenityRest.lastResponse().statusCode();
    //        currentActor.attemptsTo(Ensure.that(statusCode).isBetween(200, 299));

    ServiceWrapperAndModelClassGenerator.generateServiceWrapperClasses();
    ServiceWrapperAndModelClassGenerator.generateModelClasses();
  }
}
