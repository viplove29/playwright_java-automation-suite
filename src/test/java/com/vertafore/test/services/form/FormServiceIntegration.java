package com.vertafore.test.services.form;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

import com.vertafore.test.abilities.HaveTitanContext;
import com.vertafore.test.actor.titan.BuildCastOfTitanUsers;
import com.vertafore.test.models.TitanUser;
import com.vertafore.test.servicewrappers.UseFormTo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class FormServiceIntegration {

  private List<TitanUser> users = new ArrayList<>();

  @Before
  public void setupActors() {
    users.add(new TitanUser("titan.support1@vertafore.com", "VERTAFORE", "VERTAFORE"));
    OnStage.setTheStage(BuildCastOfTitanUsers.loadAndAuthenticate(users));
  }

  @Test
  public void searchFormTemplatesReturnsFormTemplates() throws IOException {
    Actor formActor = theActorCalled("titan.support1@vertafore.com");

    // change the product of formActor from default to "FORM-ADMIN-WEB-UI"
    HaveTitanContext.theNewProductOf(formActor, "FORM-ADMIN-WEB-UI");

    String productId = HaveTitanContext.theProductIdOf(formActor);
    String entityId = HaveTitanContext.theEntityIdOf(formActor);
    String tenantId = HaveTitanContext.theTenantIdOf(formActor);

    UseFormTo formApi = new UseFormTo();

    formActor.attemptsTo(
        formApi.searchForFormTemplatesUsingGetOnTheFormTemplateController(
            productId,
            tenantId,
            entityId,
            "work",
            "bySearchTerm",
            100,
            1,
            null,
            null,
            "NAME",
            null,
            null,
            true,
            null,
            null,
            null,
            null));
    SerenityRest.lastResponse().prettyPrint();

    formActor.should(
        seeThatResponse(
            "This request is testing that query params were formatted correctly in the rest call",
            res -> res.statusCode(200)));
  }
}
