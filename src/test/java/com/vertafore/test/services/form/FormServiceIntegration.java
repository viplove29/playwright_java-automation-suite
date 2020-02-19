package com.vertafore.test.services.form;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;

import com.vertafore.test.actors.JsonToActorsConverter;
import com.vertafore.test.models.TitanUser;
import com.vertafore.test.tasks.servicewrappers.accounting.UseAccountingServiceTo;
import com.vertafore.test.tasks.utilities.UpdateTheir;
import java.util.ArrayList;
import java.util.List;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.ensure.Ensure;
import org.json.simple.parser.ParseException;
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
  public void testJonHasActiveJournal() throws ParseException {
    Actor currentActor = theActorCalled("Jon Duncan");

    currentActor.attemptsTo(UpdateTheir.serviceTo("accounting"));

    currentActor.attemptsTo(UseAccountingServiceTo.getActiveJournal());
    int statusCode = SerenityRest.lastResponse().statusCode();
    currentActor.attemptsTo(Ensure.that(statusCode).isBetween(200, 299));
  }
}
