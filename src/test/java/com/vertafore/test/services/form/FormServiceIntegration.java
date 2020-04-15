package com.vertafore.test.services.form;

import static com.vertafore.test.utilities.misc.HelperUtils.checkStatusForSuccess;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;

import com.vertafore.test.abilities.HaveTitanContext;
import com.vertafore.test.models.TitanUser;
import com.vertafore.test.servicewrappers.form;
import com.vertafore.test.utilities.actorextractor.BuildCastOfTitanUsers;
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

    form formApi = new form();

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
    checkStatusForSuccess();
  }
}
